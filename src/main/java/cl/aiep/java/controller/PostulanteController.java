package cl.aiep.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.aiep.java.model.Curso;
import cl.aiep.java.model.Postulante;
import cl.aiep.java.repository.CursoRepository;
import cl.aiep.java.seguridad.Usuario;
import cl.aiep.java.service.PostulanteService;

@Controller
@RequestMapping("/postulante")
public class PostulanteController {


	
	@Autowired
	private PostulanteService postulanteService;
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping("/registro")
	public String registro(Postulante postulante) {
		return "postulante/registro";
	}
	
	@PostMapping("/registrar")
	public String registrarPostulante(Postulante postulante) {
		postulanteService.crearPostulante(postulante);
		return "redirect:/";
	}
	
	@GetMapping("/panel")
	public String panelControl(Curso curso, Model modelo, Authentication usuarioAutenticado) {
		Usuario usuario = (Usuario) usuarioAutenticado.getPrincipal();
		if( usuario.getPostulante() != null) {
			List<Curso> cursos = cursoRepository.findAll();
			modelo.addAttribute("curso", cursos);
			return "postulante/panel";
		}else {			
			return "error/noencontrada";
		}
	}
	
}	
