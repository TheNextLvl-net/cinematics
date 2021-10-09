package net.nonswag.tnl.cinematics.commands;

import com.google.common.io.Files;
import net.nonswag.tnl.core.api.command.CommandSource;
import net.nonswag.tnl.core.api.command.Invocation;
import net.nonswag.tnl.core.api.message.Message;
import net.nonswag.tnl.listener.api.animation.Animation;
import net.nonswag.tnl.listener.api.animation.Recording;
import net.nonswag.tnl.listener.api.command.TNLCommand;
import net.nonswag.tnl.listener.api.player.TNLPlayer;

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
                        if (Animation.exists(args[1])) {
                            File animation = new File("plugins/Animations/" + args[1] + ".json");
                            if (animation.delete()) player.sendMessage("%prefix% §aDeleted the recording §6" + args[1]);
                            else player.sendMessage("%prefix% §cFailed to delete recording");
                        } else player.sendMessage("%prefix% §cA record with this name does not exist");
                    } else player.sendMessage("%prefix% §c/cinematic delete §8[§6Record§8]");
                } else if (args[0].equalsIgnoreCase("record")) {
                    if (args.length >= 2) {
                        if (player.getVirtualStorage().containsKey("recording")) {
                            String recording = player.getVirtualStorage().get("recording", String.class).nonnull();
                            if (recording.equals(args[1])) player.getVirtualStorage().remove("recording");
                            else {
                                player.sendMessage("%prefix% §cYou are recording the animation §4" + recording);
                            }
                        } else {
                            if (!Animation.exists(args[1])) {
                                player.getVirtualStorage().put("recording", args[1]);
                                player.sendMessage("%prefix% §aRecording §6" + args[1]);
                                player.recordCinematic(new Recording(args[1], cinematic -> {
                                    player.sendMessage("%prefix% §aSaving recording §6" + args[1]);
                                    cinematic.animation().export();
                                    player.loadCinematic(cinematic.animation()).play(null);
                                }, tnlPlayer -> tnlPlayer.getVirtualStorage().containsKey("recording") &&
                                        tnlPlayer.getVirtualStorage().get("recording").nonnull().equals(args[1]), 20));
                            } else player.sendMessage("%prefix% §cA record with this name already exists");
                        }
                    } else player.sendMessage("%prefix% §c/cinematic record §8[§6Record§8]");
                } else if (args[0].equalsIgnoreCase("play")) {
                    if (args.length >= 2) {
                        if (Animation.exists(args[1])) {
                            player.loadCinematic(Animation.load(args[1]).nonnull()).play(null);
                        } else player.sendMessage("%prefix% §cA record with this name does not exist");
                    } else player.sendMessage("%prefix% §c/cinematic load §8[§6Name§8]");
                } else if (args[0].equalsIgnoreCase("info")) {
                    if (args.length >= 2) {
                        if (Animation.exists(args[1])) {
                            Animation animation = Animation.load(args[1]).nonnull();
                            player.sendMessage("%prefix% §7Recording§8: §6" + animation.getName());
                            player.sendMessage("%prefix% §7Frames§8: §6" + animation.getSteps().size());
                            File file = new File("plugins/Animations/" + animation.getName() + ".json");
                            player.sendMessage("%prefix% §7File Size§8: §6" + ((file.length() / 1024d) / 1024d) + "MB");
                        } else player.sendMessage("%prefix% §cA record with this name does not exist");
                    } else player.sendMessage("%prefix% §c/cinematic info §8[§6Record§8]");
                } else if (args[0].equalsIgnoreCase("list")) {
                    File animations = new File("plugins/Animations");
                    File[] files = animations.listFiles();
                    List<String> recordings = new ArrayList<>();
                    if (files != null) {
                        for (File file : files) {
                            if (Files.getFileExtension(file.getName()).equalsIgnoreCase("json")) {
                                recordings.add(Files.getNameWithoutExtension(file.getName()));
                            }
                        }
                    }
                    String s = recordings.isEmpty() ? "§7-§8/§7-" : String.join("§8, §6", recordings);
                    player.sendMessage("%prefix% §7Recordings §8(§a" + recordings.size() + "§8): §6" + s);
                } else {
                    player.sendMessage("%prefix% §c/cinematic delete §8[§6Record§8]");
                    player.sendMessage("%prefix% §c/cinematic record §8[§6Name§8]");
                    player.sendMessage("%prefix% §c/cinematic play §8[§6Record§8]");
                    player.sendMessage("%prefix% §c/cinematic info §8[§6Record§8]");
                    player.sendMessage("%prefix% §c/cinematic list");
                }
            } else {
                player.sendMessage("%prefix% §c/cinematic delete §8[§6Record§8]");
                player.sendMessage("%prefix% §c/cinematic record §8[§6Name§8]");
                player.sendMessage("%prefix% §c/cinematic play §8[§6Record§8]");
                player.sendMessage("%prefix% §c/cinematic info §8[§6Record§8]");
                player.sendMessage("%prefix% §c/cinematic list");
            }
        } else source.sendMessage(Message.PLAYER_COMMAND_EN.getText());
    }

    @Nonnull
    @Override
    protected List<String> suggest(@Nonnull Invocation invocation) {
        String[] args = invocation.arguments();
        List<String> suggestions = new ArrayList<>();
        if (args.length <= 1) {
            File animations = new File("plugins/Animations");
            File[] files = animations.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (Files.getFileExtension(file.getName()).equalsIgnoreCase("json")) {
                        suggestions.add("delete");
                        suggestions.add("play");
                        suggestions.add("info");
                        suggestions.add("list");
                        break;
                    }
                }
            }
            suggestions.add("record");
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("play") || args[0].equalsIgnoreCase("info")) {
                File animations = new File("plugins/Animations");
                File[] files = animations.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (Files.getFileExtension(file.getName()).equalsIgnoreCase("json")) {
                            suggestions.add(Files.getNameWithoutExtension(file.getName()));
                        }
                    }
                }
            }
        }
        return suggestions;
    }
}
