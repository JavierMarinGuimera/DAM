package cat.institutmarianao.service;

import java.net.URI;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import cat.institutmarianao.domain.Book;
import cat.institutmarianao.domain.repository.BookRepository;
import cat.institutmarianao.domain.repository.impl.InMemoryBookRepository;

@Path("/books")
@Singleton
public class BooksRestService {
	private BookRepository bookRepository = new InMemoryBookRepository();

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getAll() {
		return bookRepository.getAll();
	}

	@GET
	@Path("{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("isbn") String isbn) {
		Book book = bookRepository.get(isbn);

		if (book == null) {
			throw new NotFoundException();
		}

		return Response.ok(book).build();
	}

	@GET
	@Path("findByTitle/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> findByTitle(@PathParam("title") String title) {
		return bookRepository.findByTitle(title);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Book book) {
		if (book == null) {
			throw new BadRequestException();
		}

		bookRepository.add(book);
		URI bookUri = uriInfo.getAbsolutePathBuilder().path(book.getIsbn()).build();
		return Response.ok(bookUri).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void edit(Book book) {
		bookRepository.update(book);
	}

	@DELETE
	@Path("{isbn}")
	public void remove(@PathParam("isbn") String isbn) {
		bookRepository.delete(isbn);
	}
}