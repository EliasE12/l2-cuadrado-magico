package exceptions;

/**
 * Entidad que representa la excepción que se produce cuando
 * el orden ingresado no es un número positivo.
 */
public class NoNumeroPositivoException extends Exception {

    // Constructor
    public NoNumeroPositivoException(String ms){
        super(ms);
    }
}
