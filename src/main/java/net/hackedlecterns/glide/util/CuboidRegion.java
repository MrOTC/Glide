package net.hackedlecterns.glide.util;

import org.bukkit.Location;

public class CuboidRegion {

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
}
