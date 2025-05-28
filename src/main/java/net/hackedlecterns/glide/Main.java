package net.hackedlecterns.glide;

import net.hackedlecterns.glide.commands.Glide;
import net.hackedlecterns.glide.model.*;
import net.hackedlecterns.glide.persistence.CourseDAO;
import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        ConfigurationSerialization.registerClass(Boost.class);
        ConfigurationSerialization.registerClass(Checkpoint.class);
        ConfigurationSerialization.registerClass(Course.class);
        ConfigurationSerialization.registerClass(CourseEvent.class);
        ConfigurationSerialization.registerClass(Finish.class);
        ConfigurationSerialization.registerClass(Thermal.class);
        ConfigurationSerialization.registerClass(CuboidRegion.class);

        Objects.requireNonNull(this.getCommand("glide")).setExecutor(new Glide());

        CourseDAO.read();
    }
}
