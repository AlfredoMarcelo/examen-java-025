package cl.aiep.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.aiep.java.model.Administrador;
import cl.aiep.java.model.Curso;
import cl.aiep.java.model.Postulante;
import cl.aiep.java.repository.CursoRepository;
import cl.aiep.java.service.AdministradorService;
import cl.aiep.java.service.PostulanteService;

@SpringBootApplication
public class DevcoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevcoursesApplication.class, args);
	}
	
	//configuracion inicial para crear usuarios subir imagen  .imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/java.jpg")))
	@Bean
	public CommandLineRunner datosIniciales(
			PostulanteService postulanteService
			, AdministradorService administradorService
			, CursoRepository cursoRepository
			) {
		return args -> {
			if( administradorService.contarAdministradores() == 0) {
				Administrador administrador = Administrador.builder()
												.nombre("admin")
												.contrasena("1234")
												.build();
				administradorService.crearAdministrador(administrador);
				
				Postulante postulante = Postulante.builder()
											.nombre("alfredo")
											.rut("18314509-6")
											.email("alfredo@gmail.com")
											.contrasena("4321")
											.telefono("982154185")
											.direccion("Puelle 21")
											.region("XV")
											.build();
				postulanteService.crearPostulante(postulante);
				
				
				Path path = Paths.get("src/main/resources/static/images/javascript.png");
				String content = Files.probeContentType(path);
				Curso curso = Curso.builder()
							.nombre("Javascript")
							.descripcion("Conoce los conceptos clave del lenguaje de programación que se está comiendo al mundo. Aprende qué es una variable, una función, un objeto y dónde se guardan esos valores. Descubre qué es Scope y cómo se utilizan los loops. Obtén las herramientas para saber cómo tomar decisiones y validar acciones. En este curso, darás el primer paso para empezar tu carrera como desarrollador.")
							.contenido("-Javascript Esmac-6")
							.fechaInicio(LocalDate.of(2022, 01, 01))
							.fechaTermino(LocalDate.of(2022, 02, 02))
							.cupos(30)
							.cuposDisponibles(30)
							.datos(Files.readAllBytes(path))
							.tipo(content)
							.administrador(administrador)
							.build();
				
				Path pathDos = Paths.get("src/main/resources/static/images/java.png");
				String contentDos = Files.probeContentType(pathDos);
				Curso cursoDos = Curso.builder()
						.nombre("Java")
						.descripcion("En informática, pocas tecnologías han tenido el impacto de Java. Gracias a sus características innovadoras, Java es uno de los lenguajes informáticos más importantes del mundo, es una fuerza que revolucionó la programación y, en el proceso, cambió el mundo. Este curso de conceptos fundamentales de Java se ha diseñado para que los estudiantes con poca o ninguna experiencia en programación conozcan la programación mediante el lenguaje de programación Java. Se enseña los pasos necesarios para crear programas simples.")
						.contenido("Este curso está diseñado para ayudarlo a hacer el uso más efectivo de Java. Discute temas avanzados, que incluyen creación de objetos, concurrencia, serialización, reflection y muchos más. ¡Te guiará en tu viaje hacia el dominio de Java!")
						.fechaInicio(LocalDate.of(2022, 03, 03))
						.fechaTermino(LocalDate.of(2022, 04, 04))
						.cupos(30)
						.cuposDisponibles(30)
						.datos(Files.readAllBytes(pathDos))
						.tipo(contentDos)
						.administrador(administrador)
						.build();
				
				Path pathTres = Paths.get("src/main/resources/static/images/ruby.png");
				String contentTres = Files.probeContentType(pathTres);
				Curso cursoTres = Curso.builder()
						.nombre("Ruby")
						.descripcion("Bienvenidos a este nuevo curso de programación en Ruby completamente actualizado.\n"
								+ "\n"
								+ "Este curso diseñado para todo aquel que quiera aprender en este magnifico lenguaje tan popular en el mundo. \n"
								+ "\n"
								+ "Conocimientos previos en programación son útiles pero no requeridos, ya que cubriremos todo paso por paso.\n"
								+ "")
						.contenido("En este curso aprenderemos\n"
								+ "Variables\n"
								+ "Ciclos\n"
								+ "Decisiones\n"
								+ "Arreglos\n"
								+ "Hashes\n"
								+ "Programación Orientada a Objetos\n"
								+ "Bloques en Ruby\n"
								+ "Desarrollo Web con Sinatra\n"
								+ "Desarrollo Web con Rails\n"
								+ "Y mucho más...\n"
								+ "Este es un curso completamente práctico y enfocado a proyectos. Lo que quiere decir que no serán un montón de diapositivas aburridas sino que aprenderemos de la mejor forma: Programando Aplicaciones.\n"
								+ "\n"
								+ "Te espero!!!")
						.fechaInicio(LocalDate.of(2022, 05, 05))
						.fechaTermino(LocalDate.of(2022, 06, 06))
						.cupos(30)
						.cuposDisponibles(30)
						.datos(Files.readAllBytes(pathTres))
						.tipo(contentTres)
						.administrador(administrador)
						.build();
				
				Path pathCuatro = Paths.get("src/main/resources/static/images/python.png");
				String contentCuatro = Files.probeContentType(pathCuatro);
				Curso cursoCuatro = Curso.builder()
						.nombre("Python")
						.descripcion("Conoce los conceptos clave del lenguaje de programación que se está comiendo al mundo. Aprende qué es una variable, una función, un objeto y dónde se guardan esos valores. Descubre qué es Scope y cómo se utilizan los loops. Obtén las herramientas para saber cómo tomar decisiones y validar acciones. En este curso, darás el primer paso para empezar tu carrera como desarrollador.")
						.contenido("Te damos 2 clases de prueba y, si el curso no cumple con tus expectativas, te devolvemos el 100% del dinero. Sin preguntas.")
						.fechaInicio(LocalDate.of(2022, 07, 07))
						.fechaTermino(LocalDate.of(2022, 07, 26))
						.cupos(30)
						.cuposDisponibles(30)
						.datos(Files.readAllBytes(pathCuatro))
						.tipo(contentCuatro)
						.administrador(administrador)
						.build();
					
					
					cursoRepository.save(curso);
					cursoRepository.save(cursoDos);
					cursoRepository.save(cursoTres);
					cursoRepository.save(cursoCuatro);
					
											
			}
		};
	}
	

}
