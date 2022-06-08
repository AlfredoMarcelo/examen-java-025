package cl.aiep.java.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.aiep.java.model.Administrador;
import cl.aiep.java.model.Postulante;
import cl.aiep.java.repository.AdministradorRepository;
import cl.aiep.java.repository.PostulanteRepository;
import cl.aiep.java.seguridad.Usuario;


@Service //Para que spring encuentre esta clase para poder entregarla a otras clases
public class DetalleServicioUsuario implements UserDetailsService{

	//Unico metodo que devuelve UserDetail
	/*
	dentro del parametro se debe entregar la columna con la queremos encontrar al usuario, en
	este caso para el administrador es el nombre.
	Si no se encuentra el usuario con el nombre ingresado en el login, devolvera una excepcion
	 
	*/
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private PostulanteRepository postulanteRepository; 
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// metodo para comprobar datos del postulante
		//Optional variable local, este obliga a hacer una verificacion
		//El orden del parametro del constructor debe ser el mismo de la linea 43 y 47 al crear un nuevo Usuario
		Optional<Postulante> postulanteOpt = postulanteRepository.findByRut(username);
		if(postulanteOpt.isPresent()) {
			return new Usuario( postulanteOpt.get(), null);
		}else {
			Optional<Administrador> administradorOpt = administradorRepository.findByNombre(username);
			if( administradorOpt.isPresent()) {
				return new Usuario( null, administradorOpt.get());				
			}
		}
		
		//mensaje que se envia en caso de no encontrar a un postulante
		throw new UsernameNotFoundException("Usuario no encontrado en los registros !!!");
	}
}
