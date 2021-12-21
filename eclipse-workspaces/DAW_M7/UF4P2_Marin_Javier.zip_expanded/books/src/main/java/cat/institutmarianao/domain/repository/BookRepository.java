package cat.institutmarianao.domain.repository;

import cat.institutmarianao.domain.Book;
import java.util.List;

public interface BookRepository {
    List<Book> getAll(); 
    void add(Book book);
    void update(Book book);
    void delete(String isbn);
    Book get(String isbn);
    List<Book> findByTitle(String title);    
}
