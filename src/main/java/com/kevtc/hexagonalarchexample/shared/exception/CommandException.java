package com.kevtc.hexagonalarchexample.shared.exception;

public class CommandException extends RuntimeException {

    public CommandException(String message) {
        super("Exception in commmand with message: " + message);
    }
}
