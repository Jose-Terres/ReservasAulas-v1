package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.*;

public class Modelo {
	// creamos la clase y le asignamos los atributos, en cantidad constante  le pongo 10 
		private Profesores profesores;
		private Aulas aulas;
		private Reservas reservas;
		
		public Modelo() {
			profesores = new Profesores();
			aulas = new Aulas();
			reservas = new Reservas();
		}
		// llamamos a los metos que nos indica en el diagrama de clases
		public List<Aula> getAulas() {
			return aulas.getAulas();
		}
		// llamamos a getNumAulas
		public int getNumAulas() {
			return getNumAulas();
		}
		
		public List<String> representarAulas() {
			return aulas.representar();
		}
		
		public Aula buscarAula(Aula aula) {
			return aulas.buscar(aula);
		}
		
		public void insertarAula(Aula aula) throws OperationNotSupportedException {
			aulas.insertar(aula);
		}
		
		public void borrarAula(Aula aula) throws OperationNotSupportedException {
			aulas.borrar(aula);
		}
		
		public List<Profesor> getProfesores() {
			return profesores.getProfesores();
		}
		
		public int getNumProfesores() {
			return getNumProfesores();
		}
		
		public List<String> representarProfesores() {
			return profesores.representar();
		}
		
		public Profesor buscarProfesor(Profesor profesor) {
			return profesores.buscar(profesor);
		}
		
		public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
			profesores.insertar(profesor);
		}
		
		public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
			profesores.borrar(profesor);
		}
		
		public List<Reserva> getReservas() {
			return reservas.getReservas();
		}
		
		public int getNumReservas() {
			return getNumReservas();
		}
		
		public List<String> representarReservas() {
			return reservas.representar();
		}
		
		public Reserva buscarReserva(Reserva reserva) {
			return reservas.buscar(reserva);
		}
		
		public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
			reservas.insertar(reserva);
		}
		
		public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
			reservas.borrar(reserva);
		}
		
		public List<Reserva> getReservaAula(Aula aula) {
			return reservas.getReservasAula(aula);
		}
		
		public List<Reserva> getReservasProfesor(Profesor profesor) {
			return reservas.getReservasProfesor(profesor);
		}
		
		public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
			return reservas.getReservasPermanencia(permanencia);
		}
		
		public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
			return reservas.consultarDisponibilidad(aula, permanencia);
		}
}
