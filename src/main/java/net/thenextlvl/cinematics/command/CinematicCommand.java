package net.thenextlvl.cinematics.command;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.thenextlvl.cinematics.CinematicsPlugin;

public class CinematicCommand {
    public static LiteralCommandNode<CommandSourceStack> create(CinematicsPlugin plugin) {
        return Commands.literal("cinematic")
                .requires(source -> source.getSender().hasPermission("cinematics.command.cinematic"))
                .then(RecordCommand.create(plugin))
                .then(DeleteCommand.create(plugin))
                .then(PlayCommand.create(plugin))
                .then(InfoCommand.create(plugin))
                .then(ListCommand.create(plugin))
                .build();
    }
}
