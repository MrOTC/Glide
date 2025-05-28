package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.game.Game;
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

public class Start implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("Glide.start")) {
            sender.sendMessage("You do not have permission");
            return false;
        }

        if (args == null || args.length < 1 || args[0].isEmpty())  {
            sender.sendMessage("Invalid command format");
            return false;
        }

        var course = CourseDAO.getCourse(args[0]);
        if (course == null) {
            sender.sendMessage(String.format("Course %s not found", args[0]));
            return false;
        }

        new Game(Game.GameType.TIME, course).start();
        sender.sendMessage("Game started!");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return CourseDAO.getCourses().stream()
                    .map(Course::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                    .toList();
        }

        return Collections.emptyList();
    }
}
