package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.App;
import classes.Departamento;
import manager.ConnectionManager;
import services.DepartamentosDAO;

public class DepartamentosDAOImpl implements DepartamentosDAO {
    private static final String FOUND = "El departamento ya se encuentra en la base de datos.";
    private static final String NOT_FOUND = "El departamento no se encuentra en la base de datos.";

    @Override
    public void selectAll() throws SQLException {
        // Query to execute:
        String sql = "SELECT * FROM departamentos";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);

        ResultSet result = st.executeQuery();
        while (result.next()) {
            App.departamentos.add(new Departamento(
                    result.getInt(1), result.getString(2),
                    result.getString(3)));
        }

        System.out.println("Select all 'departamentos' operation done! \n");
        result.close();
        st.close();
    }

    @Override
    public Departamento selectOne(int dept_no) throws SQLException {
        // Query to execute:
        String sql = "SELECT * FROM departamentos WHERE dept_no = ?";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, dept_no);

        ResultSet result = st.executeQuery();
        con.close();

        while (result.next()) {
            Departamento dept = new Departamento(
                    result.getInt(1), result.getString(2), result.getString(3));
            System.out.println(dept);
            result.close();
            return dept;
        }

        result.close();
        st.close();
        return null;
    }

    @Override
    public void insertOne(Departamento dep) throws SQLException {
        if (selectOne(dep.getDept_no()) == null) {
            String sql = "INSERT INTO depLEADOS (`dep_no`, `apellido`, `oficio`, `dir`, `fecha_alt`, `salario`, `comision`, `dept_no`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            st.setInt(1, dep.getDept_no());
            st.setString(2, dep.getName());
            st.setString(3, dep.getIoc());

            st.executeUpdate();
            st.close();
        } else {
            System.out.println(FOUND);
        }
    }

    @Override
    public void updateOne(int dept_no) throws SQLException {
        if (selectOne(dept_no) != null) {
            String sql = "UPDATE empleados SET dnombre = 'MODIFIED' WHERE  dept_no = ?";
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            st.setInt(1, dept_no);

            st.executeQuery();
            st.close();
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    @Override
    public void deleteOne(int dept_no) throws SQLException {
        if (selectOne(dept_no) != null) {
            String sql = "DELETE FROM departamentos WHERE dept_no = ?";
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            st.setInt(1, dept_no);

            st.executeQuery();
            st.close();
        } else {
            System.out.println(NOT_FOUND);
        }
    }
}
