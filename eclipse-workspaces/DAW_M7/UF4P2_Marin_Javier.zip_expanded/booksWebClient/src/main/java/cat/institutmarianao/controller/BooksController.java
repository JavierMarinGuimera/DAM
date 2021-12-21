package cat.institutmarianao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Book;
import cat.institutmarianao.service.BooksService;

@Controller
public class BooksController {
	@Autowired
	private BooksService booksService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showBooks()
			throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("vistaLibros");

		List<Book> returnedBooks = booksService.getAllBooks();

		modelview.getModelMap().addAttribute("libros", returnedBooks);
		return modelview;
	}

	@RequestMapping(value = "/clientCreateBook", method = RequestMethod.GET)
	public ModelAndView createBookGET()
			throws ServletException, IOException {

		ModelAndView modelview = new ModelAndView("createBook");

		modelview.getModelMap().addAttribute("libro", new Book());

		return modelview;
	}

	@RequestMapping(value = "/clientCreateBook", method = RequestMethod.POST)
	public String createBookPOST(@ModelAttribute("libro") Book book)
			throws ServletException, IOException {

		booksService.createBook(book);

		return "redirect:/";
	}

	// Cargar la vista con el formulario de actualización de libro.
	@RequestMapping(value = "/clientUpdateBook", method = RequestMethod.GET)
	public ModelAndView updateBookGET(@RequestParam("isbn") String isbn)
			throws ServletException, IOException {

		ModelAndView modelview = new ModelAndView("updateBook");

		Book libro = booksService.findBook(isbn);

		modelview.getModelMap().addAttribute("libro", libro);
		return modelview;
	}

	// Cuando en el formulario de actualizar libro envia por POST, viene aquí.
	@RequestMapping(value = "/clientUpdateBook", method = RequestMethod.POST)
	public String updateBookPOST(@ModelAttribute("libro") Book book)
			throws ServletException, IOException {

		booksService.updateBook(book);

		return "redirect:/";
	}

	@RequestMapping(value = "/clientDeleteBook", method = RequestMethod.GET)
	public String deleteBook(@RequestParam("isbn") String isbn)
			throws ServletException, IOException {

		booksService.deleteBook(isbn);

		return "redirect:/";
	}
}
