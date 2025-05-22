package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.model.Boost;
import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddBoost implements CommandExecutor {
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

        var pos1 = Pos1.pos1Map.get(player.getUniqueId());
        var pos2 = Pos2.pos2Map.get(player.getUniqueId());
        if (pos1 == null || pos2 == null) {
            player.sendMessage("Please set both /pos1 and /pos2 first.");
            return false;
        }

        if (args.length < 2) {
            player.sendMessage("Usage: /addboost <speed> <direction> [name]");
            return false;
        }

        double speed = Double.parseDouble(args[0]);
        Boost.BoostDirection direction = Boost.BoostDirection.valueOf(args[1].toUpperCase());
        String name = (args.length >= 3) ? args[2] : "boost_" + (++boostCounter);

        Course course = EditCourse.getEditingCourse(player);
        CuboidRegion region = new CuboidRegion(pos1, pos2);
        Boost boost = new Boost(name, region, speed, direction);
        course.getCourseEvents().add(boost);

        player.sendMessage("Boost added.");
        return true;
    }
}