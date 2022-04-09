package main;

import java.util.HashSet;
import java.util.List;

import manager.DAOManager;
import pojos.Autors;
import pojos.Idiomes;
import pojos.Llibres;
import service.AuthorsService;
import service.BooksService;
import service.LanguageService;

public class App {

	public static void main(String[] args) {
		LanguageService ls = DAOManager.getLanguageService(DAOManager.JDBC);
		// Añadir idioma
		Idiomes language = new Idiomes(991, "Español");
		hasWorked("idiomas", "creación", ls.createOne(language));
		// Eliminar idioma
		hasWorked("idiomas", "eliminación", ls.deleteOne(991));

		AuthorsService as = DAOManager.getAuthorsService(DAOManager.HIBERNATE);
		// Leer autores
		List<Autors> authors = as.readAll();
		System.out.println("Autores:");
		for (Autors author : authors) {
			System.out.println(author);
		}
		System.out.println();

		// Añadir autor
		Autors author1 = new Autors(991, "Prueba 1");
		hasWorked("autores", "creación", as.createOne(author1));

		/**
		 * No he conseguido hacer la inserción de un autor con varios libros.
		 */
		// HashSet<Llibres> authorBooks = new HashSet<>();
		// Autors author2 = new Autors(992, "Prueba 2", "España", "Horror",
		// authorBooks);
		// hasWorked("autores", "creación", as.createOne(author2));

		BooksService bs = DAOManager.getBooksService(DAOManager.HIBERNATE);
		// Leer libro
		System.out.println(bs.readOne(1));
	}

	private static void hasWorked(String tabla, String operacion, boolean resultado) {
		System.out
				.println(
						"La " + operacion + " en la tabla " + tabla + (resultado ? " ha funcionado." : " ha fallado."));
	}
}
