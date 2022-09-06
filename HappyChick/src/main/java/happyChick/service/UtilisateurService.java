package happyChick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happyChick.dao.IDAOPoulailler;
import happyChick.dao.IDAOPoule;
import happyChick.dao.IDAOUtilisateur;
import happyChick.exception.UtilisateurException;
import happyChick.model.Utilisateur;

@Service
public class UtilisateurService {
	
	@Autowired
	private IDAOPoulailler poulaillerRepo;
	
	@Autowired
	private IDAOUtilisateur userRepo;

	public Utilisateur getById(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> {
			throw new UtilisateurException("id Utilisateur inconnu");
		});
	}

	public List<Utilisateur> getAll() {

		return userRepo.findAll();
	}
	
	public Utilisateur create(Utilisateur utilisateur) {
		if (utilisateur.getLogin() == null || utilisateur.getLogin().isEmpty()) {
			throw new UtilisateurException("Login obligatoire");
		}
		return userRepo.save(utilisateur);
	}
	
	public Utilisateur update(Utilisateur utilisateur) {
		if (utilisateur.getLogin() == null || utilisateur.getLogin().isEmpty()) {
			throw new UtilisateurException("Login obligatoire");
		}
		if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().isEmpty()) {
			throw new UtilisateurException("Mot de passe obligatoire");
		}
		Utilisateur userEnBase = getById(utilisateur.getId());
		userEnBase.setLogin(utilisateur.getLogin());
		userEnBase.setMotDePasse(utilisateur.getMotDePasse());
		return userRepo.save(userEnBase);
	}

	public void delete(Utilisateur utilisateur) {
		//poulaillerRepo.deleteByUser(user);
		userRepo.delete(utilisateur);
	}

	public void deleteById(Integer id) {
		delete(getById(id));
	}
}
