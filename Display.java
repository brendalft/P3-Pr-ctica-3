package es.ua.dlsi.prog3.p3.highlevel;

import java.nio.BufferUnderflowException;

import es.ua.dlsi.prog3.p3.lowlevel.OutputDevice;

/**
 * Fichero: Display.java
 *
 * @author Brenda Laffitte Díaz - DNI 00000000X
 */

/**
 * Simula una pantalla cuadrada de NxN píxeles en blanco y negro, representada
 * por una matriz de bytes donde cero es un píxel apagado y cualquier otro valor
 * un píxel encendido. Es un dispositivo de salida específico que lee del canal
 * pares de bytes (x,y) con las coordenadas de los píxeles a activar; la
 * coordenada (x,y) corresponde a la columna x y la fila y de la matriz.
 * Hereda de {@link OutputDevice} las operaciones de lectura del canal.
 */
public class Display extends OutputDevice {

	/** Número de filas (y de columnas) de la pantalla. */
	private int pixel_rows;

	/** Matriz de píxeles de la pantalla, indexada como [fila y][columna x]. */
	private byte[][] display;

	/**
	 * Crea una pantalla de N filas x N columnas. El buffer del canal tendrá
	 * tamaño N*N*2, para que quepan las coordenadas de todos los píxeles.
	 *
	 * @param N número de filas y columnas de la pantalla
	 */
	public Display(int N) {
		super(N * N * 2);
		this.pixel_rows = N;
		this.display = new byte[N][N];
	}

	/**
	 * Devuelve el tamaño de la pantalla.
	 *
	 * @return número de filas (o columnas) de la pantalla
	 */
	public int getDisplaySize() {
		return pixel_rows;
	}

	/**
	 * Lee del canal todas las coordenadas (x,y) disponibles y activa los
	 * píxeles correspondientes. Si el canal no tiene datos, la matriz no cambia.
	 * Depende de {@link #receiveFromChannel()} para leer cada byte.
	 *
	 * @return copia defensiva de la matriz de píxeles
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferUnderflowException si falta el segundo byte de una coordenada
	 * @throws IndexOutOfBoundsException si una coordenada leída no corresponde a un píxel de la pantalla
	 */
	public byte[][] refresh() {
		while (getChannel().hasData()) {
			byte x = receiveFromChannel();
			byte y = receiveFromChannel();
			if (x < 0 || x >= pixel_rows || y < 0 || y >= pixel_rows) {
				throw new IndexOutOfBoundsException("Coordenada (" + x + "," + y
						+ ") fuera de la pantalla de tamaño " + pixel_rows + "x" + pixel_rows);
			}
			display[y][x] = 1;
		}
		byte[][] copy = new byte[pixel_rows][pixel_rows];
		for (int i = 0; i < pixel_rows; i++) {
			for (int j = 0; j < pixel_rows; j++) {
				copy[i][j] = display[i][j];
			}
		}
		return copy;
	}

	/**
	 * Borra por completo la pantalla, desactivando todos sus píxeles.
	 */
	public void clear() {
		for (int i = 0; i < pixel_rows; i++) {
			for (int j = 0; j < pixel_rows; j++) {
				display[i][j] = 0;
			}
		}
	}
}
