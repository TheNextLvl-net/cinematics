package net.nonswag.tnl.cinematics;

import net.nonswag.tnl.cinematics.commands.CinematicCommand;
import net.nonswag.tnl.cinematics.utils.Messages;
import net.nonswag.tnl.listener.api.plugin.PluginUpdate;
import net.nonswag.tnl.listener.api.plugin.TNLPlugin;
import net.nonswag.tnl.listener.api.settings.Settings;

public class Cinematics extends TNLPlugin {
    @Override
    public void enable() {
        Messages.init();
        getCommandManager().registerCommand(new CinematicCommand());
        async(() -> {
            if (Settings.AUTO_UPDATER.getValue()) new PluginUpdate(this).downloadUpdate();
        });
    }
}
