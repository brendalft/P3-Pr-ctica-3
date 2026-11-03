package es.ua.dlsi.prog3.p3.highlevel;
 
import java.nio.BufferUnderflowException;
 
import es.ua.dlsi.prog3.p3.highlevel.exceptions.NoLineForPrintingException;
import es.ua.dlsi.prog3.p3.lowlevel.OutputDevice;
 
/**
 * Fichero: LinePrinter.java
 *
 * @author Brenda Laffitte Díaz - DNI 00000000X
 */
 
/**
 * Simula una impresora que imprime caracteres línea a línea. Es un dispositivo
 * de salida específico que espera encontrar en el buffer de su canal una cadena
 * con el formato: primer byte con el número de caracteres que siguen, y a
 * continuación dichos caracteres ASCII imprimibles.
 * Hereda de {@link OutputDevice} las operaciones de lectura del canal.
 */
public class LinePrinter extends OutputDevice {
 
	/** Longitud máxima de una línea; el buffer tiene este tamaño más uno (el byte de longitud). */
	private static final int MAX_LINE_LENGTH = 80;
 
	/**
	 * Crea una impresora con un buffer de tamaño {@link #MAX_LINE_LENGTH} más uno.
	 */
	public LinePrinter() {
		super(MAX_LINE_LENGTH + 1);
	}
 
	/**
	 * Devuelve la línea almacenada en el buffer del canal.
	 * Depende de {@link #readStoredString()}, que realiza la lectura de la cadena.
	 *
	 * @return la cadena leída del buffer
	 * @throws NoLineForPrintingException si el buffer está vacío
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferUnderflowException si el canal se vacía antes de leer toda la
	 *         cadena, es decir, si los datos no tienen el formato correcto
	 */
	public String printLine() throws NoLineForPrintingException {
		if (!getChannel().hasData()) {
			throw new NoLineForPrintingException();
		}
		return readStoredString();
	}
}
 