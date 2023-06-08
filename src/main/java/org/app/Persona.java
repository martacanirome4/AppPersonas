package org.app;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Persona {

	String name;
	LocalDate fechaDeNacimiento;

	// Importante crear aunque no se indique explicitamente
	private static List<Persona> personas = new ArrayList<>();

	// Constructor
	public Persona(String name, LocalDate fechaDeNacimiento) {
		this.name = name;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public long calcularEdad() {

		LocalDate currentDate = LocalDate.now();
		Period age = Period.between(fechaDeNacimiento, currentDate);

		return age.getYears();

	}

	public void annadirPersona(Persona persona) {
        personas.add(persona);
    }

    // Getters
    public String getName() {
    	return name;
    }

    public LocalDate getFechaDeNacimiento() {
    	return fechaDeNacimiento;
    }


	/*Obtener la persona mas joven usando expresiones lambda*/
	public Persona elMasJoven() {

		return personas.stream()
					.min(Comparator.comparing(Persona::getFechaDeNacimiento))
					.orElse(null);
	}

	/*Calculo de la suma de todas las edades*/
	public long calcularSumaEdades() {

		// return personas.stream().reduce( (long acumulador, persona) -> (acumulador+persona.calcularEdad) );
		return personas.stream()
					.mapToLong(Persona::calcularEdad)
					.sum();

	}

	/*Obtencion de la edad minima*/
	public long calcularEdadMinima() {

		return personas.stream()
					.mapToLong(Persona::calcularEdad)
					.min()
					.orElse(0);

	}

	/*Calculo de la edad media*/
	public double calcularMediaEdad() {

		/** average() method calculates the average of a stream of primitive long values and returns an OptionalDouble 
		 * that may contain the average value or be empty if the stream is empty
		 * for example, min, returns an int value **/

		return personas.stream()
					.mapToLong(Persona::calcularEdad)
					.average()
					.orElse(0.0);
	}

}
