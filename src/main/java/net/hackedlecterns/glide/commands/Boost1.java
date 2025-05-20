package net.hackedlecterns.glide.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Boost1 implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            float velocity = 1.5f;
            Vector pv = player.getLocation().getDirection();
            Vector v = pv.multiply(velocity);
            player.setVelocity(v);  
            return true;
        } else {
            System.out.println("You are not a player.");
            return false;
        }
    }
}

