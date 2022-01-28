package app;

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
}
