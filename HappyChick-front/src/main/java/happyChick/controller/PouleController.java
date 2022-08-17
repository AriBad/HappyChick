package happyChick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poules")
public class PouleController {
	@GetMapping("")
	public String home() {
		return "listePoules";
	}
	
	
}
