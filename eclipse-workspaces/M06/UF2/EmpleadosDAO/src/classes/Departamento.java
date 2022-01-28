package classes;

public class Departamento {

    private int id;
    private String name;
    private String ioc;

    public Departamento(int id, String name, String ioc) {
        this.id = id;
        this.name = name;
        this.ioc = ioc;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIoc() {
        return this.ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    @Override
    public String toString() {
        return "Departamento: {Nº: " + this.id + ", nombre: " + this.name + ", Ioc: " + this.ioc + "}.";
    }

}
