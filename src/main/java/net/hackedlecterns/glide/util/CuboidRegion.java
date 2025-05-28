package net.hackedlecterns.glide.util;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CuboidRegion implements ConfigurationSerializable {

    private final Location pos1;
    private final Location pos2;

    public CuboidRegion(Location pos1, Location pos2) {
        assert pos1.getWorld() == pos2.getWorld();
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public Location getPos2() {
        return pos2;
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getCenter() {
        return pos1.clone().add(pos2).multiply(0.5);
    }

    public boolean contains(Location l) {
        return l.getWorld() == pos1.getWorld()
            && isInBetween(l.getBlockX(), pos1.getBlockX(), pos2.getBlockX())
            && isInBetween(l.getBlockY(), pos1.getBlockY(), pos2.getBlockY())
            && isInBetween(l.getBlockZ(), pos1.getBlockZ(), pos2.getBlockZ());
    }

    private boolean isInBetween(double a, double b1, double b2) {
        return Math.min(b1, b2) <= a && a <= Math.max(b1, b2);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        var m = new HashMap<String, Object>();
        m.put("pos1", pos1);
        m.put("pos2", pos2);
        return m;
    }

    // deserialize
    public CuboidRegion(Map<String, Object> data) {
        this.pos1 = (Location) data.getOrDefault("pos1", null);
        this.pos2 = (Location) data.getOrDefault("pos2", null);
    }
}
