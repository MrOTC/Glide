package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.util.CuboidRegion;

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
}
