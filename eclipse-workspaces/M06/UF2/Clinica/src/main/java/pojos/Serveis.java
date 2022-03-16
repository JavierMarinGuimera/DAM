package pojos;
// Generated 14 mar. 2022 18:50:50 by Hibernate Tools 5.6.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Serveis generated by hbm2java
 */
public class Serveis implements java.io.Serializable {

	private int codi;
	private String descripcio;
	private Set<Assistencies> assistencieses = new HashSet<>();

	public Serveis() {
	}

	public Serveis(int codi, String descripcio) {
		this.codi = codi;
		this.descripcio = descripcio;
	}

	public Serveis(int codi, String descripcio, Set<Assistencies> assistencieses) {
		this.codi = codi;
		this.descripcio = descripcio;
		this.assistencieses = assistencieses;
	}

	public int getCodi() {
		return this.codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public String getDescripcio() {
		return this.descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public Set<Assistencies> getAssistencieses() {
		return this.assistencieses;
	}

	public void setAssistencieses(Set<Assistencies> assistencieses) {
		this.assistencieses = assistencieses;
	}

}