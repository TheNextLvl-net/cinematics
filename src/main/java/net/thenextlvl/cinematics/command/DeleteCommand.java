package net.thenextlvl.cinematics.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.thenextlvl.cinematics.CinematicsPlugin;

class DeleteCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> create(CinematicsPlugin plugin) {
        return Commands.literal("delete");
    }

    //@Override
    //protected void execute(Invocation invocation) {
    //    String[] args = invocation.arguments();
    //    if (args.length < 2) throw new InvalidUseException(this);
    //    Recording recording = Recording.load(args[1]);
    //    if (recording == null) throw new RecordNotFoundException();
    //    if (recording.delete()) invocation.source().sendMessage("<prefix> §aDeleted the recording §6" + args[1]);
    //    else invocation.source().sendMessage("<prefix> §cFailed to delete recording");
    //}

    //@Override
    //protected List<String> suggest(Invocation invocation) {
    //    List<String> suggestions = new ArrayList<>();
    //    File[] files = Recording.getRecordings();
    //    for (File file : files) suggestions.add(file.getName().substring(0, file.getName().length() - 5));
    //    return suggestions;
    //}
}
