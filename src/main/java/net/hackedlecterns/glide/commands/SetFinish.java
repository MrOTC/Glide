package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.model.Finish;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.hackedlecterns.glide.commands.Pos1.pos1Map;
import static net.hackedlecterns.glide.commands.Pos2.pos2Map;

public class SetFinish implements CommandExecutor {
    private static int finishCounter = 0;

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

        Location pos1 = pos1Map.get(player.getUniqueId());
        Location pos2 = pos2Map.get(player.getUniqueId());

        if (pos1 == null || pos2 == null) {
            player.sendMessage("You must set both /pos1 and /pos2 before setting a finish.");
            return false;
        }

        Course course = EditCourse.getEditingCourse(player);
        String name = "finish_" + (++finishCounter);
        CuboidRegion region = new CuboidRegion(pos1, pos2);

        Finish finish = new Finish(name, region);
        course.getCourseEvents().add(finish);

        player.sendMessage("Finish region set.");
        return true;
    }
}
