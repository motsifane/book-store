package za.co.book_store.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.book_store.entity.Book;
import za.co.book_store.exception.BookNotFoundException;
import za.co.book_store.mapper.DtoMapper;
import za.co.book_store.mapper.EntityMapper;
import za.co.book_store.model.BookDto;
import za.co.book_store.repo.BookRepository;
import za.co.book_store.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public List<BookDto> getAllBooks() {
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> bookEntities = bookRepository.findAll();
        bookEntities.forEach(book -> bookDtos.add(dtoMapper.mapBookEntityToDto(book)));
        return bookDtos;
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        bookDto.setISBN(generateISBN());
        Book book = bookRepository.save(entityMapper.mapBookDtoToEntity(bookDto));
        return dtoMapper.mapBookEntityToDto(book);
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book does not exist with id : " + id));

        book = bookRepository.save(entityMapper.mapBookDtoToEntity(bookDto));
        return dtoMapper.mapBookEntityToDto(book);
    }

    @Override
    public String deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book does not exist with id : " + id));
        bookRepository.delete(book);
        return String.format("Book with id [%s] deleted", id);
    }

    private String generateISBN() {
        // Step 1: Generate first 12 digits
        Random random = new Random();
        StringBuilder isbnBuilder = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10); // Generates a digit between 0 and 9
            isbnBuilder.append(digit);
        }
        log.info("generated 12 digits [{}]", isbnBuilder);

        // Step 2: Calculate check digit
        int checkDigit = calculateCheckDigit(isbnBuilder.toString());
        log.info("calculated check digit [{}]", checkDigit);

        // Step 3: Combine to form complete ISBN
        isbnBuilder.append(checkDigit);

        return isbnBuilder.toString();
    }

    private int calculateCheckDigit(String isbnWithoutCheckDigit) {
        int sum = 0;

        for (int i = 0; i < isbnWithoutCheckDigit.length(); i++) {
            int digit = Character.getNumericValue(isbnWithoutCheckDigit.charAt(i));
            if (i % 2 == 0) { // Even index (0-based)
                sum += digit;
            } else { // Odd index
                sum += digit * 3;
            }
        }
        log.info("sum value [{}]", sum);

        // Calculate check digit
        int remainder = sum % 10;
        return remainder == 0 ? 0 : 10 - remainder;
    }
}
