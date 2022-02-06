package services;

import java.sql.Connection;
import java.sql.SQLException;

import classes.Departamento;

public interface DepartamentosDAO {

    // Basic DAO methods:
    public void selectAll(Connection con) throws SQLException;

    public Departamento selectOne(Connection con, int id) throws SQLException;

    public void insertOne(Connection con, Departamento dep) throws SQLException;

    public void updateOne(Connection con, int dept_no) throws SQLException;

    public void deleteOne(Connection con, int dept_no) throws SQLException;

    // Custom methods:
}
