package net.nonswag.tnl.cinematics;

import net.nonswag.tnl.cinematics.commands.CinematicCommand;
import net.nonswag.tnl.listener.api.plugin.PluginUpdate;
import net.nonswag.tnl.listener.api.plugin.TNLPlugin;
import net.nonswag.tnl.listener.api.settings.Settings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Cinematics extends TNLPlugin {

    @Nullable
    private static Cinematics instance = null;

    @Override
    public void enable() {
        setInstance(this);
        getCommandManager().registerCommand(new CinematicCommand());
        async(() -> {
            if (Settings.AUTO_UPDATER.getValue()) new PluginUpdate(this).downloadUpdate();
        });
    }

    private static void setInstance(@Nonnull Cinematics instance) {
        Cinematics.instance = instance;
    }

    @Nonnull
    public static Cinematics getInstance() {
        assert instance != null;
        return instance;
    }
}
