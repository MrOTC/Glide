package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.persistence.CourseDAO;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class EditCourse implements CommandExecutor, TabCompleter {

    private static final Map<UUID, Course> editingCourses = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("Glide.editcourse")) {
            sender.sendMessage("You do not have permission to edit courses.");
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can edit courses.");
            return false;
        }

        if (args.length < 1 || args[0].isEmpty()) {
            player.sendMessage("Invalid command format.");
            return false;
        }

        String name = args[0];
        Course course = CourseDAO.courses.get(name);
        if (course == null) {
            player.sendMessage("Course not found.");
            return false;
        }

        editingCourses.put(player.getUniqueId(), course);

        player.sendMessage("You are now editing course: " + name);
        player.sendMessage("Use /addboost, /addcheckpoint, /addthermal, /setstart, /setfinish, /pos1, and /pos2.");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
                                                @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return CourseDAO.courses.keySet().stream().toList();
        }
        return Collections.emptyList();
    }

    public static @Nullable Course getEditingCourse(Player player) {
        return editingCourses.get(player.getUniqueId());
    }

    public static void clearEditSession(Player player) {
        editingCourses.remove(player.getUniqueId());
    }

    public static boolean isEditing(Player player) {
        return editingCourses.containsKey(player.getUniqueId());
    }
}