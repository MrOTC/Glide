package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.persistence.CourseDAO;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class CreateCourse implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("Glide.createcourse")) {
            sender.sendMessage("You do not have permission to create a course.");
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can create courses.");
            return false;
        }

        if (args.length < 1 || args[0].isEmpty()) {
            sender.sendMessage("Invalid command format.");
            return false;
        }

        String name = args[0];
        if (CourseDAO.containsCourse(name)) {
            sender.sendMessage("A course with that name already exists.");
            return false;
        }

        Location loc = player.getLocation();
        Course course = new Course(name, loc);
        CourseDAO.addCourse(course);

        sender.sendMessage("Course '" + name + "' created with spawn at your current location.");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return Collections.emptyList();
    }
}