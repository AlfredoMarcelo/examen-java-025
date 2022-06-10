package cl.aiep.java.seguridad;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cl.aiep.java.model.Administrador;
import cl.aiep.java.model.Postulante;

//se implementa el metodo UserDetails desde userDetailService, este devolvera varios metodos
//UserDetail no influye en el atributo que se usa para autenticar
public class Usuario implements UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6383347288116472226L;
	private Administrador administrador;
	private Postulante postulante;
	
	
	//constructor
	public Usuario(Postulante postulante, Administrador administrador) {
		this.postulante	= postulante;
		this.administrador = administrador;
	}
	
	public Administrador getAdministrador() {return administrador;}
	public Postulante getPostulante() {return postulante;}
	
	@Override
	public String getUsername() {
		// devuelve lo que queremos mostrar en la vista, ej: nombre, rut, etc.
		if( postulante != null) return postulante.getNombre();
		if( administrador != null) return administrador.getNombre();
		return null;
	}
	
	@Override
	public String getPassword() {
		// Se usa para comparar la contrasena ingresada y la guardada en bd
		if(postulante != null) return postulante.getContrasena();
		if(administrador != null) return administrador.getContrasena();
		return null;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Devuelve los permisos que tiene el usuario.
		if( postulante != null) return List.of( new SimpleGrantedAuthority("POSTULANTE"));
		if ( administrador != null) return List.of( new SimpleGrantedAuthority("ADMINISTRADOR"));
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
