package org.ye.yeoldegrep.exceptions;

public class MissingFilePathException extends RuntimeException {
    public MissingFilePathException() {
        super("No file-paths were given!");
    }
}
