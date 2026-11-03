package es.ua.dlsi.prog3.p3.highlevel;

import java.nio.BufferOverflowException;

import es.ua.dlsi.prog3.p3.lowlevel.InputDevice;

/**
 * Fichero: Mouse.java
 *
 * @author Brenda Laffitte Díaz - DNI 00000000X
 */

/**
 * Simula un ratón: un dispositivo de entrada específico que envía al canal
 * asociado la posición actual del cursor en pantalla como una coordenada 2D
 * formada por dos bytes (primero x y después y).
 * Hereda de {@link InputDevice} las operaciones básicas de envío de bytes.
 */
public class Mouse extends InputDevice {

	/**
	 * Crea un ratón que permite enviar coordenadas 2D de la posición del cursor.
	 */
	public Mouse() {
		super();
	}

	/**
	 * Envía una coordenada 2D al canal asociado: primero el byte x y luego el byte y.
	 * Depende de {@link #sendToChannel(byte)}, que realiza las comprobaciones.
	 *
	 * @param x coordenada horizontal
	 * @param y coordenada vertical
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferOverflowException si el canal ya está lleno o se llena tras enviar el primer byte
	 */
	public void put(byte x, byte y) {
		sendToChannel(x);
		sendToChannel(y);
	}
}
