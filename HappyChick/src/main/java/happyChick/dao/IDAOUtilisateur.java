package happyChick.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import happyChick.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur,Integer>{

	public Optional<Utilisateur>findByLoginAndMotDePasse(String login,String password);

	
	public Optional<Utilisateur> findByLogin(String username);
	
}
