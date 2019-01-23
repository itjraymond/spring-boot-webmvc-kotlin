package ca.jent.webmvc.controllers

import ca.jent.webmvc.domain.Book
import ca.jent.webmvc.services.BooksService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BooksController {

    @Autowired
    lateinit var booksService: BooksService

    @GetMapping("books/{id}")
    fun getBook(@PathVariable("id") id: String): Book  =
            booksService.findBook(id) ?: Book("","Not found", "", null)

    @GetMapping("books")
    fun getBooks(): List<Book> = booksService.findAllBook()

    @PostMapping("books")
    fun createBook(@RequestBody book: Book): ResponseEntity<String> {
        booksService.createBook(book)
        return ResponseEntity.status(HttpStatus.CREATED).build<String>()
    }

    @PutMapping("books/{id}")
    fun updateBook(@RequestBody book: Book): ResponseEntity<Book?> {
        val b: Book = booksService.updateBook(book) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)

        return ResponseEntity.status(HttpStatus.OK).body(b)

        /*
        I think the above should be:
        val b: Book? = booksService.updateBook(book)
        return if (b != null) {
                    ResponseEntity(b, HttpStatus.OK)
               } else {
                    ResponseEntity(ErrorResponse("Book not found", "specific here"), HttpStatus.NOT_FOUND)
               }
         */

    }

}