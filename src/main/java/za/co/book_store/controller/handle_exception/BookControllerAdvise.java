package za.co.book_store.controller.handle_exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import za.co.book_store.exception.BookNotFoundException;
import za.co.book_store.model.ErrorDto;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class BookControllerAdvise {

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    public Object handleNotFound(BookNotFoundException ex) {
        log.error("book not found [{}]", ex);
        return ErrorDto.builder()
                .successful(false).message("Requested book is not found.")
                .errorCode(HttpStatus.NOT_FOUND.value()).build();
    }
}
