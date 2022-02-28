package pojos;
// Generated 21 feb. 2022 17:59:07 by Hibernate Tools 5.6.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Assignatura generated by hbm2java
 */
public class Assignatura implements java.io.Serializable {

	private String codAsig;
	private String nom;
	private Set<Alumne> alumnes = new HashSet<>();

	public Assignatura() {
	}

	public Assignatura(String codAsig) {
		this.codAsig = codAsig;
	}

	public Assignatura(String codAsig, String nom, Set<Alumne> alumnes) {
		this.codAsig = codAsig;
		this.nom = nom;
		this.alumnes = alumnes;
	}

	public String getCodAsig() {
		return this.codAsig;
	}

	public void setCodAsig(String codAsig) {
		this.codAsig = codAsig;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Alumne> getAlumnes() {
		return this.alumnes;
	}

	public void setAlumnes(Set<Alumne> alumnes) {
		this.alumnes = alumnes;
	}

}
