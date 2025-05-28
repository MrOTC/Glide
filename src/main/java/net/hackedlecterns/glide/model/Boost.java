package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

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
        Vector v = switch (direction) {
            case PLUS_X -> new Vector(speedBoost, 0, 0);
            case PLUS_Z -> new Vector(0, 0, speedBoost);
            case MINUS_X -> new Vector(-speedBoost, 0, 0);
            case MINUS_Z -> new Vector(0, 0, -speedBoost);
            default -> player.getLocation().getDirection().multiply(speedBoost);
        };
        player.setVelocity(v);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        var m = super.serialize();
        m.put("speedBoost", speedBoost);
        m.put("direction", direction.name());
        return m;
    }

    // deserialize
    public Boost(Map<String, Object> data) {
        super(data);
        this.speedBoost = (Double) data.getOrDefault("speedBoost", 0.0);
        this.direction = BoostDirection.valueOf((String) data.getOrDefault("direction", BoostDirection.OMNI_PLUS_X.name()));
    }
}
