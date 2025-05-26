package net.hackedlecterns.glide;

import net.hackedlecterns.glide.commands.Glide;
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
    }
}
