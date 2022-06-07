package cl.aiep.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Postulante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false, unique = true)
	private String rut;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String contrasena;
	@Column(nullable = false)
	private String telefono;
	@Column(nullable = false)
	private String direccion;
	@Column(nullable = false)
	private String region;

	
}
