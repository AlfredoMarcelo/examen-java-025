package cl.aiep.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
	
	//encriptar la contrasena
	@Bean
	public PasswordEncoder encriptarContrasena() {
		return new BCryptPasswordEncoder();
	}
	
	
	//Autorizacion y autenticacion
	
	@Bean
	public SecurityFilterChain filtroSeguridad(HttpSecurity http) throws Exception{
		http
			.authorizeRequests(authorize -> authorize
								.anyRequest().permitAll()//para cualquier peticion, solicite la autenticacion(rutas privadas y publicas)
			)
			.formLogin(form -> form							
						.loginPage("/acceso")				//se indica la vista que se usará para el login
						.defaultSuccessUrl("/", true)		//redireccion cuando el login sea exitoso, se le agrega un true para aque fuerce la redireccion
						.permitAll()		
			).logout(logout -> logout
						.logoutRequestMatcher( new AntPathRequestMatcher("/salir", "GET")) //permitirá cerrar sesion y no usar el por defecto
					)
			;
			return http.build();
	}
	
	//configurar el acceso a archivos staticos
	@Bean
	public WebSecurityCustomizer personalizarSeguridad() {
		return (web) -> web.ignoring().antMatchers("/img/**", "/css/**", "/js/**");
	}
	
	
	
	

}
