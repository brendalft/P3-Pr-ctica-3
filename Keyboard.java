package es.ua.dlsi.prog3.p3.highlevel;

import java.nio.BufferOverflowException;

import es.ua.dlsi.prog3.p3.lowlevel.InputDevice;

/**
 * Fichero: Keyboard.java
 *
 * @author Brenda Laffitte Díaz - DNI 00000000X
 */

/**
 * Simula un teclado: un dispositivo de entrada específico que permite
 * enviar caracteres, uno a uno, al canal al que esté asociado.
 * Hereda de {@link InputDevice} las operaciones básicas de envío de bytes.
 */
public class Keyboard extends InputDevice {

	/**
	 * Crea un teclado para enviar caracteres.
	 */
	public Keyboard() {
		super();
	}

	/**
	 * Envía un carácter al canal asociado.
	 * Depende de {@link #sendToChannel(byte)}, que realiza las comprobaciones.
	 *
	 * @param c carácter que se envía
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferOverflowException si el canal ya está lleno
	 */
	public void put(char c) {
		sendToChannel((byte) c);
	}
}
