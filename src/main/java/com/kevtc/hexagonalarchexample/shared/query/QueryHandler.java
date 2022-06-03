package com.kevtc.hexagonalarchexample.shared.query;

import com.kevtc.hexagonalarchexample.shared.exception.QueryException;

public interface QueryHandler<T extends QueryResponse, U extends Query> {
    T handle(U query) throws QueryException;
}
