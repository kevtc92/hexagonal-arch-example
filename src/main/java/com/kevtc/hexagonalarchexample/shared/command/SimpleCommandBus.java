package com.kevtc.hexagonalarchexample.shared.command;

import com.kevtc.hexagonalarchexample.shared.exception.CommandException;
import io.vavr.control.Try;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimpleCommandBus implements CommandBus {

    private final Map<Class<?>, CommandHandler<Command>> handlers;

    public SimpleCommandBus(List<CommandHandler<Command>> commandHandlers) {
        this.handlers = new HashMap<>();
        commandHandlers.forEach(commandHandler -> {
            Class<?> commandClass = getCommandClass(commandHandler);
            handlers.put(commandClass,commandHandler);
        });
    }

    @Override
    public void dispatch(Command command) throws CommandException {
        if (!handlers.containsKey(command.getClass()))
            throw new CommandException(String.format("No handler for %s", command.getClass().getName()));
        handlers.get(command.getClass()).handle(command);
    }

    private Class<?> getClass(String name) {
        return Try.of(() -> Class.forName(name)).getOrNull();
    }

    private Class<?> getCommandClass(CommandHandler<Command> commandHandler) {
        Type commandInterface = ((ParameterizedType) commandHandler.getClass()
                .getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
        return getClass(commandInterface.getTypeName());
    }

}
