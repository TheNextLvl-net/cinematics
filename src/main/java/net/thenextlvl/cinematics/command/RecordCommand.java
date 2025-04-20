package net.thenextlvl.cinematics.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.thenextlvl.cinematics.CinematicsPlugin;

class RecordCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> create(CinematicsPlugin plugin) {
        return Commands.literal("record");
    }

    //@Override
    //protected void execute(Invocation invocation) {
    //    TNLPlayer player = (TNLPlayer) invocation.source();
    //    String[] args = invocation.arguments();
    //    CinematicManger cinematicManger = player.cinematicManger();
    //    Messenger messenger = player.messenger();
    //    if (cinematicManger.isRecording()) {
    //        cinematicManger.stopRecording();
    //        messenger.sendMessage(Messages.RECORDING_STOPPED);
    //    } else if (args.length < 2) throw new InvalidUseException(this);
    //    if (Recording.exists(args[1])) throw new RecordNotFoundException();
    //    messenger.sendMessage("<prefix> §aRecording §6" + args[1]);
    //    cinematicManger.record(new Recording(args[1]), new CinematicManger.Finished() {
    //        @Override
    //        public void success(TNLPlayer player, Recording recording) {
    //            player.messenger().sendMessage(Messages.RECORDING_SAVED, new Placeholder("recording", args[1]));
    //        }

    //        @Override
    //        public void general(TNLPlayer player, Recording recording) {
    //            recording.export();
    //            player.cinematicManger().play(recording);
    //        }

    //        @Override
    //        public void failure(TNLPlayer player, Recording recording) {
    //            player.messenger().sendMessage(Messages.RECORDING_ERROR);
    //        }
    //    });
    //}
}
