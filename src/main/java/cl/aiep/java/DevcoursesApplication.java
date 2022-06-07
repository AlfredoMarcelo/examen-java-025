package cl.aiep.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.aiep.java.model.Administrador;
import cl.aiep.java.service.AdministradorService;
import cl.aiep.java.service.PostulanteService;

@SpringBootApplication
public class DevcoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevcoursesApplication.class, args);
	}
	
	//configuracion inicial para crear usuarios
	@Bean
	public CommandLineRunner datosIniciales(
			PostulanteService postulanteService
			, AdministradorService administradorService
			) {
		return args -> {
			if( administradorService.contarAdministradores() == 0) {
				Administrador administrador = Administrador.builder()
												.nombre("admin")
												.contrasena("123")
												.build();
				administradorService.crearAdministrador(administrador);
			}
		};
	}
	

}
