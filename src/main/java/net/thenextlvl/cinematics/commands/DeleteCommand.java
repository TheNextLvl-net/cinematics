package net.thenextlvl.cinematics.commands;

import net.nonswag.core.api.command.Invocation;
import net.thenextlvl.cinematics.errors.RecordNotFoundException;
import net.nonswag.tnl.listener.api.cinematic.Recording;
import net.nonswag.tnl.listener.api.command.exceptions.InvalidUseException;
import net.nonswag.tnl.listener.api.command.simple.SubCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class DeleteCommand extends SubCommand {

    DeleteCommand() {
        super("delete");
    }

    @Override
    protected void execute(Invocation invocation) {
        String[] args = invocation.arguments();
        if (args.length < 2) throw new InvalidUseException(this);
        Recording recording = Recording.load(args[1]);
        if (recording == null) throw new RecordNotFoundException();
        if (recording.delete()) invocation.source().sendMessage("%prefix% §aDeleted the recording §6" + args[1]);
        else invocation.source().sendMessage("%prefix% §cFailed to delete recording");
    }

    @Override
    protected List<String> suggest(Invocation invocation) {
        List<String> suggestions = new ArrayList<>();
        File[] files = Recording.getRecordings();
        for (File file : files) suggestions.add(file.getName().substring(0, file.getName().length() - 5));
        return suggestions;
    }

    @Override
    public void usage(Invocation invocation) {
        invocation.source().sendMessage("%prefix% §c/cinematic delete §8[§6Record§8]");
    }
}
