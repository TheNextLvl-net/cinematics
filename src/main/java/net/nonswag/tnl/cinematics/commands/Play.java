package net.nonswag.tnl.cinematics.commands;

import net.nonswag.core.api.command.Invocation;
import net.nonswag.tnl.cinematics.errors.RecordNotFoundException;
import net.nonswag.tnl.listener.api.cinematic.Recording;
import net.nonswag.tnl.listener.api.command.exceptions.InvalidUseException;
import net.nonswag.tnl.listener.api.command.simple.PlayerSubCommand;
import net.nonswag.tnl.listener.api.player.TNLPlayer;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Play extends PlayerSubCommand {

    Play() {
        super("play");
    }

    @Override
    protected void execute(@Nonnull Invocation invocation) {
        TNLPlayer player = (TNLPlayer) invocation.source();
        String[] args = invocation.arguments();
        if (args.length < 2) throw new InvalidUseException(this);
        Recording recording = Recording.load(args[1]);
        if (recording == null) throw new RecordNotFoundException();
        player.cinematicManger().play(recording);
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
        invocation.source().sendMessage("%prefix% §c/cinematic play §8[§6Record§8]");
    }
}
