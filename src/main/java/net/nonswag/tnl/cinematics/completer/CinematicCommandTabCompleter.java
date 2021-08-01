package net.nonswag.tnl.cinematics.completer;

import com.google.common.io.Files;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CinematicCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
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
        suggestions.removeIf(tabCompleter -> !tabCompleter.toLowerCase().startsWith(args[args.length - 1].toLowerCase()));
        return suggestions;
    }
}
