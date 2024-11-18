package ar.edu.unju.escmi.tpfinal.exceptions;

public class InvalidTimeRangeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTimeRangeException(String message) {
        super(message);
    }
}