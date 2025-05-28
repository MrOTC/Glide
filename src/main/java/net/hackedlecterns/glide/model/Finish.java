package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Finish extends CourseEvent {

    public Finish(@Nullable String name, CuboidRegion region) {
        super(name, region);
    }

    @Override
    public void onEnter(Player p, Game game) {
        // finish line behavior
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return super.serialize();
    }

    // deserialize
    public Finish(Map<String, Object> data) {
        super(data);
    }
}
