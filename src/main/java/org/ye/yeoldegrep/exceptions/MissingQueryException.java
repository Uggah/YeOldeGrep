package org.ye.yeoldegrep.exceptions;

public class MissingQueryException extends RuntimeException {
    public MissingQueryException() {
        super("No query was given!");
    }
}
