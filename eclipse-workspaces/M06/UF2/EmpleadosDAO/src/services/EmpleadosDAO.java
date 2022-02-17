package services;

import java.sql.SQLException;
import java.util.List;

import classes.Empleado;

/**
 * EmpleadosDAO
 */

public interface EmpleadosDAO {

    /*
     * BASIC DAO METHODS:
     */
    public List<Empleado> selectAll() throws SQLException;

    public Empleado selectOne(int emp_no) throws SQLException;

    public Enum insertOne() throws SQLException;

    public void updateOne(int emp_no) throws SQLException;

    public void deleteOne() throws SQLException;

    /*
     * CUSTOM METHODS:
     */
    public void selectXJefes() throws SQLException;

    public void selectGroupingBy() throws SQLException;

    public void selectByDepartamento(int dept_no) throws SQLException;

    public void selectByApellido(String apellido) throws SQLException;

    public void selectByOficio() throws SQLException;

    public void alterColumns(String operation, String column) throws SQLException;
}