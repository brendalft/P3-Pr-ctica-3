package es.ua.dlsi.prog3.p3.lowlevel;

import java.nio.BufferOverflowException;

/**
 * Fichero: InputDevice.java
 * 
 * @author Brenda Laffitte Díaz - DNI 00000000X
 */

/**
 * Representa un dispositivo de entrada no específico. Su propósito es
 * ofrecer a las subclases (teclado, ratón, etc.) las operaciones básicas
 * para enviar bytes al canal al que está asociado el dispositivo.
 * Hereda de {@link IODevice}, que gestiona la asociación con el canal,
 * y se crea con el constructor por defecto de ésta.
 */
public class InputDevice extends IODevice {

	/**
	 * Crea un nuevo dispositivo de entrada.
	 */
	protected InputDevice() {
		super();
	}

	/**
	 * Envía un byte al canal asociado.
	 * Usa {@link #getChannel()}, que comprueba que existe canal asociado,
	 * y {@link Channel#input(byte)}, que comprueba que el canal no está lleno.
	 * 
	 * @param b byte que se envía al canal
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferOverflowException si el canal ya está lleno
	 */
	protected void sendToChannel(byte b) {
		getChannel().input(b);
	}

	/**
	 * Envía un array de bytes al canal asociado, en orden.
	 * Depende de {@link #sendToChannel(byte)}, que realiza las comprobaciones.
	 * 
	 * @param data bytes que se envían al canal
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferOverflowException si el canal está lleno o se llena al enviar los datos
	 */
	protected void put(byte[] data) {
		for (byte b : data) {
			sendToChannel(b);
		}
	}
}
