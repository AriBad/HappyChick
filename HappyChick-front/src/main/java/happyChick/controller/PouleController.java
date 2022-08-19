package happyChick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import happyChick.service.PouleService;

@Controller
@RequestMapping("/poules")
public class PouleController {
	@Autowired
	PouleService pouleService;
	
	@GetMapping("")
	public String home() {
		return "listePoules";
	}
	
	
}
