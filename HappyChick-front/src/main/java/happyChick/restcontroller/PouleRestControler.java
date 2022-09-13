package happyChick.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import happyChick.exception.PouleException;
import happyChick.model.CauseMort;
import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.jsonview.JsonViews;
import happyChick.service.PoulaillerService;
import happyChick.service.PouleService;

@RestController
@RequestMapping("/api/poule")
public class PouleRestControler {

	@Autowired
	private PouleService pouleService;

	@Autowired
	private PoulaillerService poulaillerService;

	@JsonView(JsonViews.Base.class)
	@GetMapping("")
	public List<Poule> getAll() {
		return pouleService.getAll();
	}
	
	@JsonView(JsonViews.pouleWithPoulailler.class)
	@GetMapping("/poulailler")
	public List<Poule> getAllWihPoulailler() {
		return pouleService.getAll();
	}
	
	
	@JsonView(JsonViews.Base.class)
	@GetMapping("/{id}")
	public Poule getById(@PathVariable Integer id) {
		return pouleService	.getById(id);
	}
	
	@JsonView(JsonViews.pouleWithPoulailler.class)
	@GetMapping("/{id}/poulailler")
	public Poule getByIdWithPoulailler(@PathVariable Integer id) {
		return pouleService	.getById(id);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	@JsonView(JsonViews.Base.class)
	public Poule create(@RequestBody Poule poule) {
		return pouleService.create(poule);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Poule update(@Valid @PathVariable Integer id, @RequestBody Poule poule) {
		try {
			pouleService.getById(id);
		} catch (PouleException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return pouleService.update(poule);
	}

	@PatchMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Poule partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable Integer id) {
		Poule poule = null;
		try {
			poule = pouleService.getById(id);
		} catch (PouleException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		final Poule p = poule;
		fields.forEach((k, v) -> {
			if (k.equals("poulailler")) {
				Map<String, Object> map = (Map<String, Object>) v;
				p.setPoulailler(poulaillerService.getById((Integer) map.get("id")));
			} else {
				Field field = ReflectionUtils.findField(Poule.class, k);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, p, v);
			}
		});
		return pouleService.update(poule);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		pouleService.deleteById(id);
	}

}
