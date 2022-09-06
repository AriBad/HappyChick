package happyChick.model;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("Utilisateur")
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(length = 20, nullable = false, unique = true)
	protected String login;
	@Column(length = 120, nullable = false)
	protected String motDePasse;
	
	@NotNull
	@Column(nullable = false)
	private boolean activated = true;

	@OneToMany(mappedBy = "utilisateur")
	private List<Poulailler> poulaillers;
	
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "utilisateur_roles", joinColumns = @JoinColumn(name = "utilisateur_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
	
	public Utilisateur(){}

	public Utilisateur(String login, String motDePasse) {
		this.login = login;
		this.motDePasse = motDePasse;

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

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public List<Poulailler> getPoulaillers() {
		return poulaillers;
	}

	public void setPoulaillers(List<Poulailler> poulaillers) {
		this.poulaillers = poulaillers;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
        this.roles.add(role);
    }

    public void remove(Role role) {
        this.roles.remove(role);
    }
    
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", motDePasse=" + motDePasse + "]";
	}



}