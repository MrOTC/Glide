package net.hackedlecterns.glide;

import net.hackedlecterns.glide.commands.Glide;
import net.hackedlecterns.glide.model.*;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public Main() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("glide")).setExecutor(new Glide());
        ConfigurationSerialization.registerClass(Boost.class);
        ConfigurationSerialization.registerClass(Checkpoint.class);
        ConfigurationSerialization.registerClass(Course.class);
        ConfigurationSerialization.registerClass(CourseEvent.class);
        ConfigurationSerialization.registerClass(Finish.class);
        ConfigurationSerialization.registerClass(Thermal.class);
        ConfigurationSerialization.registerClass(CuboidRegion.class);
    }
}
