package za.co.book_store.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.book_store.exception.BookNotFoundException;
import za.co.book_store.mapper.DtoMapper;
import za.co.book_store.mapper.EntityMapper;
import za.co.book_store.model.BookDto;
import za.co.book_store.repo.BookRepository;
import za.co.book_store.service.impl.BookServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static za.co.book_store.data.TestData.getBookDto;
import static za.co.book_store.data.TestData.getBookEntity;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private EntityMapper entityMapper;
    @Mock
    private DtoMapper dtoMapper;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void saveBook_success() {
        when(entityMapper.mapBookDtoToEntity(any())).thenReturn(getBookEntity());
        when(dtoMapper.mapBookEntityToDto(any())).thenReturn(getBookDto());
        BookDto bookDto = bookService.saveBook(getBookDto());
        Assertions.assertNotNull(bookDto);
        Assertions.assertEquals(1, bookDto.getId());
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void updateBook_success() {
        when(entityMapper.mapBookDtoToEntity(any())).thenReturn(getBookEntity());
        when(dtoMapper.mapBookEntityToDto(any())).thenReturn(getBookDto());
        when(bookRepository.findById(any())).thenReturn(Optional.of(getBookEntity()));
        BookDto bookDto = bookService.updateBook(1L, getBookDto());
        Assertions.assertNotNull(bookDto);
        Assertions.assertEquals(1, bookDto.getId());
        verify(bookRepository, times(1)).findById(any());
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void updateBook_notFound() {
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(1L, getBookDto()));
    }
}
