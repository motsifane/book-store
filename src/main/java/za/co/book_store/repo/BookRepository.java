package za.co.book_store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.book_store.entity.Book;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
}
