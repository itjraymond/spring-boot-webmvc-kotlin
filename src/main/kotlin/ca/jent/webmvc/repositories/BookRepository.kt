package ca.jent.webmvc.repositories

import ca.jent.webmvc.domain.Book
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap

@Repository
class BookRepository {

    val bookStore = mutableMapOf<String, Book>(
            "1" to Book("1", "Title one", "John One", Instant.now()),
            "2" to Book("2", "Title two", "John Two", Instant.now()),
            "3" to Book("3", "Title three", "John Three", Instant.now()),
            "4" to Book("4", "Title four", "John Four", Instant.now())
    )

    // *****************************************************************************************
    // NOTE:  another neat way of creating a small data store is as follow:
    val altBookStore = arrayOf(
            Book("10", "Title ten", "John Ten", Instant.now()),
            Book("11", "Title eleven", "John Eleven", Instant.now()),
            Book("12", "Title twelve", "John Twelve", Instant.now())
    )
    val altMapBookStore = ConcurrentHashMap<String, Book>(altBookStore.associateBy(Book::id))

    // NOTE: to improve on the above (because the above actually "expose" the var bookStore or altBookStore
    //       we can create a companion object instead which will keep encapsulated our data store
    companion object {
        val privAltBookStore = arrayOf(
            Book("10", "Title ten", "John Ten", Instant.now()),
            Book("11", "Title eleven", "John Eleven", Instant.now()),
            Book("12", "Title twelve", "John Twelve", Instant.now())
        )
        val privMapBookStore = ConcurrentHashMap<String, Book>(privAltBookStore.associateBy(Book::id))
    }
    // *****************************************************************************************


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