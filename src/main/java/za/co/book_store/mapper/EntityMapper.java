package za.co.book_store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import za.co.book_store.entity.Book;
import za.co.book_store.model.BookDto;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "ISBN", target = "ISBN")
    Book mapBookDtoToEntity(BookDto bookDto);
}
