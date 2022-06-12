package cl.aiep.java.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.aiep.java.model.Curso;
import cl.aiep.java.model.Postulacion;
import cl.aiep.java.model.Postulante;
import cl.aiep.java.repository.PostulacionRepository;
import cl.aiep.java.repository.PostulanteRepository;
import cl.aiep.java.seguridad.Usuario;
import cl.aiep.java.service.PostulanteService;

@Controller
@RequestMapping("/postulante")
public class PostulanteController {


	
	@Autowired
	private PostulanteService postulanteService;
	
	@Autowired
	private PostulanteRepository postulanteRepository;
	
	@Autowired
	private PostulacionRepository postulacionRepository;
	
	@GetMapping("/registro")
	public String registro(Postulante postulante) {
		return "postulante/registro";
	}
	
	@PostMapping("/registrar")
	public String registrarPostulante(@Valid Postulante postulante, BindingResult mensajeValidacion) {
		if(mensajeValidacion.hasErrors()) {
			return "postulante/registro";
		}
		
		postulanteService.crearPostulante(postulante);
		return "redirect:/";
	}
	
	@GetMapping("/panel")
	public String panelControl(Curso curso, Model modelo, Authentication usuarioAutenticado) {
		Usuario usuario = (Usuario) usuarioAutenticado.getPrincipal();
		if( usuario.getPostulante() != null) {
			List<Postulacion> postulaciones = postulacionRepository.findByPostulante(usuario.getPostulante());
			modelo.addAttribute("postulaciones", postulaciones);
			return "postulante/panel";
		}else {			
			return "error/noencontrada";
		}
	}
	
}	
