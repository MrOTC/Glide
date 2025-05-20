package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.entity.Player;

public class Checkpoint extends CourseEvent {

    public Checkpoint(String name, CuboidRegion region) {
        super(name, region);
    }

    @Override
    public void onEnter(Player player, Game game) {
        // TODO
        player.sendMessage("Checkpoint reached!");
    }
}
