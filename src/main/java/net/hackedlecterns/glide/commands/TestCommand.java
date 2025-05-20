package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.model.Boost;
import net.hackedlecterns.glide.model.Checkpoint;
import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    Location testLoc1 = null;
    Location testLoc2 = null;
    Game testGame = new Game(Game.GameType.TIME, new Course(null, null));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }

        sender.sendMessage("executing testcmd!");
        switch (args[0]) {
            case "pos1" -> testLoc1 = player.getLocation().clone();
            case "pos2" -> testLoc2 = player.getLocation().clone();
            case "start" -> {
                sender.sendMessage("Starting game");
                testGame.getCourse().getCourseEvents().add(new Boost(null, new CuboidRegion(testLoc1, testLoc2), 10, Boost.BoostDirection.OMNI_MINUS_X));
                testGame.start();
                sender.sendMessage(testGame.getCourse().getCourseEvents().get(0).getRegion().getCenter().toString());
            }
        }
        return true;
    }
}
