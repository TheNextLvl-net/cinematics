package net.thenextlvl.cinematics;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.thenextlvl.cinematics.command.CinematicCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CinematicsPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        registerCommands();
    }

    private void registerCommands() {
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event ->
                event.registrar().register(CinematicCommand.create(this)));
    }
}
