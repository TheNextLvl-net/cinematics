package net.nonswag.tnl.cinematics.errors;

import net.nonswag.core.api.command.Invocation;
import net.nonswag.tnl.cinematics.utils.Messages;
import net.nonswag.tnl.listener.api.command.exceptions.CommandException;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RecordNotFoundException extends CommandException {

    @Override
    public void handle(Invocation invocation) {
        invocation.source().sendMessage(Messages.RECORD_NOT_FOUND);
    }
}
