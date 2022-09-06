package happyChick.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import happyChick.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur,Integer>{

	public Utilisateur findByMotDePasseAndLogin(String login,String password);

	
	public Optional<Utilisateur> findByLogin(String username);
	
}
