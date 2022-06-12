package cl.aiep.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

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
	@NotBlank(message = "! Debe ingresar un nombre")
	@Column(nullable = false)
	private String nombre;
	@NotBlank(message = "! Debe ingresar un numero de rut")
	@Column(nullable = false, unique = true)
	private String rut;
	@NotBlank(message = "! Debe ingresar un email")
	@Column(nullable = false, unique = true)
	private String email;
	@NotBlank(message = "! Debe ingresar una contraseña")
	@Column(nullable = false)
	private String contrasena;
	@NotBlank(message = "! Debe ingresar un número telefonico")
	@Column(nullable = false)
	private String telefono;
	@NotBlank(message = "! Debe ingresar una dirección")
	@Column(nullable = false)
	private String direccion;
	@NotBlank(message = "! Debe seleccionar una región")
	@Column(nullable = false)
	private String region;

	
}
