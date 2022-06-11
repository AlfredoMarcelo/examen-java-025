package cl.aiep.java.validacion;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cl.aiep.java.model.Curso;



public class FechaInicioTerminoValidator 
	implements ConstraintValidator<FechaInicioTermino, Curso>
{

	@Override
	public boolean isValid(Curso curso, ConstraintValidatorContext context) {
		LocalDate fechaInicio 	= curso.getFechaInicio();
		LocalDate fechaTermino 	= curso.getFechaTermino();
		
		if( fechaInicio == null || fechaTermino == null) {
			context.buildConstraintViolationWithTemplate("Las fechas no pueden ser nulas");
			return false;
		}
		
		if( fechaInicio.isBefore(fechaTermino) ) {
			return true; // es válido 
		} else {
			return false;
		}
	}

}
