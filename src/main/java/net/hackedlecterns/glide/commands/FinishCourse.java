package net.hackedlecterns.glide.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FinishCourse implements CommandExecutor {
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

        EditCourse.clearEditSession(player);
        player.sendMessage("You have exited edit mode.");
        return true;
    }
}