package cl.aiep.java.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping("/registrar/{id}")
	public String registrarPostulacion(@PathVariable("id") Curso curso, Authentication autenticacion, Model modelo) {
		
		Usuario usuario = (Usuario) autenticacion.getPrincipal();
		if(usuario.getPostulante() != null) {	
			Postulante postulante = usuario.getPostulante();
			List<Postulacion> postulaciones = postulacionRepository.findByPostulante(usuario.getPostulante());
			
			if(postulaciones.size() > 0) {
				modelo.addAttribute("error", "Ya estas inscrito en un curso, solo puedes postular a 1");
				return "postulante/panel";
			}
				Postulacion postulacion = Postulacion.builder()
						.postulante(postulante)
						.curso(curso)
						.fechaRegistro(LocalDateTime.now())
						.build();
				//Disminuir cupos del curso
				int cuposDisponibles = curso.getCuposDisponibles() - 1;
				curso.setCuposDisponibles(cuposDisponibles);
				
				cursoRepository.save(curso);
				postulacionRepository.save(postulacion);
				return "redirect:/postulante/panel";
		}else {
			return"error/noencontrada";
		}
	}
}
