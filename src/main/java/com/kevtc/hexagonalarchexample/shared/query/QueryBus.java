package com.kevtc.hexagonalarchexample.shared.query;

import com.kevtc.hexagonalarchexample.shared.exception.QueryException;

public interface QueryBus {
    QueryResponse dispatch(Query query) throws QueryException;
}
