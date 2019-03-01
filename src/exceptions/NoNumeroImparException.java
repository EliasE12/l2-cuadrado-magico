package exceptions;

/**
 * Entidad que representa la excepción que se produce cuando
 * el orden ingresado no es un número impar.
 */
public class NoNumeroImparException extends Exception {

    // Constructor
    public NoNumeroImparException(String ms){
        super(ms);
    }
}
