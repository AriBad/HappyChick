package happyChick.model;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(length = 20, nullable = false, unique = true)
	protected String login;
	@Column(length = 120, nullable = false)
	protected String password;
	

	@OneToMany(mappedBy = "user")
	private List<Poulailler> poulaillers;
	
	public User(){}

	public User(String login, String password) {
		this.login = login;
		this.password = password;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Poulailler> getPoulaillers() {
		return poulaillers;
	}

	public void setPoulaillers(List<Poulailler> poulaillers) {
		this.poulaillers = poulaillers;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
	}

}