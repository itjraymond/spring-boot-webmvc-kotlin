package ca.jent.webmvc.services

import ca.jent.webmvc.domain.Book
import ca.jent.webmvc.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BooksService {

    @Autowired
    lateinit var bookRepository: BookRepository

    fun findBook(id: String): Book? = bookRepository.findBook(id)

    fun findAllBook(): List<Book> = bookRepository.findAllBook()

    fun createBook(book: Book) = bookRepository.createBook(book)

    fun updateBook(book: Book): Book? = bookRepository.updateBook(book)

}