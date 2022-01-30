package services;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * EmpleadosDAO
 */

public interface EmpleadosDAO {

    // Basic DAO methods:
    public void selectAll(Connection con) throws SQLException;

    public boolean selectOne(Connection con, int emp_no) throws SQLException;

    public void insertOne(Connection con) throws SQLException;

    public void updateOne(Connection con, int emp_no) throws SQLException;

    public void deleteOne(Connection con) throws SQLException;

    // Custom methods:
    public void selectGroupingBy(Connection con) throws SQLException;

    public void selectByDepartamento(Connection con, int dept_no) throws SQLException;

    public void selectByApellido(Connection con, String apellido) throws SQLException;

    public void selectByOficio(Connection con) throws SQLException;

    public void alterColumns(Connection con, String operation, String column) throws SQLException;
}