package es.ua.dlsi.prog3.p3.highlevel.exceptions;

/**
 * Fichero: NoLineForPrintingException.java
 *
 * @author Brenda Laffitte Díaz - DNI 00000000X
 */

/**
 * Excepción verificada que se lanza cuando una impresora de líneas intenta
 * imprimir pero no hay ninguna línea disponible en el buffer de su canal.
 * Se define en un subpaquete propio para separar las excepciones de usuario
 * de las clases de dispositivos, y no recibe ningún parámetro en su constructor.
 */
public class NoLineForPrintingException extends Exception {

	/** Identificador de versión de serialización de la excepción. */
	private static final long serialVersionUID = 1L;

	/**
	 * Crea la excepción sin mensaje.
	 */
	public NoLineForPrintingException() {
		super();
	}
}
