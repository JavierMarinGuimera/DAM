package cat.institutmarianao.service;

import java.util.List;

import cat.institutmarianao.domain.Book;

public interface BooksService {

	List<Book> getAllBooks();

	Book findBook(String isbn);

	boolean createBook(Book book);

	boolean updateBook(Book book);

	void deleteBook(String isbn);

}