package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Thermal extends CourseEvent {

    private final double liftForceModifier;
    private final double staticLift;
    private final int targetHeight;

    public Thermal(String name, CuboidRegion region, double liftForceModifier) {
        super(name, region);
        this.staticLift = 0;
        this.targetHeight = 0;
        this.liftForceModifier = liftForceModifier;
    }

    public Thermal(String name, CuboidRegion region, double staticLift, int targetHeight) {
        super(name, region);
        this.staticLift = staticLift;
        this.targetHeight = targetHeight;
        this.liftForceModifier = 0;
    }

    public double getLiftForceModifier() {
        return liftForceModifier;
    }

    public double getStaticLift() {
        return staticLift;
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    @Override
    public void onEnter(Player player, Game game) {
        player.setGravity(false);
        player.setVelocity(new Vector(player.getVelocity().getX(), staticLift * 1.5, player.getVelocity().getZ()));
    }

    @Override
    public void onLeave(Player player, Game game) {
        player.setGravity(true);
    }
}
