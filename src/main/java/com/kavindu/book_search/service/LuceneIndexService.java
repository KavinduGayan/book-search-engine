package com.kavindu.book_search.service;

import com.kavindu.book_search.model.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class LuceneIndexService {

    private final Analyzer analyzer = new StandardAnalyzer();
    private final Directory indexDirectory;

    public LuceneIndexService() throws IOException {
        // Change the path to wherever you'd like the index stored
        String indexPath = "./lucene-index";
        this.indexDirectory = FSDirectory.open(Paths.get(indexPath));
    }

    public void indexBook(Book book) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        try (IndexWriter writer = new IndexWriter(indexDirectory, config)) {
            Document doc = new Document();
            doc.add(new StringField("id", book.getId(), Field.Store.YES));
            doc.add(new TextField("title", book.getTitle(), Field.Store.YES));
            doc.add(new TextField("author", book.getAuthor(), Field.Store.YES));
            doc.add(new TextField("description", book.getDescription(), Field.Store.YES));
            writer.addDocument(doc);
        }
    }

    public List<Book> searchBooks(String queryStr) throws Exception {
        List<Book> results = new ArrayList<>();
        try (DirectoryReader reader = DirectoryReader.open(indexDirectory)) {
            IndexSearcher searcher = new IndexSearcher(reader);
            QueryParser parser = new MultiFieldQueryParser(
                    new String[] { "title", "author", "description" },
                    analyzer
            );
            Query query = parser.parse(queryStr);
            TopDocs hits = searcher.search(query, 10);
            for (ScoreDoc sd : hits.scoreDocs) {
                Document d = searcher.storedFields().document(sd.doc);
                Book book = new Book();
                book.setId(d.get("id"));
                book.setTitle(d.get("title"));
                book.setAuthor(d.get("author"));
                book.setDescription(d.get("description"));
                results.add(book);
            }
        }
        return results;
    }
}

