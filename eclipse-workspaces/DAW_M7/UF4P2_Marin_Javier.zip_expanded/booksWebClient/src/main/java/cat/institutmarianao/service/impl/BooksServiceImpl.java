package cat.institutmarianao.service.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.Book;
import cat.institutmarianao.service.BooksService;

@Service
public class BooksServiceImpl implements BooksService {
	private static final Client client = ClientBuilder.newClient();

	private final Map<String, Book> books = new HashMap<String, Book>();

	@Override
	public List<Book> getAllBooks() {
		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target = client.target(uri);

		// Prepare an invocation of the web target, accepting JSON responses for the
		// request, using GET HTTP method
		Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();

		// Read the entity from the response in a list
		List<Book> returnedBooks = res.readEntity(new GenericType<List<Book>>() {
		});

		return returnedBooks;
	}

	@Override
	public Book findBook(String isbn) {
		Book returnedBook = null;

		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).path(isbn).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target = client.target(uri);

		// Prepare an invocation of the web target, accepting JSON responses for the
		// request, using GET HTTP method
		Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();

		if (res != null && res.getStatusInfo().equals(Status.OK)) {
			// Read the entity from the response in a list
			returnedBook = res.readEntity(Book.class);
		}

		return returnedBook;
	}

	@Override
	public boolean createBook(Book book) {
		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target = client.target(uri);

		Invocation invocation = target.request(MediaType.APPLICATION_JSON)
				.buildPost(Entity.entity(book, MediaType.APPLICATION_JSON));

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();

		if (res != null && res.getStatusInfo().equals(Status.OK)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books/").port(8080).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target = client.target(uri);

		Invocation invocation = target.request(MediaType.APPLICATION_JSON)
				.buildPut(Entity.entity(book, MediaType.APPLICATION_JSON));

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();

		if (res != null && res.getStatusInfo().equals(Status.OK)) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteBook(String isbn) {
		// TODO Auto-generated method stub
		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books/" + isbn).port(8080).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target = client.target(uri);

		Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildDelete();

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();
	}
}
