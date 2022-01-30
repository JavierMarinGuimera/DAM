package classes;

import java.sql.Date;

public class Empleado {
    private int emp_no;
    private String apellido;
    private String oficio;
    private int dir;
    private Date fecha_alt;
    private double salario;
    private double comision;
    private int dept_no;

    public Empleado(int emp_no, String apellido, String oficio, int dir, Date fecha_alt, double salario,
            double comision, int dept_no) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dept_no = dept_no;
    }

    public int getEmp_no() {
        return this.emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return this.oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public int getDir() {
        return this.dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Date getFecha_alt() {
        return this.fecha_alt;
    }

    public void setFecha_alt(Date fecha_alt) {
        this.fecha_alt = fecha_alt;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getComision() {
        return this.comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getDept_no() {
        return this.dept_no;
    }

    public void setDepartamento(int dept_no) {
        this.dept_no = dept_no;
    }

    @Override
    public String toString() {
        return "Empleado: {Nº:" + this.emp_no + ", apellidos: " + this.apellido + ", oficio: " + this.oficio
                + ", director: " + this.dir + ", fecha alta: " + this.fecha_alt + ", salario: " + this.salario
                + ", comisión: " + this.comision + ", departamento: " + this.dept_no + "}";
    }

}
