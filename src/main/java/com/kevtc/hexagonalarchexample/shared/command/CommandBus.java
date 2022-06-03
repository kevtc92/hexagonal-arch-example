package com.kevtc.hexagonalarchexample.shared.command;

import com.kevtc.hexagonalarchexample.shared.exception.CommandException;

public interface CommandBus {
    void dispatch(Command command) throws CommandException;
}
