package es.ua.dlsi.prog3.p3.lowlevel;

import java.nio.BufferUnderflowException;

/**
 * Fichero: OutputDevice.java
 *
 * @author Brenda Laffitte Díaz - DNI 00000000X
 */

/**
 * Representa un dispositivo de salida no específico. Su propósito es
 * ofrecer a las subclases (pantalla, impresora, etc.) las operaciones básicas
 * para leer bytes del canal asociado al dispositivo, ya sea byte a byte,
 * en bloques o como una cadena almacenada con su longitud por delante.
 * Hereda de {@link IODevice}, que gestiona la asociación con el canal.
 */
public class OutputDevice extends IODevice {

	/**
	 * Crea un dispositivo de salida.
	 *
	 * @param bufferSize tamaño del buffer del canal que se asociará al dispositivo
	 */
	protected OutputDevice(int bufferSize) {
		super(bufferSize);
	}

	/**
	 * Lee un byte del canal asociado.
	 * Usa {@link #getChannel()}, que comprueba que existe canal asociado,
	 * y {@link Channel#output()}, que comprueba que hay datos.
	 *
	 * @return el byte leído del canal
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferUnderflowException si el canal no contiene datos
	 */
	protected byte receiveFromChannel() {
		return getChannel().output();
	}

	/**
	 * Lee como máximo <code>num_bytes</code> bytes del canal asociado.
	 * Si el canal contiene menos datos, se devuelven sólo los que haya
	 * (posiblemente ninguno).
	 *
	 * @param num_bytes número máximo de bytes a leer, entre 1 y el tamaño del buffer
	 * @return array con los bytes leídos; su tamaño es el número de bytes leídos
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws IllegalArgumentException si num_bytes no es mayor que cero y menor
	 *         o igual al tamaño del buffer del dispositivo
	 */
	protected byte[] get(int num_bytes) {
		Channel channel = getChannel();
		if (num_bytes <= 0 || num_bytes > getBufferSize()) {
			throw new IllegalArgumentException("num_bytes debe estar entre 1 y " + getBufferSize());
		}
		byte[] tmp = new byte[num_bytes];
		int count = 0;
		while (count < num_bytes && channel.hasData()) {
			tmp[count] = receiveFromChannel();
			count++;
		}
		byte[] result = new byte[count];
		System.arraycopy(tmp, 0, result, 0, count);
		return result;
	}

	/**
	 * DON'T TOUCH THIS METHOD!!! The earth will collapse on itself if you ever think of doing it!
	 * 
	 * Reads a string from the channel. 
	 * 
	 * The channel MUST contain a string of characteres encoded as
	 * 
	 * 
	 * [length][A-Ba-b0-9]+
	 * 
	 * which means that the first byte is the string length, and the rest of bytes are the actual string 
	 * 
	 * 
	 * @return the string read, as an String object, or an empty if there is no data in the channel 
	 * @throws BufferUnderflowException if the channel becomes empty before the whole string is read, i.e. the data in the channel is corrupted
	 * @throws IllegalStateException if there is no channel associated to this device 
	 */
	protected String readStoredString() {
		byte[] data = null;
		char[] string = null;
		data = get(1);
		if (data.length != 1) 
			return "";
		int string_length = data[0];		
		data = get(string_length);
		if (data.length != string_length)
			throw new BufferUnderflowException();
		string = new char[string_length];
		for (int i=0; i < string_length; i++)
			string[i] = (char) data[i];
		return String.valueOf(string);
	}
}
