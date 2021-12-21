package cat.marianao.daw2.m07.uf3.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userId;

	@NotNull
	@Size(max = 30)
	@Column(name = "username")
	private String username;

	@NotNull
	@Size(max = 30)
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(max = 30)
	@Column(name = "email")
	private String email;

	@NotNull
	@Size(max = 30, min = 5)
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "rank")
	private Integer rank;

	@NotNull
	@Column(name = "active")
	private Boolean active;

	@NotNull
	@Column(name = "created_on")
	private Timestamp createdOn;

	public User() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
}
