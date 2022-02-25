package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.util.Iterator;
import java.util.List;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

// creamos la clase vista
public class Vista {

	/*
	 * private static String ERROR; private static String NOMBRE_VALIDO; private
	 * static String CORREO_VALIDO;
	 */

	private Controlador controlador;

	// constructor
	public Vista() {
		Opcion.setVista(this);
	}

	// creamso setControlador con el null y su error
	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
		}
		this.controlador = controlador;
	}

	// creamso comenzar
	public void comenzar() {
		Consola.mostrarCabecera("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		int opcion;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			Opcion opcionElegida = Opcion.getOpcionSegunOrdinal(opcion);
			opcionElegida.ejecutar();
		} while (opcion != Opcion.SALIR.ordinal());
	}

	// cremos salir
	public void salir() {
		controlador.terminar();
	}

	// creamos insertarAula
	public void insertarAula() {
		Consola.mostrarCabecera("INSERTAR AULA");
		// intenta llamando al controlador insertar aula con el paramentro de la consola
		// LeerAula y si todo es correcto muestra mensaje
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula Insertada Correctamente");

			// que lance la excepcion
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// creamos borrarAula
	public void borrarAula() {
		Consola.mostrarCabecera("BORRAR AULA");
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Aula borrada correctamente");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// creamos buscarAula
	public void buscarAula() {
		Consola.mostrarCabecera("BUSCAR AULA");
		Aula aula;
		try {
			aula = controlador.buscarAula(Consola.leerAula());
			if (aula == null) {
				System.out.println("\nEl aula no existe");
			} else {
				System.out.println(aula.toString());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	// mostramos listarAulas
	public void listarAulas() {
		Consola.mostrarCabecera("LISTADO DE AULAS");
		List<String> listaAulas = controlador.representarAulas();
		if (!listaAulas.isEmpty()) {
			Iterator<String> it = listaAulas.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("No hay aulas que mostrar");
		}
	}

	// creamos insertarProfesor
	public void insertarProfesor() {
		Consola.mostrarCabecera("INSERTAR PROFESOR");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor Insertado Correctamente");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// creamos borrarProfesor
	public void borrarProfesor() {
		Consola.mostrarCabecera("BORRAR PROFESOR");
		try {
			controlador.borrarProfesor(Consola.leerProfesor());
			System.out.println("Profesor borrado correctamente");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// creamos buscarProfesor
	public void buscarProfesor() {
		Consola.mostrarCabecera("BUSCAR PROFESOR");
		Profesor profesor;
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesor());
			if (profesor == null) {
				System.out.println("\nEl profesor no existe");
			} else {
				System.out.println(profesor.toString());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	// listwar profesores
	public void listarProfesores() {
		Consola.mostrarCabecera("LISTADO DE PROFESORES");
		List<String> listaProfesores = controlador.representarProfesores();
		if (!listaProfesores.isEmpty()) {
			Iterator<String> it = listaProfesores.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("No hay profesores que mostrar");
		}
	}

	// cramos realizarReserva
	public void realizarReserva() {
		try {
			System.out.println("INTRODUCE LOS DATOS DE LA RESERVA");
			Reserva reserva = leerReserva();
			Boolean centinela = true;
			if (controlador.buscarAula(reserva.getAula()) == null) {
				System.out.println("El aula no esta en listado");
				centinela = false;
			}
			if (controlador.buscarProfesor(reserva.getProfesor()) == null) {
				System.out.println("El profesor no esta en listado");
				centinela = false;
			}
			if (centinela) {
				controlador.realizarReserva(reserva);
				System.out.println("Reserva realizada con exito.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// creamos leerReserva
	private Reserva leerReserva() {
		Reserva reserva = null;
		boolean centinela = false;
		boolean centinelaDisponibilidad = false;
		Aula aula = null;
		Permanencia permanencia = null;
		do {
			do {
				aula = Consola.leerAula();
				permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
				if (controlador.consultarDisponibilidad(aula, permanencia)) {
					centinelaDisponibilidad = true;
				}
			} while (!centinelaDisponibilidad);
			try {
				reserva = new Reserva(Consola.leerProfesor(), aula, permanencia);
				centinela = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!centinela);
		return reserva;
	}

// creamos anularReserva
	public void anularReserva() {
		try {
			System.out.println("ANULAR RESERVA");
			controlador.anularReserva(leerReserva());
			System.out.println("Reserva eliminda con éxito");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// creamos listar Reservas
	public void listarReservas() {
		Consola.mostrarCabecera("LISTADO DE RESERVAS");
		List<String> reservas = controlador.representarReservas();
		if (!reservas.isEmpty()) {
			Iterator<String> it = reservas.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("No hay reservas para mostrar");
		}
	}

	// cremos listarReservasAulas
	public void listarReservasAula() {
		Consola.mostrarCabecera("LISTADO DE RESERVAS POR AULA");
		List<Reserva> reservas = controlador.getReservasAula(Consola.leerAula());
		if (!reservas.isEmpty()) {
			Iterator<Reserva> it = reservas.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("No hay reservas para mostrar");
		}
	}

	// creamos listarReservasProfesor
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("LISTADO DE RESERVAS POR PROFESOR");
		List<Reserva> reservas = controlador.getReservasProfesor(Consola.leerProfesor());
		if (!reservas.isEmpty()) {
			Iterator<Reserva> it = reservas.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("No hay reservas para mostrar");
		}
	}

	// creamos listarReservasPermanencia
	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("LISTADO DE RESERVAS POR HORARIO");
		List<Reserva> reservas = controlador
				.getReservasPermanencia(new Permanencia(Consola.leerDia(), Consola.leerTramo()));
		if (!reservas.isEmpty()) {
			Iterator<Reserva> it = reservas.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("No hay reservas para mostrar");
		}
	}

	// Creamos consultarDisponibilidad
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("CONSULTAR DISPONIBILIDAD");
		Aula aula = Consola.leerAula();
		if (controlador.buscarAula(aula) == null) {
			System.out.println("El aula no esta en el listado");
		} else {
			if (controlador.consultarDisponibilidad(aula, new Permanencia(Consola.leerDia(), Consola.leerTramo()))) {
				System.out.println("El aula esta disponible");
			} else {
				System.out.println("El aula no esta disponible");
			}
		}

	}
}
