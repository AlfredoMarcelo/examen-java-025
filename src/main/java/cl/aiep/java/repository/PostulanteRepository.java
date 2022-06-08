package cl.aiep.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.aiep.java.model.Postulante;

public interface PostulanteRepository extends JpaRepository<Postulante, Long>{

	//metodo buscar por rut para el postulante
	public Optional<Postulante> findByRut(String rut);
	
	
}
