package cl.aiep.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.aiep.java.model.Administrador;
import cl.aiep.java.repository.AdministradorRepository;

@Service //define la clase como service
public class AdministradorService {

	//se debe inyectar el repository de la entidad y el la configuracion para encriptar la contrasena
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private PasswordEncoder encriptarContrasena;
	
	//el metodo devolvera un objeto del tipo Administrador
	public Administrador crearAdministrador(Administrador administrador) {
		//creara la encriptacion de la contrasena plana
		String contrasenaEncriptada = encriptarContrasena.encode(
					administrador.getContrasena()
				);
		//persistira la contrasena encriptada
		administrador.setContrasena(contrasenaEncriptada);
		return administradorRepository.save(administrador);
	}
	
	public long contarAdministradores() {
		return administradorRepository.count();
	}
	
	
	
}
