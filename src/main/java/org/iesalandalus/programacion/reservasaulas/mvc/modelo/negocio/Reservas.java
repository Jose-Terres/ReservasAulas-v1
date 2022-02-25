package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
// creamos reservas con los atributos y la clase  reserva
public class Reservas {
	private List<Reserva> coleccionReservas = new ArrayList<>();
	
	// Creamos el constructor 
	public Reservas() {
	}
	
	public Reservas(Reservas reservas) {
		if (reservas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		}
		coleccionReservas = reservas.getReservas();
	}
	//Creamos gert de Profesor (cosntructor copia)
	public List<Reserva> getReservas() {
		return copiaProfundaReservas();
	}
	// creamos copia profunda
	private List<Reserva> copiaProfundaReservas() {
		List<Reserva> copiaReservas = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		while (it.hasNext()) {
			copiaReservas.add(new Reserva(it.next()));
		}
		return copiaReservas;
	}
	
	public int getNumReservas() {
		return coleccionReservas.size();
	}
// cramos metodo insertar
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		}
		// Si superamos el indice máximo es que no hay hueco
		if (!coleccionReservas.contains(reserva)) {
			coleccionReservas.add(reserva);
		} else {
			throw new OperationNotSupportedException("ERROR: La reserva ya existe.");
		}
	}
	// creamos buscar
	public Reserva buscar(Reserva reserva) throws IllegalArgumentException, NullPointerException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		}
		Iterator<Reserva> it = coleccionReservas.iterator();
		while (it.hasNext()) {
			if (it.next().equals(reserva)) {
				return new Reserva(reserva);
			}
		}
		return null;

	}
	// creamos borrar
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede anular una reserva nula.");
		}
		boolean borrado = false;
		Iterator<Reserva> it = coleccionReservas.iterator();
		while (it.hasNext()) {
			if (it.next().equals(reserva)) {
				it.remove();
				borrado = true;
			}
		}
		if (!borrado) {
			throw new OperationNotSupportedException("ERROR: La reserva a anular no existe.");
		} 
	}

	// creamos representar
	public List<String> representar() {
		List<String> cadena = new ArrayList<>();
		Iterator<Reserva> it = coleccionReservas.iterator();
		while (it.hasNext()) {
			cadena.add(it.next().toString());
		}
		return cadena;
	}
	// getReservasProfesor
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}
		List<Reserva> copiaReservas = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if(reserva.getProfesor().equals(profesor)) {
				copiaReservas.add(reserva);
			}
		}
		return copiaReservas;
	}
	// getReservasAula
	public List<Reserva> getReservasAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		}
		List<Reserva> copiaReservas = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if(reserva.getAula().equals(aula)) {
				copiaReservas.add(reserva);
			}
		}
		return copiaReservas;
	}
	// Creamos getReservasPermanencia
	public List<Reserva> getReservasPermanencia(Permanencia permanecia) {
		if (permanecia == null) {
			throw new NullPointerException("ERROR: La permanencia no puede ser nula.");
		}
		List<Reserva> copiaReservas = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if(reserva.getPermanencia().equals(permanecia)) {
				copiaReservas.add(reserva);
			}
		}
		return copiaReservas;
	}
	// Creamos consultarDisponibilidad
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		} 
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		List<Reserva> copiaReservas = getReservasAula(aula);
		if (copiaReservas.isEmpty()) {
			return true;
		}  else {
			Iterator<Reserva> it = copiaReservas.iterator();
			while (it.hasNext()) {
				if (it.next().getPermanencia().equals(permanencia)) {
					return false;
				}
			}
		}
		return true;
	}
}
