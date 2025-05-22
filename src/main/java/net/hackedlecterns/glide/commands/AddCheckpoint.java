package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.model.Checkpoint;
import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddCheckpoint implements CommandExecutor {
    private static int checkpointCounter = 0;

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

        String name = (args.length >= 1) ? args[0] : "checkpoint_" + (++checkpointCounter);
        Course course = EditCourse.getEditingCourse(player);
        CuboidRegion region = new CuboidRegion(pos1, pos2);
        Checkpoint checkpoint = new Checkpoint(name, region, player.getLocation());
        course.getCourseEvents().add(checkpoint);

        player.sendMessage("Checkpoint added.");
        return true;
    }
}