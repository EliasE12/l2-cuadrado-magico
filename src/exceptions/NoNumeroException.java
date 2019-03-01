package exceptions;

import java.util.InputMismatchException;

/**
 * Entidad que representa la excepción que se produce
 * cuando el orden ingresado no es un número.
 */
public class NoNumeroException extends NumberFormatException {

    // Constructor
    public NoNumeroException(String ms) {
        super(ms);
    }

}
