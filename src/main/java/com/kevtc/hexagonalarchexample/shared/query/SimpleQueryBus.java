package com.kevtc.hexagonalarchexample.shared.query;

import com.kevtc.hexagonalarchexample.shared.exception.QueryException;
import io.vavr.control.Try;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimpleQueryBus implements QueryBus {

    private final Map<Class<?>, QueryHandler<QueryResponse, Query>> handlers;

    public SimpleQueryBus(List<QueryHandler<QueryResponse, Query>> queryHandlers) {
        this.handlers = new HashMap<>();
        queryHandlers.forEach(queryHandler -> {
            Class<?> queryClass = getQueryClass(queryHandler);
            handlers.put(queryClass, queryHandler);
        });
    }

    @Override
    public QueryResponse dispatch(Query query) throws QueryException {
        if (!handlers.containsKey(query.getClass()))
            throw new QueryException(String.format("No habndler for %s", query.getClass().getName()));
        return handlers.get(query.getClass()).handle(query);
    }

    private Class<?> getClass(String name) {
        return Try.of(() -> Class.forName(name)).getOrNull();
    }

    private Class<?> getQueryClass(QueryHandler<QueryResponse, Query> handler) {
        Type queryInterface = ((ParameterizedType) handler.getClass()
                .getGenericInterfaces()[0])
                .getActualTypeArguments()[1];
        return getClass(queryInterface.getTypeName());
    }

}
