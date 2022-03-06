package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Departamento;
import classes.Empleado;
import impl.EmpleadosDAOImpl;
import manager.ConnectionManager;
import services.EmpleadosDAO;

public class App {
	public static final String PRINT_SEPARATION = "--------------------------------------------";

	public static ConnectionManager daoConnection;

	public static List<Empleado> empleados;
	public static List<Departamento> departamentos = new ArrayList<>();
	public static Scanner sc;

	/**
	 * DAO of our "Empleados" and "Departamentos"'s data base.
	 * 
	 * Some methods are not of the exercise, just to make tests and the default DAO
	 * methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Empleado empEjemplo = new Empleado(1, "MARIN", "PROGRAMER", 7839,
		// new java.sql.Date(new Date().getTime()), 2000.0, 1348.67, 20);

		// Departamento depEjemplo = new Departamento(50, "BIOLOGIA", "VALENCIA");

		try {
			// New object of the DAO:
			EmpleadosDAO empDAO = new EmpleadosDAOImpl();
			// DepartamentosDAO depDAO = new DepartamentosDAOImpl();

			// Select of every employee and departament
			// System.out.println("Todos los datos:");
			empleados = empDAO.selectAll();
			// depDAO.selectAll();
			printAll();

			// Ejercicio 4:
			System.out.println("Ejercicio 4.1: ");
			empDAO.selectOne(30);

			System.out.println("Ejercicio 4.2");
			empDAO.selectGroupingBy();

			// Ejercicio 5:
			System.out.println("Ejercicio 5.1:");
			empDAO.selectByDepartamento(20);

			System.out.println("Ejercicio 5.2:");
			empDAO.selectByApellido("SAN");

			System.out.println("Ejercicio 5.3:");
			empDAO.selectByOficio();

			// Ejercicio 6:
			System.out.println("Ejercicio 6.1:");
			empDAO.insertOne();

			System.out.println("Ejercicio 6.2:");
			empDAO.deleteOne();

			// Ejercicio 7:
			System.out.println("Ejercicio 7: ");
			empDAO.alterColumns("add", "nombre");
			empDAO.alterColumns("drop", "nombre");

			// Ejercicio 8:
			System.out.println("Ejercicio 8: ");
			ConnectionManager.DBData();

			// Ejercicio EXTRA:
			System.out.println("Ejercicio EXTRA: ");
			empDAO.selectXJefes();

		} catch (SQLException e) {
			System.out.println("Error relacionado con el SQL!");
			e.printStackTrace();
		}
	}

	private static void printAll() {
		System.out.println(PRINT_SEPARATION);
		for (Empleado empleado : empleados) {
			System.out.println(empleado);
			for (Departamento departamento : departamentos) {
				if (departamento.getDept_no() == empleado.getDept_no()) {
					System.out.println("\t" + departamento);
				}
			}
		}
		System.out.println(PRINT_SEPARATION + "\n");
	}
}
