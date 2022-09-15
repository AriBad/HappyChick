package happyChick.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import happyChick.model.Recap;
import happyChick.model.jsonview.JsonViews;
import happyChick.service.PoulaillerService;
import happyChick.service.PouleService;
import happyChick.service.RecapService;

@RestController
@RequestMapping("/happyChick/api/recap")
@CrossOrigin("*")
public class RecapRestController {
	
	@Autowired
	private RecapService recapService;

	
	
	@JsonView(JsonViews.Base.class)
	@GetMapping("")
	public List<Recap> getAll() {
		return recapService.getAll();
	}
	
	@JsonView(JsonViews.Base.class)
	@GetMapping("/poulailler/{id}")
	public List<Recap> getAllByPoulailler(@PathVariable Integer id) {
		return recapService.getAllByPoulailler(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	@JsonView(JsonViews.Base.class)
	public Recap create(@RequestBody Recap recap) {
		return recapService.create(recap);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@JsonView(JsonViews.Base.class)
	public void delete(@PathVariable Integer id) {
		recapService.delete(id);
	}
	
	@JsonView(JsonViews.Base.class)
	@DeleteMapping("/poulailler/{id}")
	public void deleteByPoulailler(@PathVariable Integer id) {
		recapService.deleteByPoulailler(id);
	
}}
