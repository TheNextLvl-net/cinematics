package net.nonswag.tnl.cinematics;

import net.nonswag.tnl.cinematics.commands.CinematicCommand;
import net.nonswag.tnl.listener.api.command.CommandManager;
import net.nonswag.tnl.listener.api.plugin.PluginUpdate;
import net.nonswag.tnl.listener.api.settings.Settings;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Cinematics extends JavaPlugin {

    @Nullable
    private static Cinematics instance = null;

    @Override
    public void onEnable() {
        setInstance(this);
        CommandManager.registerCommands(new CinematicCommand());
        if (Settings.AUTO_UPDATER.getValue()) new PluginUpdate(this).downloadUpdate();
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
