package net.nonswag.tnl.cinematics.commands;

import net.nonswag.core.api.command.CommandSource;
import net.nonswag.core.api.command.Invocation;
import net.nonswag.core.utils.StringUtil;
import net.nonswag.tnl.cinematics.errors.RecordNotFoundException;
import net.nonswag.tnl.listener.api.cinematic.Recording;
import net.nonswag.tnl.listener.api.command.exceptions.InvalidUseException;
import net.nonswag.tnl.listener.api.command.simple.SubCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Info extends SubCommand {

    Info() {
        super("info");
    }

    @Override
    protected void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        if (args.length < 2) throw new InvalidUseException(this);
        Recording recording = Recording.load(args[1]);
        if (recording == null) throw new RecordNotFoundException();
        String size = StringUtil.format("#,##0.0kb", recording.getFile().length() / 1024d);
        source.sendMessage("%prefix% §7Recording§8: §6" + recording.getName());
        source.sendMessage("%prefix% §7Frames§8: §6" + recording.getSteps().size());
        source.sendMessage("%prefix% §7File Size§8: §6" + size);
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
        invocation.source().sendMessage("%prefix% §c/cinematic info §8[§6Record§8]");
    }
}
