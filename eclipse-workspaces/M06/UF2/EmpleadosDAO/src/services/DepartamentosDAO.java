package services;

import java.sql.Connection;
import java.sql.SQLException;

import classes.Departamento;

public interface DepartamentosDAO {

    // Basic DAO methods:
    public void selectAll() throws SQLException;

    public Departamento selectOne(int id) throws SQLException;

    public void insertOne(Departamento dep) throws SQLException;

    public void updateOne(int dept_no) throws SQLException;

    public void deleteOne(int dept_no) throws SQLException;

    // Custom methods:
}
