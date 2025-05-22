package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.model.Thermal;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddThermal implements CommandExecutor {
    private static int thermalCounter = 0;

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

        if (args.length < 1) {
            player.sendMessage("Invalid command format.");
            return false;
        }

        double lift = Double.parseDouble(args[0]);
        String name = (args.length >= 2) ? args[1] : "thermal_" + (++thermalCounter);

        Course course = EditCourse.getEditingCourse(player);
        CuboidRegion region = new CuboidRegion(pos1, pos2);
        Thermal thermal = new Thermal(name, region, lift);
        course.getCourseEvents().add(thermal);

        player.sendMessage("Thermal added.");
        return true;
    }
}