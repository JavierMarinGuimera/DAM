package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Departamento;
import classes.Empleado;
import impl.EmpleadosDAOImpl;
import services.EmpleadosDAO;

public class App {
	public static final String PRINT_SEPARATION = "--------------------------------------------";

	public static List<Empleado> empleados = new ArrayList<>();
	public static List<Departamento> departamentos = new ArrayList<>();
	public static Scanner sc;

	public static void main(String[] args) {
		MyConnection myCon = new MyConnection();
		sc = new Scanner(System.in);

		// Empleado empEjemplo = new Empleado(1, "MARIN", "PROGRAMER", 7839,
		// new java.sql.Date(new Date().getTime()), 2000.0, 1348.67, 20);

		// Departamento depEjemplo = new Departamento(50, "BIOLOGIA", "VALENCIA");

		try {
			Connection con = DriverManager.getConnection(myCon.getUrl(), myCon.getUser(), myCon.getPasswd());

			// New object of the DAO:
			EmpleadosDAO empDAO = new EmpleadosDAOImpl();
			// DepartamentosDAO depDAO = new DepartamentosDAOImpl();

			// Select of every employee and departament
			// System.out.println("Todos los datos:");
			// empDAO.selectAll(con);
			// depDAO.selectAll(con);
			// printAll();

			// Ejercicio 4:
			System.out.println("Ejercicio 4.1: ");
			// depDAO.selectOne(con, 30);

			System.out.println("Ejercicio 4.2");
			empDAO.selectGroupingBy(con);

			// Ejercicio 5:
			System.out.println("Ejercicio 5.1:");
			// empDAO.selectByDepartamento(con, 20);

			System.out.println("Ejercicio 5.2:");
			// empDAO.selectByApellido(con, "SAN");

			System.out.println("Ejercicio 5.3:");
			// empDAO.selectByOficio(con);

			// Ejercicio 6:
			System.out.println("Ejercicio 6.1:");
			// empDAO.insertOne(con);

			System.out.println("Ejercicio 6.2:");
			// empDAO.deleteOne(con);

			// Ejercicio 7:
			System.out.println("Ejercicio 7: ");
			// empDAO.alterColumns(con, "add", "nombre");
			// empDAO.alterColumns(con, "drop", "nombre");

			// Ejercicio 8:
			System.out.println("Ejercicio 8: ");
			// myCon.DBData(con);

			// Ejercicio EXTRA:
			System.out.println("Ejercicio EXTRA: ");
			// empDAO.selectXJefes(con);

			sc.close();
		} catch (SQLException e) {
			System.out.println("Error relacionado con el SQL!");
			e.printStackTrace();
		}
	}

	// private static void printAll() {
	// System.out.println(PRINT_SEPARATION);
	// for (Empleado empleado : empleados) {
	// System.out.println(empleado);
	// for (Departamento departamento : departamentos) {
	// if (departamento.getDept_no() == empleado.getDept_no()) {
	// System.out.println("\t" + departamento);
	// }
	// }
	// }
	// System.out.println(PRINT_SEPARATION + "\n");
	// }
}
