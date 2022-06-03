package com.kevtc.hexagonalarchexample.shared.command;

import com.kevtc.hexagonalarchexample.shared.exception.CommandException;

public interface CommandHandler<T extends Command> {
    void handle(T command) throws CommandException;
}
