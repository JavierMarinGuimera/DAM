package app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyConnection {
    private String url;
    private String user;
    private String passwd;

    public MyConnection() {
        this.url = "jdbc:mysql://localhost/empleados";
        this.user = "root";
        this.passwd = "";
    }

    public MyConnection(String url, String user, String passwd) {
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

    public void DBData(Connection con) throws SQLException {
        DatabaseMetaData estructuraBD = con.getMetaData();
        String nom = estructuraBD.getDatabaseProductName();
        String nomDriver = estructuraBD.getDriverName();
        String url = estructuraBD.getURL();
        String usuari = estructuraBD.getUserName();

        System.out.println("INFORMACIÓ DE LA BASE DE DADES");
        System.out.println("Nom: " + nom);
        System.out.println("Nom Driver: " + nomDriver);
        System.out.println("Url: " + url);
        System.out.println("Usuari: " + usuari);

        // Obtenir les taules
        String[] tipusTaules = { "TABLE" };
        ResultSet taules = estructuraBD.getTables(null, null, null,
                tipusTaules);

        while (taules.next()) {
            System.out.println("Taula: " + taules.getString(3) + " Tipus: "
                    + taules.getString(4));
        }
    }
}
