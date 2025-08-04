package com.kavindu.book_search.controller;

import com.kavindu.book_search.model.Book;
import com.kavindu.book_search.service.LuceneIndexService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final LuceneIndexService indexService;

    public BookController(LuceneIndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping("/index")
    public ResponseEntity<String> indexBook(@RequestBody Book book) {
        try {
            indexService.indexBook(book);
            return ResponseEntity.ok("Indexed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Indexing failed");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(@RequestParam String q) {
        try {
            return ResponseEntity.ok(indexService.searchBooks(q));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

