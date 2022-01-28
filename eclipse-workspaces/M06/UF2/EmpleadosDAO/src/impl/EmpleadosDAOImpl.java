package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.App;
import classes.Departamento;
import classes.Empleado;
import services.EmpleadosDAO;

public class EmpleadosDAOImpl implements EmpleadosDAO {

    @Override
    public List<Empleado> selectAll(Connection con) throws SQLException {
        List<Empleado> empleados = new ArrayList<>();

        // Query to execute:
        String sql = "SELECT * FROM empleados INNER JOIN departamentos ON empleados.dept_no = departamentos.dept_no";
        Statement st = con.createStatement();

        ResultSet resultat = st.executeQuery(sql);
        System.out.println("\n" + App.PRINT_SEPARAATION);
        System.out.println("Select all:");
        while (resultat.next()) {
            Departamento dep = new Departamento(resultat.getInt(9), resultat.getString(10), resultat.getString(11));
            empleados.add(new Empleado(resultat.getInt(1), resultat.getString(2),
                    resultat.getString(3), resultat.getInt(4), resultat.getDate(5),
                    resultat.getInt(6), resultat.getInt(7), dep));
            System.out.println(empleados.get(0));
        }
        System.out.println(App.PRINT_SEPARAATION + "\n");

        return empleados;
    }

    @Override
    public void selectDept_no(Connection con) throws SQLException {
        // Query to execute:
        String sql = "SELECT * FROM departamentos WHERE dept_no = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, 10);

        ResultSet resultat = pst.executeQuery();
        while (resultat.next()) {
            System.out.println("Codi departament: " + resultat.getString(1) + " Nom: " + resultat.getString(2));
        }
    }

    @Override
    public void selectGroupingBy(Connection con) throws SQLException {
        // Query to execute:
        String sql = "SELECT dept.dnombre, em1.emp_no, em1.apellido, em1.oficio, em2.apellido, em1.salario, em1.comision "
                +
                "FROM empleados em2 JOIN empleados em1 ON em2.emp_no = em1.dir JOIN departamentos dept ON em1.dept_no = dept.dept_no "
                +
                "GROUP BY dept.dnombre";
        Statement st = con.createStatement();

        ResultSet resultat = st.executeQuery(sql);
        while (resultat.next()) {
            System.out.println("Codi departament: " + resultat.getString(1) + " Nom: " + resultat.getString(2));
        }
    }
}
