package connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionJDBC {
    private static String url;
    private static String user;
    private static String passwd;

    /**
     * Simple constructor to modify the class attributtes.
     * 
     */
    public DBConnectionJDBC(String url, String user, String passwd) {
        DBConnectionJDBC.url = url;
        DBConnectionJDBC.user = user;
        DBConnectionJDBC.passwd = passwd;
    }

    static {
        url = "jdbc:mysql://localhost/bdautors";
        user = "root";
        passwd = "";
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            System.out.println("Something went wrong on getting the connection!");
            return null;
        }
    }

    /**
     * This method will give you the metadata of the current database.
     * 
     * @throws SQLException
     */
    public static void DBData() throws SQLException {
        Connection con = getConnection();
        DatabaseMetaData estructuraBD = con.getMetaData();
        con.close();
        String nom = estructuraBD.getDatabaseProductName();
        String nomDriver = estructuraBD.getDriverName();
        String url = estructuraBD.getURL();
        String usuari = estructuraBD.getUserName();

        System.out.println("INFORMACIÓ DE LA BASE DE DADES");
        System.out.println("Nombre: " + nom);
        System.out.println("Nombre Driver: " + nomDriver);
        System.out.println("Url: " + url);
        System.out.println("Usuario: " + usuari);

        // Obtenir les taules
        String[] tipusTaules = { "TABLES" };
        ResultSet taules = estructuraBD.getTables(null, null, null,
                tipusTaules);

        while (taules.next()) {
            System.out.println("Taula: " + taules.getString(3) + " Tipus: "
                    + taules.getString(4));
        }
    }
}
