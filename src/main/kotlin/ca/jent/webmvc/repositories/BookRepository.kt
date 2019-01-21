package ca.jent.webmvc.repositories

import ca.jent.webmvc.domain.Book
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class BookRepository {

    val bookStore = mutableMapOf<String, Book>(
            "1" to Book("1", "Title one", "John One", Instant.now()),
            "2" to Book("2", "Title two", "John Two", Instant.now()),
            "3" to Book("3", "Title three", "John Three", Instant.now()),
            "4" to Book("4", "Title four", "John Four", Instant.now())
    )

    fun findBook(id: String): Book? = bookStore[id]

    fun findAllBook(): List<Book> = bookStore.values.map { it }

    fun createBook(book: Book) {
        // cheap and naive way to create unique id
        val nextId: Int = bookStore.size + 1
        val b = Book("" + nextId, book.title, book.author, book.publishedDate)
        bookStore[b.id] = b
    }

    fun updateBook(book: Book): Book? {
        bookStore[book.id]?.author = book.author
        bookStore[book.id]?.title = book.title
        bookStore[book.id]?.publishedDate = book.publishedDate
        return bookStore[book.id]
    }
}