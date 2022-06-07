package cl.aiep.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
	
	@GetMapping("/")
	public String inicio() {
		return "index";
	}
	
	@GetMapping("/acceso")
	public String iniciarSesion() {
		return "acceso";
	}
}
