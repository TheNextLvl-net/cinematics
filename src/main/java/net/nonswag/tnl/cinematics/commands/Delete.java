package net.nonswag.tnl.cinematics.commands;

import net.nonswag.core.api.command.Invocation;
import net.nonswag.tnl.cinematics.errors.RecordNotFoundException;
import net.nonswag.tnl.listener.api.cinematic.Recording;
import net.nonswag.tnl.listener.api.command.exceptions.InvalidUseException;
import net.nonswag.tnl.listener.api.command.simple.SubCommand;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Delete extends SubCommand {

    Delete() {
        super("delete");
    }

    @Override
    protected void execute(@Nonnull Invocation invocation) {
        String[] args = invocation.arguments();
        if (args.length < 2) throw new InvalidUseException(this);
        Recording recording = Recording.load(args[1]);
        if (recording == null) throw new RecordNotFoundException();
        if (recording.delete()) invocation.source().sendMessage("%prefix% §aDeleted the recording §6" + args[1]);
        else invocation.source().sendMessage("%prefix% §cFailed to delete recording");
    }

    @Nonnull
    @Override
    protected List<String> suggest(@Nonnull Invocation invocation) {
        List<String> suggestions = new ArrayList<>();
        File[] files = Recording.getRecordings();
        for (File file : files) suggestions.add(file.getName().substring(0, file.getName().length() - 5));
        return suggestions;
    }

    @Override
    public void usage(@Nonnull Invocation invocation) {
        invocation.source().sendMessage("%prefix% §c/cinematic delete §8[§6Record§8]");
    }
}
