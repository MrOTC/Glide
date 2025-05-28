package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

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

    @Override
    public @NotNull Map<String, Object> serialize() {
        var m = super.serialize();
        m.put("spawn", spawn);
        return m;
    }

    // deserialize
    public Checkpoint(Map<String, Object> data) {
        super(data);
        this.spawn = (Location) data.getOrDefault("spawn", null);
    }
}
