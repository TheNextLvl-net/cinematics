package net.thenextlvl.cinematics.commands;

import net.thenextlvl.cinematics.CinematicsPlugin;

public class CinematicCommand {

    public CinematicCommand() {
        super("cinematic", "tnl.cinematic");
        addSubCommand(new RecordCommand());
        addSubCommand(new DeleteCommand());
        addSubCommand(new PlayCommand());
        addSubCommand(new InfoCommand());
        addSubCommand(new ListCommand());
    }
}
