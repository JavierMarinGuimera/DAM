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

	private static void selectAll(Connection con) throws SQLException {
		// Query to execute:
		String sql = "SELECT * FROM departamentos";
		Statement st = con.createStatement();

		ResultSet resultat = st.executeQuery(sql);
		System.out.println("Select all:");
		while (resultat.next()) {
			System.out.println("Codi departament: " + resultat.getInt(1) + " Nom: " + resultat.getString(2));
		}
		System.out.println("------------------------------");
	}

	private static void selectDept_no(Connection con) throws SQLException {
		// Query to execute:
		String sql = "SELECT * FROM departamentos WHERE dept_no = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, 10);

		ResultSet resultat = pst.executeQuery();
		while (resultat.next()) {
			System.out.println("Codi departament: " + resultat.getString(1) + " Nom: " + resultat.getString(2));
		}
		
//		executeAndPrintResults(st, sql);
	}
	
	private static void selectGroupingBy(Connection con) throws SQLException {
		// Query to execute:
		String sql = "SELECT dept.dnombre, em1.emp_no, em1.apellido, em1.oficio, em2.apellido, em1.salario, em1.comision " + 
				"FROM empleados em2 JOIN empleados em1 ON em2.emp_no = em1.dir JOIN departamentos dept ON em1.dept_no = dept.dept_no " +
				"GROUP BY dept.dnombre";
		Statement st = con.createStatement();

		ResultSet resultat = st.executeQuery(sql);
		while (resultat.next()) {
			System.out.println("Codi departament: " + resultat.getString(1) + " Nom: " + resultat.getString(2));
		}
	}

}
