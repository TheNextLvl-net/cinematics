package net.thenextlvl.cinematics.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.thenextlvl.cinematics.CinematicsPlugin;

class PlayCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> create(CinematicsPlugin plugin) {
        return Commands.literal("play");
    }

    //@Override
    //protected void execute(Invocation invocation) {
    //    TNLPlayer player = (TNLPlayer) invocation.source();
    //    String[] args = invocation.arguments();
    //    if (args.length < 2) throw new InvalidUseException(this);
    //    Recording recording = Recording.load(args[1]);
    //    if (recording == null) throw new RecordNotFoundException();
    //    player.cinematicManger().play(recording);
    //}

    //@Override
    //protected List<String> suggest(Invocation invocation) {
    //    List<String> suggestions = new ArrayList<>();
    //    File[] files = Recording.getRecordings();
    //    for (File file : files) suggestions.add(file.getName().substring(0, file.getName().length() - 5));
    //    return suggestions;
    //}
}
