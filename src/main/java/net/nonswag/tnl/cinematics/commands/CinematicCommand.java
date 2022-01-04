package net.nonswag.tnl.cinematics.commands;

import com.google.common.io.Files;
import net.nonswag.tnl.core.api.command.CommandSource;
import net.nonswag.tnl.core.api.command.Invocation;
import net.nonswag.tnl.core.utils.StringUtil;
import net.nonswag.tnl.listener.api.cinematic.Recording;
import net.nonswag.tnl.listener.api.command.TNLCommand;
import net.nonswag.tnl.listener.api.command.exceptions.SourceMismatchException;
import net.nonswag.tnl.listener.api.player.TNLPlayer;
import net.nonswag.tnl.listener.api.player.manager.CinematicManger;
import net.nonswag.tnl.listener.api.player.manager.Messenger;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CinematicCommand extends TNLCommand {

    public CinematicCommand() {
        super("cinematic", "tnl.cinematic");
    }

    @Override
    protected void execute(@Nonnull Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        if (source.isPlayer()) {
            TNLPlayer player = (TNLPlayer) source.player();
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("delete")) {
                    if (args.length >= 2) {
                        Recording recording = Recording.load(args[1]);
                        if (recording != null) {
                            if (recording.delete()) {
                                player.messenger().sendMessage("%prefix% §aDeleted the recording §6" + args[1]);
                            } else player.messenger().sendMessage("%prefix% §cFailed to delete recording");
                        } else player.messenger().sendMessage("%prefix% §cA record with this name does not exist");
                    } else player.messenger().sendMessage("%prefix% §c/cinematic delete §8[§6Record§8]");
                } else if (args[0].equalsIgnoreCase("record")) {
                    CinematicManger cinematicManger = player.cinematicManger();
                    Messenger messenger = player.messenger();
                    if (cinematicManger.isRecording()) {
                        cinematicManger.stopRecording();
                        messenger.sendMessage("%prefix% §aStopped the recording");
                    } else if (args.length >= 2) {
                        String name = args[1];
                        if (!Recording.exists(name)) {
                            messenger.sendMessage("%prefix% §aRecording §6" + name);
                            cinematicManger.record(new Recording(name), (player1, cinematic) -> {
                                player1.messenger().sendMessage("%prefix% §aSaving recording §6" + name);
                                cinematic.export();
                                player1.cinematicManger().play(cinematic);
                            });
                        } else messenger.sendMessage("%prefix% §cA record with this name already exists");
                    } else messenger.sendMessage("%prefix% §c/cinematic record §8[§6Record§8]");
                } else if (args[0].equalsIgnoreCase("play")) {
                    if (args.length >= 2) {
                        Recording recording = Recording.load(args[1]);
                        if (recording != null) player.cinematicManger().play(recording);
                        else player.messenger().sendMessage("%prefix% §cA record with this name does not exist");
                    } else player.messenger().sendMessage("%prefix% §c/cinematic load §8[§6Name§8]");
                } else if (args[0].equalsIgnoreCase("info")) {
                    Messenger messenger = player.messenger();
                    if (args.length >= 2) {
                        Recording recording = Recording.load(args[1]);
                        if (recording != null) {
                            messenger.sendMessage("%prefix% §7Recording§8: §6" + recording.getName());
                            messenger.sendMessage("%prefix% §7Frames§8: §6" + recording.getSteps().size());
                            messenger.sendMessage("%prefix% §7File Size§8: §6" + StringUtil.format("#,##0.0kb", recording.getFile().length() / 1024d));
                        } else messenger.sendMessage("%prefix% §cA record with this name does not exist");
                    } else messenger.sendMessage("%prefix% §c/cinematic info §8[§6Record§8]");
                } else if (args[0].equalsIgnoreCase("list")) {
                    File[] files = Recording.getRecordings();
                    List<String> recordings = new ArrayList<>();
                    for (File file : files) {
                        if (Files.getFileExtension(file.getName()).equalsIgnoreCase("json")) {
                            recordings.add(Files.getNameWithoutExtension(file.getName()));
                        }
                    }
                    String s = recordings.isEmpty() ? "§7-§8/§7-" : String.join("§8, §6", recordings);
                    player.messenger().sendMessage("%prefix% §7Recordings §8(§a" + recordings.size() + "§8): §6" + s);
                } else {
                    Messenger messenger = player.messenger();
                    messenger.sendMessage("%prefix% §c/cinematic delete §8[§6Record§8]");
                    messenger.sendMessage("%prefix% §c/cinematic record §8[§6Name§8]");
                    messenger.sendMessage("%prefix% §c/cinematic play §8[§6Record§8]");
                    messenger.sendMessage("%prefix% §c/cinematic info §8[§6Record§8]");
                    messenger.sendMessage("%prefix% §c/cinematic list");
                }
            } else {
                Messenger messenger = player.messenger();
                messenger.sendMessage("%prefix% §c/cinematic delete §8[§6Record§8]");
                messenger.sendMessage("%prefix% §c/cinematic record §8[§6Name§8]");
                messenger.sendMessage("%prefix% §c/cinematic play §8[§6Record§8]");
                messenger.sendMessage("%prefix% §c/cinematic info §8[§6Record§8]");
                messenger.sendMessage("%prefix% §c/cinematic list");
            }
        } else throw new SourceMismatchException();
    }

    @Nonnull
    @Override
    protected List<String> suggest(@Nonnull Invocation invocation) {
        String[] args = invocation.arguments();
        List<String> suggestions = new ArrayList<>();
        if (args.length <= 1) {
            File[] files = Recording.getRecordings();
            if (files.length >= 1) {
                suggestions.add("delete");
                suggestions.add("play");
                suggestions.add("info");
                suggestions.add("list");
            }
            suggestions.add("record");
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("play") || args[0].equalsIgnoreCase("info")) {
                File[] files = Recording.getRecordings();
                for (File file : files) suggestions.add(Files.getNameWithoutExtension(file.getName()));
            }
        }
        return suggestions;
    }
}
