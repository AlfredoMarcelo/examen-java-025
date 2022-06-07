package cl.aiep.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.aiep.java.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

}
