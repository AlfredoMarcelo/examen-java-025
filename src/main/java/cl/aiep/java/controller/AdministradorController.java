package cl.aiep.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.aiep.java.model.Curso;
import cl.aiep.java.repository.CursoRepository;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@Autowired
	private CursoRepository cursoRepository; 

	@GetMapping("/panel")
	public String panelControl(Curso curso, Model modelo) {
		List<Curso> cursos = cursoRepository.findAll();
		modelo.addAttribute("cursos", cursos);
		return "administrador/panel";
	}
}
