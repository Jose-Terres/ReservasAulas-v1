package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Profesores {
	private List<Profesor> coleccionProferores = new ArrayList<>();

	// Creamos el constructor
	public Profesores() {
		
	}

	public Profesores(Profesores profesores) {
		if (profesores == null) {
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		}
		this.coleccionProferores = profesores.getProfesores();
	}

	// Creamos gert de Profesor (cosntructor copia)
	public List<Profesor> getProfesores() {
		return copiaProfundaProfesores();
	}

	// creamos copia profunda
	// Creamos copia profunda Aulas
	private List<Profesor> copiaProfundaProfesores() throws IllegalArgumentException, NullPointerException {
		List<Profesor> copiaProfesores = new ArrayList<>();
		Iterator<Profesor> it = coleccionProferores.iterator();
		while (it.hasNext()) {
			copiaProfesores.add(new Profesor(it.next()));
		}
		return copiaProfesores;
	}
	
	public int getNumProfesores() {
		return coleccionProferores.size();
	}

	// cramos Insertar
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		if (coleccionProferores.contains(profesor)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		} else {
			coleccionProferores.add(profesor);
		}

	}

	// Buscar(Profesor)
	public Profesor buscar(Profesor profesor) throws IllegalArgumentException, NullPointerException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		Iterator<Profesor> it = coleccionProferores.iterator();
		while (it.hasNext()) {
			if (it.next().equals(profesor)) {
				return new Profesor(profesor);
			}
		}
		return null;

	}
	// creamos Borrar

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		boolean borrado = false;
		Iterator<Profesor> it = coleccionProferores.iterator();
		while (it.hasNext()) {
			if (it.next().equals(profesor)) {
				it.remove();
				borrado = true;
			}
		}
		if (!borrado) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		} 
	}

	public List<String> representar() {
		List<String>  cadena = new ArrayList<>();;
		Iterator<Profesor> it = coleccionProferores.iterator();
		while (it.hasNext()) {
			cadena.add(it.next().toString());
		}
		return cadena;
	}
}