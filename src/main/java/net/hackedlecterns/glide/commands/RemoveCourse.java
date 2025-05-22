package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.persistence.CourseDAO;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class RemoveCourse implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("Glide.removecourse")) {
            sender.sendMessage("You do not have permission to remove a course.");
            return false;
        }

        if (args.length < 1 || args[0].isEmpty()) {
            sender.sendMessage("Invalid command format.");
            return false;
        }

        String name = args[0];
        Course removed = CourseDAO.courses.remove(name);

        if (removed == null) {
            sender.sendMessage("No course named '" + name + "' was found.");
            return false;
        }

        sender.sendMessage("Course '" + name + "' has been removed.");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return CourseDAO.courses.keySet().stream().toList();
        }

        return Collections.emptyList();
    }
}