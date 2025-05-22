package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.model.Boost;
import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static net.hackedlecterns.glide.commands.Pos1.pos1Map;
import static net.hackedlecterns.glide.commands.Pos2.pos2Map;

public class AddBoost implements CommandExecutor, TabCompleter {
    private static int boostCounter = 0;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        if (!EditCourse.isEditing(player)) {
            player.sendMessage("You are not currently editing any course.");
            return false;
        }

        var pos1 = pos1Map.get(player.getUniqueId());
        var pos2 = pos2Map.get(player.getUniqueId());
        if (pos1 == null || pos2 == null) {
            player.sendMessage("Please set both /pos1 and /pos2 first.");
            return false;
        }

        if (args.length < 2) {
            player.sendMessage("Invalid command format.");
            return false;
        }

        double speed = Double.parseDouble(args[0]);
        Boost.BoostDirection direction;
        try {
            direction = Boost.BoostDirection.valueOf(args[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage("Invalid direction. Valid directions: " + Arrays.toString(Boost.BoostDirection.values()));
            return false;
        }

        String name = (args.length >= 3) ? args[2] : "boost_" + (++boostCounter);

        Course course = EditCourse.getEditingCourse(player);
        CuboidRegion region = new CuboidRegion(pos1, pos2);
        Boost boost = new Boost(name, region, speed, direction);
        course.getCourseEvents().add(boost);

        player.sendMessage("Boost added.");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 2) {
            return Arrays.stream(Boost.BoostDirection.values())
                    .map(Enum::name).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}