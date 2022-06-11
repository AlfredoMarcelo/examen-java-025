package cl.aiep.java.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import cl.aiep.java.validacion.FechaInicioTermino;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FechaInicioTermino
@Entity
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "! Debe ingresar un nombre")
	@Column(nullable = false)
	private String nombre;
	@NotBlank(message = "! Debe agregar la descripcion del curso")
	@Column(nullable = false, columnDefinition = "TEXT")
	private String descripcion;
	@NotBlank(message = "! Debe agregar los contenidos del curso")
	@Column(nullable = false, columnDefinition = "TEXT")
	private String contenido;
	@Column(nullable = false)
	private LocalDate fechaInicio;
	@Column(nullable = false)
	private LocalDate fechaTermino;
	@Min(value = 5, message = "! El curso debe tener un minimo de 5 estudiantes")
	@Max(value = 30, message="! El curso debe tener como maximo 30 estudiantes")
	@Column(nullable = false)
	private int cupos;
	private int cuposDisponibles;
	//imagen
	private String tipo;
	@Lob
	private byte[] datos;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Administrador administrador;
	
	
	
}
