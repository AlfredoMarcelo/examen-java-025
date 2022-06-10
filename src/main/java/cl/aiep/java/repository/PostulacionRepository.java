package cl.aiep.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.aiep.java.model.Postulacion;
import cl.aiep.java.model.Postulante;

public interface PostulacionRepository extends JpaRepository<Postulacion, Long>{

	public long countByPostulante(Postulante postulante);
	
}
