package impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.App;
import classes.Empleado;
import services.DepartamentosDAO;
import services.EmpleadosDAO;

public class EmpleadosDAOImpl implements EmpleadosDAO {
    private static final String FOUND = "El empleado ya se encuentra en la base de datos.";
    private static final String NOT_FOUND = "El empleado no se encuentra en la base de datos.";

    /*
     * BASIC DAO METHODS:
     */

    @Override
    public void selectAll(Connection con) throws SQLException {

        // Query to execute:
        String sql = "SELECT * FROM empleados";
        PreparedStatement st = con.prepareStatement(sql);

        ResultSet result = st.executeQuery();
        while (result.next()) {
            App.empleados.add(new Empleado(
                    result.getInt(1), result.getString(2),
                    result.getString(3), result.getInt(4), result.getDate(5),
                    result.getInt(6), result.getInt(7), result.getInt(8)));
        }

        System.out.println("Operación Select all 'empleados' completada!");
        result.close();
        st.close();
    }

    @Override
    public Empleado selectOne(Connection con, int emp_no) throws SQLException {
        // Query to execute:
        String sql = "SELECT * FROM empleados WHERE emp_no = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, emp_no);

        ResultSet result = st.executeQuery();
        while (result.next()) {
            Empleado emp = new Empleado(
                    result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getDate(5),
                    result.getInt(6), result.getInt(7), result.getInt(8));
            System.out.println(emp);
            result.close();
            st.close();
            return emp;
        }
        result.close();
        st.close();
        return null;
    }

    @Override
    public void insertOne(Connection con) throws SQLException {
        System.out.println("Vamos a crear un usuario nuevo. Introduce los datos:");

        Scanner sc = App.sc;
        Empleado emp;

        while (true) {
            try {
                System.out.print("- Nº: ");
                int emp_no = Integer.parseInt(sc.nextLine());
                System.out.print("- Apellido: ");
                String apellido = sc.nextLine().toUpperCase();
                System.out.print("- Oficio: ");
                String oficio = sc.nextLine().toUpperCase();
                System.out.print("- Director (0 para que sea NULL): ");
                int dir = Integer.parseInt(sc.nextLine());
                Date fecha_alt = new Date(new java.util.Date().getTime());
                System.out.print("- Salario: ");
                double salario = Double.parseDouble(sc.nextLine());
                System.out.print("- Comisión: ");
                double comision = Double.parseDouble(sc.nextLine());
                System.out.print("- Departamento: ");
                int dept_no = Integer.parseInt(sc.nextLine());

                emp = new Empleado(emp_no, apellido, oficio, dir, fecha_alt, salario,
                        comision, dept_no);

                DepartamentosDAO depDAO = new DepartamentosDAOImpl();

                if ((dir == 0 || selectOne(con, dir) != null) && depDAO.selectOne(con, emp.getDept_no()) != null) {
                    if (selectOne(con, emp.getEmp_no()) != null) {
                        String sql = "INSERT INTO EMPLEADOS (`emp_no`, `apellido`, `oficio`, `dir`, `fecha_alt`, `salario`, `comision`, `dept_no`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement st = con.prepareStatement(sql);
                        st.setInt(1, emp.getEmp_no());
                        st.setString(2, emp.getApellido());
                        st.setString(3, emp.getOficio());

                        if (emp.getDir() == 0) {
                            st.setNull(4, Types.INTEGER);
                        } else {
                            st.setInt(4, emp.getDir());
                        }

                        st.setDate(5, emp.getFecha_alt());
                        st.setDouble(6, emp.getSalario());
                        st.setDouble(7, emp.getComision());
                        st.setInt(8, emp.getDept_no());

                        st.executeUpdate();
                        st.close();
                        System.out.println("Usuario creado con éxito!");
                        break;
                    } else {
                        System.out.println(FOUND);
                    }
                } else {
                    System.out.println("El director o el departamento no existe.");
                }
            } catch (Exception e) {
                System.out.println("Debes introducir datos válidos.");
            }
        }
    }

