package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Checkpoint extends CourseEvent {

    Location spawn;

    public Checkpoint(String name, CuboidRegion region, Location spawn) {
        super(name, region);
        this.spawn = spawn;
    }

    public Location getSpawn() {
        return spawn;
    }

    @Override
    public void onEnter(Player player, Game game) {
        // TODO
        player.sendMessage("Checkpoint reached!");
    }
}
