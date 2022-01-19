package classes;

public class Departamento {
	private String nombre;
	private String asignatura;

	public Departamento(String nombre, String asignatura) {
		super();
		this.nombre = nombre;
		this.asignatura = asignatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
}