    @Override
    public void updateOne(Connection con, int emp_no) throws SQLException {
        if (selectOne(con, emp_no) != null) {
            String sql = "UPDATE empleados SET apellido = 'MODIFIED' WHERE emp_no = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, emp_no);

            st.executeQuery();
            st.close();
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    @Override
    public void deleteOne(Connection con) throws SQLException {
        Scanner sc = App.sc;

        while (true) {
            try {
                System.out.print("Nº de empleado (0 para no eliminar empleado): ");
                int emp_no = Integer.parseInt(sc.nextLine());

                if (emp_no == 0) {
                    System.out.println("No se han eliminado empleados");
                    break;
                } else {
                    if (selectOne(con, emp_no) != null) {
                        String sql = "DELETE FROM empleados WHERE emp_no = ?";
                        PreparedStatement st = con.prepareStatement(sql);
                        st.setInt(1, emp_no);

                        st.executeUpdate();
                        st.close();

                        System.out.println("Empleado eliminado con éxito!");
                        break;
                    } else {
                        System.out.println(NOT_FOUND);
                    }
                }
            } catch (Exception e) {
                System.out.println("Debes introducir un número de empleado correcto.");
            }
        }
    }

    /*
     * CUSTOM METHODS:
     */

    @Override
    public void selectXJefes(Connection con) throws SQLException {
        Scanner sc = App.sc;

        int emp_no;
        int countJefes;

        while (true) {
            try {
                System.out.print("Nº de empleado por el que empezar a buscar: ");
                emp_no = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Cúantos jefes quieres leer? (0 para todos hasta el director general): ");
                countJefes = Integer.parseInt(sc.nextLine().trim());

                if (countJefes == 0)
                    countJefes = -1;
                break;
            } catch (Exception e) {
                System.out.println("Debes introducir valores correctos!");
            }
        }

        System.out.print("Empezamos por este empleado: ");
        Empleado emp = selectOne(con, emp_no);

        do {
            if (countJefes != -1) {
                countJefes--;
            }

            System.out.print("\nEl director de " + emp.getApellido() + " es: ");
            Empleado currentDirector = selectOne(con, emp.getDir());

            if (currentDirector != null && currentDirector.getDir() == Types.NULL) {
                System.out.println("\n" + currentDirector.getApellido() + " es el director general.");
                break;
            } else if (currentDirector == null) {
                System.out.println("Él mismo!");
                break;
            }

            emp = currentDirector;
        } while (countJefes == -1 || countJefes > 0);
    }

    @Override
    public void selectGroupingBy(Connection con) throws SQLException {
        // Query to execute:
        String sql = "SELECT dept.dnombre, em1.dept_no, em1.apellido as Empleado, em1.oficio, em2.apellido AS Jefe, em1.salario, em1.comision FROM empleados em2 JOIN empleados em1 ON em2.emp_no = em1.dir JOIN departamentos dept ON em1.dept_no = dept.dept_no GROUP BY dept.dept_no";

        PreparedStatement st = con.prepareStatement(sql);

        ResultSet result = st.executeQuery();
        while (result.next()) {
            System.out.println(
                    "Nombre departamento : " + result.getString(1)
                            + " empleado: " + result.getString(2)
                            + " apellido: " + result.getString(3)
                            + " oficio: " + result.getString(4)
                            + " apellido jefe: " + result.getString(5)
                            + " salario: " + result.getString(6)
                            + " comisión: " + result.getString(7));
        }

        result.close();
        st.close();
    }

    @Override
    public void selectByDepartamento(Connection con, int dept_no) throws SQLException {
        // Query to execute:
        String sql = "SELECT * FROM empleados WHERE dept_no = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, dept_no);

        ResultSet result = st.executeQuery();

        while (result.next()) {
            Empleado emp = new Empleado(
                    result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getDate(5),
                    result.getInt(6), result.getInt(7), result.getInt(8));
            System.out.println(emp);
        }

        result.close();
        st.close();

    }

    @Override
    public void selectByApellido(Connection con, String apellido) throws SQLException {
        // Query to execute:
        String sql = "SELECT em1.emp_no, em1.apellido, em1.oficio, em2.apellido, em1.salario, em1.comision FROM empleados em1 INNER JOIN empleados em2 ON em1.dir = em2.emp_no WHERE em1.apellido LIKE ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, apellido.toUpperCase() + "%");

        ResultSet result = st.executeQuery();

        while (result.next()) {
            System.out.println("Nº: " + result.getInt(1) + ", apellido: " + result.getString(2) + ", oficio: "
                    + result.getString(3) + ", director: " + result.getString(4) + ", salario: " + result.getDouble(5)
                    + ", comisión: " + result.getDouble(6));
        }

        result.close();
        st.close();
    }

    @Override
    public void selectByOficio(Connection con) throws SQLException {
        List<String> oficios = new ArrayList<>();

        // Query to execute:
        String sql = "SELECT DISTINCT oficio FROM empleados";
        PreparedStatement st = con.prepareStatement(sql);

        ResultSet result = st.executeQuery();

        System.out.println("Selecciona uno de los oficios: ");
        while (result.next()) {
            System.out.println("\t - " + oficios.size() + ": " + result.getString(1));
            oficios.add(result.getString(1));
        }

        Scanner sc = App.sc;

        int oficio;
        while (true) {
            oficio = Integer.parseInt(sc.nextLine());
            if (oficio >= 0 && oficio < oficios.size()) {
                System.out.println("Has escogido: " + oficios.get(oficio));
                break;
            } else {
                System.out.println("Seleccionado una opción correcta.");
            }
        }

        sql = "SELECT * FROM empleados WHERE oficio = ?";
        st = con.prepareStatement(sql);
        st.setString(1, oficios.get(oficio));

        result = st.executeQuery();

        while (result.next()) {
            Empleado emp = new Empleado(
                    result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getDate(5),
                    result.getInt(6), result.getInt(7), result.getInt(8));
            System.out.println(emp);
        }

        result.close();
        st.close();
    }

    @Override
    public void alterColumns(Connection con, String operation, String column) throws SQLException {
        String sql = "";

        if (operation.toLowerCase().equals("add")) {
            sql = "ALTER TABLE empleados ADD " + column + " varchar(10)";
        } else if (operation.toLowerCase().equals("drop")) {
            sql = "ALTER TABLE empleados DROP COLUMN " + column;
        } else {
            System.out.println("Opción errónea de alter columns.");
        }

        PreparedStatement st = con.prepareStatement(sql);

        st.executeUpdate();
        st.close();
        System.out.println("Columna " + column + " " + (operation.toLowerCase().equals("add") ? "añadida" : "eliminada")
                + " con éxito!");
    }
}
