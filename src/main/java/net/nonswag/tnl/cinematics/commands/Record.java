package net.nonswag.tnl.cinematics.commands;

import net.nonswag.core.api.command.Invocation;
import net.nonswag.core.api.message.Placeholder;
import net.nonswag.tnl.cinematics.errors.RecordNotFoundException;
import net.nonswag.tnl.cinematics.utils.Messages;
import net.nonswag.tnl.listener.api.cinematic.Recording;
import net.nonswag.tnl.listener.api.command.exceptions.InvalidUseException;
import net.nonswag.tnl.listener.api.command.simple.PlayerSubCommand;
import net.nonswag.tnl.listener.api.player.TNLPlayer;
import net.nonswag.tnl.listener.api.player.manager.CinematicManger;
import net.nonswag.tnl.listener.api.player.manager.Messenger;

import javax.annotation.Nonnull;

class Record extends PlayerSubCommand {

    Record() {
        super("record");
    }

    @Override
    protected void execute(@Nonnull Invocation invocation) {
        TNLPlayer player = (TNLPlayer) invocation.source();
        String[] args = invocation.arguments();
        CinematicManger cinematicManger = player.cinematicManger();
        Messenger messenger = player.messenger();
        if (cinematicManger.isRecording()) {
            cinematicManger.stopRecording();
            messenger.sendMessage(Messages.RECORDING_STOPPED);
        } else if (args.length < 2) throw new InvalidUseException(this);
        if (Recording.exists(args[1])) throw new RecordNotFoundException();
        messenger.sendMessage("%prefix% §aRecording §6" + args[1]);
        cinematicManger.record(new Recording(args[1]), new CinematicManger.Finished() {
            @Override
            public void success(@Nonnull TNLPlayer player, @Nonnull Recording recording) {
                player.messenger().sendMessage(Messages.RECORDING_SAVED, new Placeholder("recording", args[1]));
            }

            @Override
            public void general(@Nonnull TNLPlayer player, @Nonnull Recording recording) {
                recording.export();
                player.cinematicManger().play(recording);
            }

            @Override
            public void failure(@Nonnull TNLPlayer player, @Nonnull Recording recording) {
                player.messenger().sendMessage(Messages.RECORDING_ERROR);
            }
        });
    }

    @Override
    public void usage(@Nonnull Invocation invocation) {
        invocation.source().sendMessage("%prefix% §c/cinematic record §8[§6Name§8]");
    }
}
