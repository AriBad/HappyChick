package happyChick.restcontroller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import happyChick.exception.PoulaillerException;
import happyChick.model.Activite;
import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.jsonview.JsonViews;
import happyChick.service.PoulaillerService;
import happyChick.service.PouleService;

@RestController
@RequestMapping("/api/poulailler")
public class PoulaillerRestController {
	@Autowired
	PoulaillerService poulaillerService;
	
	@Autowired
	PouleService pouleService;
	
	@GetMapping("")
	@JsonView(JsonViews.Base.class)
	public List<Poulailler> getAll() {
		return poulaillerService.getAll();
	}
	
	@GetMapping("/poule")
	@JsonView(JsonViews.PoulaillerWithPoules.class)
	public List<Poulailler> getAllWithPoules() {
		return poulaillerService.getAllWithPoules();
	}
	
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Poulailler getById(@PathVariable Integer id) {
		return poulaillerService.getById(id);
	}

	@GetMapping("/{id}/poule")
	@JsonView(JsonViews.PoulaillerWithPoules.class)
	public Poulailler getByIdWithPoules(@PathVariable Integer id) {
		return poulaillerService.getByIdWithPoules(id);
	}
	
	@PostMapping("")
	@JsonView(JsonViews.Base.class)
	public Poulailler create(@Valid @RequestBody Poulailler poulailler, BindingResult br) {
		if (br.hasErrors()) {
			throw new PoulaillerException();
		}
		return poulaillerService.create(poulailler);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Poulailler update(@Valid @RequestBody Poulailler poulailler, BindingResult br, @PathVariable Integer id) {
		if (br.hasErrors()) {
			throw new PoulaillerException();
		}
		Poulailler poulaillerEnBase = poulaillerService.getById(id);
		if (poulaillerEnBase != null) {
			poulailler.setId(id);
			return poulaillerService.update(poulailler);
		}
		return null;
	}
	
	@PostMapping("/{id}/saison")
	@JsonView(JsonViews.PoulaillerWithPoules.class) //peut-être que optionnal fait exploser
	public Poulailler poulaillerStep(@PathVariable Integer id,@Param("nourriture") Integer nourriture, @Param("activite") String activite, 
			@Param("securite") Optional<String> securite, 
			@Param("taille") Optional<String> taille, @RequestBody HashMap<Integer, Integer> mapCouveuse) {
		Poulailler poulailler = poulaillerService.getById(id);
		Activite a = Activite.valueOf(activite);
		boolean securiser = false;
		boolean agrandir = false;
		if( securite.isPresent() ) { //peut exploser
			securiser = true;
		}
		if (taille.isPresent()) {
			agrandir=true;
		}
		
		HashMap<Poule, Integer> mapCouveuseTemp = new HashMap();
		for (Map.Entry<Integer, Integer> entry : mapCouveuse.entrySet()) {
			mapCouveuseTemp.put(pouleService.getById(entry.getKey()), entry.getValue());
	    }
		
		poulaillerService.step(poulailler, a, nourriture, mapCouveuseTemp, agrandir, securiser);
		
		return poulailler;		
	}

	@PatchMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Poulailler partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable Integer id) {
		Poulailler poulailler= poulaillerService.getById(id);
		fields.forEach((k, v) -> {
			if (k.equals("listePoules")) {
				//TODO
			} else {
				Field field = ReflectionUtils.findField(Poulailler.class, k);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, poulailler, v); // ne fonctionne que pour les types standards
			}
		});
		return poulaillerService.update(poulailler);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		poulaillerService.deleteById(id);
	}
}
