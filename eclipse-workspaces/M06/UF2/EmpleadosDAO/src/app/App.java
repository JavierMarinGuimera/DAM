package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Empleado;
import impl.EmpleadosDAOImpl;
import services.EmpleadosDAO;

public class App {
	public static final String PRINT_SEPARAATION = "--------------------------------------------";

	public static void main(String[] args) {
		MyConnection myCon = new MyConnection();
		List<Empleado> empleados = new ArrayList<>();

		try {
			Connection con = DriverManager.getConnection(myCon.getUrl(), myCon.getUser(), myCon.getPasswd());

			// New object of the DAO:
			EmpleadosDAO empDAO = new EmpleadosDAOImpl();

			//
			empleados = empDAO.selectAll(con);
			printEmployees(empleados);

			// Select from specific "Dept_no":
			// empDAO.selectDept_no(con);

			// Select grouping by "Departament":
			// empDAO.selectGroupingBy(con);

		} catch (SQLException e) {
			System.out.println("Error relacionado con el SQL!");
			e.printStackTrace();
		}
	}

	private static void printEmployees(List<Empleado> empleados) {
		for (Empleado empleado : empleados) {
			System.out.println(empleado);
		}
	}

}
