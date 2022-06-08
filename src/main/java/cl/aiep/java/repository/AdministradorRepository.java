package cl.aiep.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.aiep.java.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
	
	public Optional<Administrador> findByNombre(String nombre); 

}
