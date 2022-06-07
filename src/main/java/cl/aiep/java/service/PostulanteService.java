package cl.aiep.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.aiep.java.model.Postulante;
import cl.aiep.java.repository.PostulanteRepository;

@Service
public class PostulanteService {

	@Autowired
	private PostulanteRepository postulanteRepository;
	
	@Autowired
	private PasswordEncoder encriptarContrasena;
	
	public Postulante crearPostulante(Postulante postulante) {
		String contrasenaEncriptada = encriptarContrasena.encode(
				postulante.getContrasena()
				);
				postulante.setContrasena(contrasenaEncriptada);
				return postulanteRepository.save(postulante);
	}
	
	
	public long contarPostulantes() {
		return postulanteRepository.count();
	}
	
	
}
