package org.ye.yeoldegrep.exceptions;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException() {
        super("An invalid Option was given! Please refer to the ReadME!");
    }
}
