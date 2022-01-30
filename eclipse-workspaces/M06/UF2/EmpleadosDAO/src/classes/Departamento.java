package classes;

public class Departamento {

    private int dept_no;
    private String name;
    private String ioc;

    public Departamento(int dept_no, String name, String ioc) {
        this.dept_no = dept_no;
        this.name = name;
        this.ioc = ioc;
    }

    public int getDept_no() {
        return this.dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
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
        return "Departamento: {Nº: " + this.dept_no + ", nombre: " + this.name + ", Ioc: " + this.ioc + "}.";
    }

}
