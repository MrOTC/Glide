package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class CourseEvent {

    private final String name;
    private final CuboidRegion region;

    public CourseEvent(@Nullable String name, CuboidRegion region) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return Objects.requireNonNullElse(name, this.getClass().getSimpleName());
    }

    public CuboidRegion getRegion() {
        return this.region;
    }

    public void onEnter(Player p) {}

    public void onLeave(Player p) {}

    @Override
    public String toString() {
        return "CourseEvent{" +
                "name='" + name + '\'' +
                ", regionCenter=" + region.getCenter() +
                '}';
    }
}
