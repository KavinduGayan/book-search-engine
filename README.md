
# 📚 Book Search API

A Spring Boot project that demonstrates full-text search capability using **Apache Lucene** integrated with the **Gradle** build system.

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3.x
- Apache Lucene
- Gradle
- REST API
- JSON

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Git
- Gradle 8.x (or use the included wrapper)

### Clone the repository

```bash
git clone https://github.com/yourusername/book-search.git
cd book-search
````

### Build the project

```bash
./gradlew build
```

### Run the application

```bash
./gradlew bootRun
```

The application will start on:
📍 `http://localhost:8383`

---

## 📖 API Endpoints

### 🔍 Search Books

```
GET /books/search?q={query}
```

Example:

```
GET http://localhost:8383/books/search?q=atomic
```

### ➕ Add a Book

```
POST /books
```

**Request Body:**

```json
{
  "id": "1",
  "title": "Atomic Habits",
  "author": "James Clear",
  "description": "A guide to building good habits and breaking bad ones."
}
```

---

## 📁 Project Structure

```
book-search/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/booksearch/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── model/
│   │   │       └── BookSearchApplication.java
│   │   └── resources/
│   │       └── application.yml
├── build.gradle
└── README.md
```

---

## ⚙️ Configuration

Edit `application.yml` (or `application.properties`) as needed:

```yaml
server:
  port: 8383
```

---

## 🧪 Running Tests

```bash
./gradlew test
```

---

## 👤 Author

**Gayan Rupasinghe**
• [Medium]([https://medium.com/@your-profile](https://medium.com/@gayanrupasinghe21)

```
