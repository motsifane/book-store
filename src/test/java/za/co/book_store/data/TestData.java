package za.co.book_store.data;

import za.co.book_store.entity.Book;
import za.co.book_store.model.BookDto;

public class TestData {

    public static BookDto getBookDto() {
        return BookDto.builder().id(1).author("author").ISBN("isbn").title("title").build();
    }

    public static Book getBookEntity() {
        return Book.builder().id(1).author("author").ISBN("isbn").title("title").build();
    }
}
