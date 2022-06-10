package cl.aiep.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.aiep.java.model.Curso;
import cl.aiep.java.repository.CursoRepository;

@Controller
public class InicioController {
	
	
	@Autowired
	private CursoRepository cursoRepository;
	
	
	@GetMapping("/")
	public String inicio(Curso curso, Model modelo) {
		List<Curso> cursos = cursoRepository.findAll();
		modelo.addAttribute("cursos", cursos);
		return "index";
	}
	
	@GetMapping("/acceso")
	public String iniciarSesion() {
		return "acceso";
	}
	
	@GetMapping("/nosotros")
	public String presentacion() {
		return "/presentacion";
	}
	
}
