package cl.aiep.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.aiep.java.model.Postulante;
import cl.aiep.java.repository.PostulanteRepository;

@Controller
@RequestMapping("/postulante")
public class PostulanteController {

	@Autowired
	PostulanteRepository postulanteRepository;
	
	@GetMapping("/registro")
	public String registro(Postulante postulante) {
		
		return "postulante/registro";
	}
	
	@PostMapping("/registrar")
	public String registrarPostulante(Postulante postulante) {
		postulanteRepository.saveAndFlush(postulante);
		return "redirect:/";
	}
	
	
	
}	
