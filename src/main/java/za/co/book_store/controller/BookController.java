package za.co.book_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.book_store.model.BookDto;
import za.co.book_store.service.BookService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Book", description = "the Book Api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    @Operation(
            summary = "Get all books",
            description = "Gets all the books in the book store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping("/store-book")
    @Operation(
            summary = "Add a book",
            description = "Adds a new book in the book store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<BookDto> storeBook(@RequestBody BookDto book) {
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    @Operation(
            summary = "Update a book",
            description = "Updates the book available in the book store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    @Operation(
            summary = "Delete a book",
            description = "Deletes the book available in the book store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<String> updateBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }
}
