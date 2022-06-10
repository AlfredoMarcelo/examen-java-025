package cl.aiep.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.aiep.java.model.Curso;
import cl.aiep.java.model.Postulacion;
import cl.aiep.java.model.Postulante;
import cl.aiep.java.repository.CursoRepository;
import cl.aiep.java.repository.PostulacionRepository;
import cl.aiep.java.seguridad.Usuario;

@Controller
@RequestMapping("/postulacion")
public class PostulacionController {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private PostulacionRepository postulacionRepository;
	
	@PostMapping("/registar/{id}")
	public String registrarPostulacion(@PathVariable("id") Curso curso, Authentication autenticacion, Model modelo) {
		
		int cuposDisponibles = curso.getCupos() - 1;
		curso.setCuposDisponibles(cuposDisponibles);
		cursoRepository.save(curso);
		
		Usuario usuario = (Usuario) autenticacion.getPrincipal();
		Postulante postulante = usuario.getPostulante();
		Postulacion postulacion = Postulacion.builder()
									.postulante(postulante)
									.curso(curso)
									.build();

		postulacionRepository.save(postulacion);
				return "/";
	}
	
	
	
}
