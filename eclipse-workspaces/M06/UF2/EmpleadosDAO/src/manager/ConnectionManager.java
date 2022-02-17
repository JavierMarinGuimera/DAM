package manager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
    private String url;
    private String user;
    private String passwd;

    public ConnectionManager() {
        this.url = "jdbc:mysql://localhost/empleados";
        this.user = "root";
        this.passwd = "";
    }

    public ConnectionManager(String url, String user, String passwd) {
        this.url = url;
        this.user = user;
        this.passwd = passwd;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPasswd());
        } catch (SQLException e) {
            System.out.println("Something went wrong on getting the connection!");
            return null;
        }
    }

    public void DBData() throws SQLException {
        Connection con = this.getConnection();
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
