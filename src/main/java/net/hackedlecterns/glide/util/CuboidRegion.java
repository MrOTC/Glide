package net.hackedlecterns.glide.util;

import org.bukkit.Location;

public class CuboidRegion {

    private final Location pos1;
    private final Location pos2;

    public CuboidRegion(Location pos1, Location pos2) {
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
        return pos1.add(pos2).multiply(0.5);
    }

}
