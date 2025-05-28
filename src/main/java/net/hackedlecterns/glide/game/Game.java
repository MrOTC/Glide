package net.hackedlecterns.glide.game;

import net.hackedlecterns.glide.model.Course;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static net.hackedlecterns.glide.Main.plugin;

public class Game {

    public enum GameType {
        TIME,
        SCORE
    }

    enum GameState {
        WAITING, // in lobby waiting for players to join
        STARTING, // in course waiting for 3-second countdown
        PLAYING,
        FINISHED
    }

    private final GameType type;
    private final Course course;
    private GameState state;
    private final Set<Player> players = new HashSet<>();

    public Game(GameType type, Course course) {
        this.type = type;
        this.course = course;
        this.state = GameState.WAITING;
    }

    public void start() {
        for (Player p : players) {
            p.saveData();
            p.setHealth(6);
            p.teleport(course.getSpawnLocation());
            p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
            p.setFlying(true);
        }
        this.state = GameState.STARTING;
        Bukkit.getServer().getPluginManager().registerEvents(new GameEventListener(this), plugin);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            players.forEach(p -> p.sendMessage("Start"));
            this.state = GameState.PLAYING;
        }, 10);
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public Course getCourse() {
        return course;
    }

    public Collection<Player> getPlayers() {
        return players;
    }
}
