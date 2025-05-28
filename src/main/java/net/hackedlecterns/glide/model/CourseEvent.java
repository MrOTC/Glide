package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class CourseEvent implements ConfigurationSerializable {

    protected final String name;
    protected final CuboidRegion region;

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

    public void onEnter(Player player, Game game) {}

    public void onLeave(Player player, Game game) {}

    @Override
    public String toString() {
        return "CourseEvent{" +
                "name='" + name + '\'' +
                ", regionCenter=" + region.getCenter() +
                '}';
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        var m = new HashMap<String, Object>();
        m.put("name", name);
        m.put("region", region);
        return m;
    }

    // deserialize
    public CourseEvent(Map<String, Object> data) {
        this.name = (String) data.getOrDefault("name", null);
        this.region = (CuboidRegion) data.getOrDefault("region", null);
    }
}
