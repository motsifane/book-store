package za.co.book_store.service;

import za.co.book_store.model.BookDto;

import java.util.List;

public interface BookService {

    /**
     * Returns all the books in the book store
     */
    List<BookDto> getAllBooks();

    /**
     * Returns the saved book in the store
     *
     * @param book this is the new book to be saved
     */
    BookDto saveBook(BookDto book);

    /**
     * Returns the book with the updated details
     *
     * @param id  this is the identifier of the book that needs to be updated
     * @param book this is the book to be updated
     */
    BookDto updateBook(Long id, BookDto book);

    /**
     * Returns confirmation of the deletion of the book
     *
     * @param id  this is the identifier of the book that needs to be deleted
     */
    String deleteBook(Long id);
}
