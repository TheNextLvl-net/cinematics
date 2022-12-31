package net.nonswag.tnl.cinematics;

import net.nonswag.tnl.cinematics.commands.CinematicCommand;
import net.nonswag.tnl.cinematics.utils.Messages;
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
