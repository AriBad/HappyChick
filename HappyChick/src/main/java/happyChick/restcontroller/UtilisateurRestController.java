package happyChick.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import happyChick.exception.PoulaillerException;
import happyChick.exception.UtilisateurException;
import happyChick.model.Poulailler;
import happyChick.model.Utilisateur;
import happyChick.model.jsonview.JsonViews;
import happyChick.service.UtilisateurService;

@RestController
@RequestMapping("/happyChick/api/utilisateur")
public class UtilisateurRestController {
	@Autowired
	UtilisateurService utilisateurService;
	
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
}
