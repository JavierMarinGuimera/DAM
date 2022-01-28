package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import classes.Empleado;

/**
 * EmpleadosDAO
 */

public interface EmpleadosDAO {

    public List<Empleado> selectAll(Connection con) throws SQLException;

    public void selectDept_no(Connection con) throws SQLException;

    public void selectGroupingBy(Connection con) throws SQLException;
}