package cl.aiep.java.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import cl.aiep.java.model.Administrador;
import cl.aiep.java.model.Curso;
import cl.aiep.java.repository.AdministradorRepository;
import cl.aiep.java.repository.CursoRepository;
import cl.aiep.java.seguridad.Usuario;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private AdministradorRepository administradorRepository; 

	@GetMapping("/formulario")
	public String formularioRegistro(Curso curso) {
		return "curso/formulario";
	}
	

	@PostMapping("/registrar")
	public String registrarCurso(
			@Valid Curso curso
			, BindingResult mensajeValidacion
			, @RequestParam("archivo") MultipartFile archivo
			, Authentication usuarioAutenticado ) {
		
		//Consultar por las validaciones de campo y como utilizar el @NotBlank
		if( mensajeValidacion.hasErrors()) {
			return "curso/formulario";
		}
		try {
			Usuario usuario 			= (Usuario) usuarioAutenticado.getPrincipal();
			String tipoArchivo			= archivo.getContentType();
			byte[] contenidoArchivo 	= archivo.getBytes();
			Curso cursoObjeto 			= new Curso();
			
			Administrador administrador = administradorRepository.findById(usuario.getAdministrador().getId()).get();
			curso.setAdministrador(administrador);
			
			//sea una modificacion de curso
			if(curso.getId() != null && curso.getId() > 0) {
				cursoObjeto = cursoRepository.findById(curso.getId()).get();
			}else {
				//en caso de que se este creando un curso
				cursoObjeto.setCuposDisponibles(curso.getCupos());			
			}
			
			cursoObjeto.setDatos(contenidoArchivo);
			cursoObjeto.setTipo(tipoArchivo);
			cursoObjeto.setNombre(curso.getNombre());
			cursoObjeto.setDescripcion(curso.getDescripcion());
			cursoObjeto.setContenido(curso.getContenido());
			cursoObjeto.setFechaInicio(curso.getFechaInicio());
			cursoObjeto.setFechaTermino(curso.getFechaTermino());
			cursoObjeto.setCupos(curso.getCupos());
			cursoObjeto.setAdministrador(administrador);
			cursoRepository.save(cursoObjeto);
		}catch (Exception e) {
			e.printStackTrace();
			return "curso/formulario";
		}
		return "redirect:/administrador/panel";
	}
	
	
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminarCurso(@PathVariable Long id) {
		cursoRepository.deleteById(id);
		return "redirect:/administrador/panel";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCurso(@PathVariable(name = "id") Curso curso, Model modelo ) {
		modelo.addAttribute("curso", curso);
		return "curso/formulario";
	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> mostrarImagen(@PathVariable("id")Curso curso){
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline")
				.contentType(MediaType.valueOf(curso.getTipo()))
				.body(curso.getDatos())
				;
	}
	
	@GetMapping("/ver/{id}")
	public String verCurso(@PathVariable(name = "id")Curso curso, Model modelo) {
		modelo.addAttribute("curso", curso);
		return "curso/descripcion";
	}
	
}
