package happyChick.restcontroller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import happyChick.dao.IDAOUtilisateur;
import happyChick.exception.PoulaillerException;
import happyChick.exception.UtilisateurException;
import happyChick.model.Poulailler;
import happyChick.model.Utilisateur;
import happyChick.model.dto.LoginDTO;
import happyChick.model.jsonview.JsonViews;
import happyChick.service.UtilisateurService;

@RestController
@RequestMapping("/happyChick/api/utilisateur")
@CrossOrigin("*")
public class UtilisateurRestController {
	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	IDAOUtilisateur utilisateurRepo;
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Utilisateur getById(@PathVariable Integer id) {
		return utilisateurService.getById(id);
	}
	
	@PostMapping("")
	@JsonView(JsonViews.Base.class)
	public Utilisateur create(@Valid @RequestBody Utilisateur utilisateur, BindingResult br) {
		if (br.hasErrors()) {
			throw new UtilisateurException();
		}
		return utilisateurService.create(utilisateur);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		utilisateurService.deleteById(id);
	}
	
	@PostMapping("/login")
	@JsonView(JsonViews.Base.class)
	public Utilisateur login(@RequestBody LoginDTO loginDTO) {
		Optional<Utilisateur> optionalUtilisateur = utilisateurRepo.findByLoginAndMotDePasse(loginDTO.getIdentifiant(), loginDTO.getMotDePasse());
		
		if(!optionalUtilisateur.isPresent()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Utilisateur inconnu");
		}
		
		return optionalUtilisateur.get();
	}
}
