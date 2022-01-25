package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/empleados";
		String user = "root";
		String passwd = "";

		try {
			Connection con = DriverManager.getConnection(url, user, passwd);

			// Select every "Departamento" query::
			selectAll(con);

			// Select from specific "Dept_no":
			selectDept_no(con);

			// Select grouping by "Departament":
			selectGroupingBy(con);

		} catch (SQLException e) {
			System.out.println("Error relacionado con el SQL!");
			e.printStackTrace();
		}
	}

	private static void selectGroupingBy(Connection con) throws SQLException {
		// Query to execute:
		String sql = "SELECT dept.dnombre, em.emp_no, em.apellido, em.oficio, em2.apellido, em.salario, em.comision "
				+ "FROM empleados em JOIN empleados em2 ON em.dir = em2.emp_no JOIN departamentos dept ON em.dept_no = dept.dept_no "
				+ "GROUP BY em.dept_no  ";
		Statement st = con.createStatement();

		executeAndPrintResults(st, sql);
	}

	// SELECT ALL
	private static void selectAll(Connection con) throws SQLException {
		// Query to execute:
		String sql = "SELECT * FROM departamentos";
		Statement st = con.createStatement();

		executeAndPrintResults(st, sql);
	}

	private static void selectDept_no(Connection con) throws SQLException {
		// Query to execute:
		String sql = "SELECT * FROM departamentos WHERE dept_no = ?;";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, 10);

		executeAndPrintResults(st, sql);
	}

	private static void executeAndPrintResults(Statement st, String sql) throws SQLException {
		ResultSet resultat = st.executeQuery(sql);
		while (resultat.next()) {
			System.out.println("Codi departament: " + resultat.getInt(1) + " Nom: " + resultat.getString(2));
		}
	}

}
