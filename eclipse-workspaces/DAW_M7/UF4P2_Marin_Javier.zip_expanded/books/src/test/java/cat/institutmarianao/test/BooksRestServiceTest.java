package cat.institutmarianao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import cat.institutmarianao.domain.Book;

public class BooksRestServiceTest {
	private static final Client client = ClientBuilder.newClient();

	@Test
	public void shouldReturnAllBooks() {
		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target = client.target(uri);

		// Prepare an invocation of the web target, accepting JSON responses for the
		// request, using GET HTTP method
		Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();

		// Expected books for this test
		Book first = new Book("9788425343537", "Ildefonso Falcones", "La catedral del mar");
		Book second = new Book("9788467009477", "Jose Maria Peridis Perez", "La luz y el misterio de las catedrales");

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();

		// Read the entity from the response in a list
		List<Book> returnedBooks = res.readEntity(new GenericType<List<Book>>() {
		});

		/* Assert */
		assertTrue(returnedBooks.contains(first));
		assertTrue(returnedBooks.contains(second));
	}

	@Test
	public void nonExistentBookShouldReturn404() {
		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).build();

		// Prepare the web target to invoke, adding the unknownISBN to the uri
		WebTarget target = client.target(uri).path("unknownISBN");

		// Prepare an invocation of the web target, accepting JSON responses for the
		// request, using GET HTTP method
		Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();

		/* Assert */
		// Check the response status
		assertEquals(Response.Status.NOT_FOUND.toString(), res.getStatusInfo().toString());
	}

	@Test
	public void attemptsToCreateNullBooksShouldReturn400() {
		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).build();

		// Prepare the web target to invoke
		WebTarget target = client.target(uri);

		// Prepare an invocation of the web target, accepting JSON responses for the
		// request, using POST HTTP method with null data
		Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildPost(null);

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();

		/* Assert */
		// Check the response status
		assertEquals(Response.Status.BAD_REQUEST.toString(), res.getStatusInfo().toString());
	}

	@Test
	public void createBookShouldReturnTheURLToGetTheBook() {
		/* Arrange */
		// Prepare the book to create
		Book book = new Book("9788423342518", "Clara Sanchez", "Lo que esconde tu nombre");

		// Prepare the URI of the ws resource to test
		URI uri = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).build();

		// Prepare the web target to invoke
		WebTarget target = client.target(uri);

		// Prepare an invocation of the web target, accepting JSON responses for the
		// request, using POST HTTP method with the book passed as JSON data
		Invocation invocation = target.request(MediaType.APPLICATION_JSON)
				.buildPost(Entity.entity(book, MediaType.APPLICATION_JSON));

		/* Act */
		// Do the invocation and get the response
		Response res = invocation.invoke();

		// Gets the URI from the response
		URI returnedUri = res.readEntity(URI.class);

		/* Assert */
		// Build the expected URI
		URI expectedUri = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).path("9788423342518")
				.build();

		// Check the uri
		assertEquals(expectedUri, returnedUri);
	}

	@Test
	public void updateBookTest() {
		// Prepare new book
		String isbn = "123456789";
		Book book = new Book(isbn, "Prueba", "Libro de prueba");

		/*
		 * Book creation on server.
		 */

		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri1 = UriBuilder.fromUri("http://localhost/books/rest/books/").port(8080).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target1 = client.target(uri1);

		// Create the book first.
		Invocation invocation1 = target1.request(MediaType.APPLICATION_JSON)
				.buildPost(Entity.entity(book, MediaType.APPLICATION_JSON));

		/* Act */
		// Do the invocation and get the response
		Response res1 = invocation1.invoke();

		/*
		 * After create book, we modify and update it on the local database.
		 */

		book.setAuthor("Prueba actualizada");
		book.setTitle("Libro de prueba actualizada");

		// Trying to update the created book.
		Invocation invocation2 = target1.request(MediaType.APPLICATION_JSON)
				.buildPut(Entity.entity(book, MediaType.APPLICATION_JSON));

		/* Act */
		// Do the invocation and get the response
		Response res2 = invocation2.invoke();

		/*
		 * After updating the book, we need to take it from the datebase to check after
		 * the values.
		 */

		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri2 = UriBuilder.fromUri("http://localhost/books/rest/books").port(8080).path(isbn).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target2 = client.target(uri2);

		// Prepare an invocation of the web target, accepting JSON responses for the
		// request, using GET HTTP method
		Invocation invocation3 = target2.request(MediaType.APPLICATION_JSON).buildGet();

		/* Act */
		// Do the invocation and get the response
		Response res3 = invocation3.invoke();

		// Response book
		Book resBook = res3.readEntity(Book.class);

		/* Assert */
		assertEquals(isbn, resBook.getIsbn());
		assertEquals(book.getAuthor(), resBook.getAuthor());
		assertEquals(book.getTitle(), resBook.getTitle());
	}

	@Test
	public void deleteBookTest() {
		// Prepare new book
		String isbn = "987654321";
		Book book = new Book(isbn, "Eliminar", "Libro para eliminar");

		/*
		 * Book creation on server.
		 */

		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri1 = UriBuilder.fromUri("http://localhost/books/rest/books/").port(8080).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target1 = client.target(uri1);

		// Create the book first.
		Invocation invocation1 = target1.request(MediaType.APPLICATION_JSON)
				.buildPost(Entity.entity(book, MediaType.APPLICATION_JSON));

		/* Act */
		// Do the invocation and get the response
		Response res1 = invocation1.invoke();

		/*
		 * New uri to delete the book:
		 */

		/* Arrange */
		// Prepare the URI of the ws resource to test
		URI uri2 = UriBuilder.fromUri("http://localhost/books/rest/books/").port(8080).path(book.getIsbn()).build();

		// Prepare the web target to invoke, from the uri
		WebTarget target2 = client.target(uri2);

		// Create the book first.
		Invocation invocation2 = target1.request(MediaType.APPLICATION_JSON)
				.buildDelete();

		/* Act */
		// Do the invocation and get the response
		Response res2 = invocation1.invoke();

		assertEquals(200, res2.getStatus());
	}
}