package classes;

import java.util.HashMap;

public class Contacto {
	private String id;
	private String nombre;
	private String apellidos;
	private Departamento departamento;
	private HashMap<String, String> telefonos;
	private HashMap<String, String> correos;

	public Contacto(String id, String nombre, String apellidos, Departamento depart, HashMap<String, String> tel,
			HashMap<String, String> correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.departamento = depart;
		this.telefonos = tel;
		this.correos = correo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepart(Departamento depart) {
		this.departamento = depart;
	}

	public HashMap<String, String> getTelefonos() {
		return telefonos;
	}

	public void setTel(HashMap<String, String> tel) {
		this.telefonos = tel;
	}

	public HashMap<String, String> getCorreos() {
		return correos;
	}

	public void setCorreo(HashMap<String, String> correo) {
		this.correos = correo;
	}

}
