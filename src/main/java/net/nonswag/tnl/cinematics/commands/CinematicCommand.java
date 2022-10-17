package net.nonswag.tnl.cinematics.commands;

import net.nonswag.tnl.listener.api.command.simple.SimpleCommand;

public class CinematicCommand extends SimpleCommand {

    public CinematicCommand() {
        super("cinematic", "tnl.cinematic");
        addSubCommand(new Record());
        addSubCommand(new Delete());
        addSubCommand(new Play());
        addSubCommand(new Info());
        addSubCommand(new List());
    }
}
