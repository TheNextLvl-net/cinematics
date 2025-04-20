package net.thenextlvl.cinematics;

import net.thenextlvl.cinematics.commands.CinematicCommand;
import net.thenextlvl.cinematics.utils.Messages;
import net.nonswag.tnl.listener.api.plugin.TNLPlugin;

public class Cinematics extends TNLPlugin {
    @Override
    public void load() {
        Messages.init();
    }

    @Override
    public void enable() {
        getCommandManager().registerCommand(new CinematicCommand());
    }
}
