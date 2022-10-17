package net.nonswag.tnl.cinematics.commands;

import net.nonswag.core.api.command.Invocation;
import net.nonswag.tnl.listener.api.cinematic.Recording;
import net.nonswag.tnl.listener.api.command.simple.SubCommand;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;

class List extends SubCommand {

    List() {
        super("list");
    }

    @Override
    protected void execute(@Nonnull Invocation invocation) {
        File[] files = Recording.getRecordings();
        java.util.List<String> recordings = new ArrayList<>();
        for (File file : files) recordings.add(file.getName().substring(0, file.getName().length() - 5));
        String s = recordings.isEmpty() ? "§7-§8/§7-" : String.join("§8, §6", recordings);
        invocation.source().sendMessage("%prefix% §7Recordings §8(§a" + recordings.size() + "§8): §6" + s);
    }
}
