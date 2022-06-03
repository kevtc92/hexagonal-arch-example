package com.kevtc.hexagonalarchexample.shared.exception;

public class QueryException extends RuntimeException {

    public QueryException(String message) {
        super("Exception in query with message: " + message);
    }
}
