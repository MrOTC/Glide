package net.hackedlecterns.glide.commands;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.persistence.CourseDAO;
import net.hackedlecterns.glide.util.CourseImport;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;

public class TestCommand implements CommandExecutor {

    Location testLoc1 = null;
    Location testLoc2 = null;

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
                Game game = new Game(Game.GameType.TIME, Objects.requireNonNull(CourseDAO.courses.get(args[1])));
                game.start();
                sender.sendMessage("Starting game");
//                testGame.getCourse().getCourseEvents().add(new Boost(null, new CuboidRegion(testLoc1, testLoc2), 10, Boost.BoostDirection.OMNI_MINUS_X));
//                testGame.start();
//                sender.sendMessage(testGame.getCourse().getCourseEvents().get(0).getRegion().getCenter().toString());
            }
            case "import" -> {
                try {
                    CourseDAO.courses.put(args[1], CourseImport.fromGrf(args[1], args[2], args[3]));
                } catch (IOException e) {
                    e.printStackTrace(); // TODO
                }
            }
            case "save" -> {
                try {
                    CourseDAO.save();
                } catch (IOException e) {
                    e.printStackTrace(); // TODO
                }
            }
            case "read" -> {
                try {
                    CourseDAO.read();
                } catch (IOException e) {
                    e.printStackTrace(); // TODO
                }
            }
        }
        return true;
    }
}
