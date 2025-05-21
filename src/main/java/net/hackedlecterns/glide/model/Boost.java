package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Boost extends CourseEvent {

    public enum BoostDirection {
        PLUS_X, PLUS_Z, MINUS_X, MINUS_Z, OMNI_PLUS_X, OMNI_PLUS_Z, OMNI_MINUS_X, OMNI_MINUS_Z
    }

    private final double speedBoost;
    private final BoostDirection direction;

    public Boost(String name, CuboidRegion region, double speedBoost, BoostDirection direction) {
        super(name, region);
        this.speedBoost = speedBoost;
        this.direction = direction;
    }

    public double getSpeedBoost() {
        return speedBoost;
    }

    public BoostDirection getDirection() {
        return direction;
    }

    @Override
    public void onEnter(Player player, Game game) {
        // TODO copied from boost1
        float velocity = 2f;
        Vector pv = player.getLocation().getDirection();
        Vector v = pv.multiply(velocity);
        player.setVelocity(v);
    }
}
