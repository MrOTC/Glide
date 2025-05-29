package net.hackedlecterns.glide.game;

import net.hackedlecterns.glide.model.Checkpoint;
import net.hackedlecterns.glide.model.Course;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private Map<Player, LinkedHashSet<Checkpoint>> checkpoints;

    public Game(GameType type, Course course) {
        this.type = type;
        this.course = course;
        this.state = GameState.WAITING;
    }

    public void start() {
        checkpoints = players.stream().collect(Collectors.toMap(Function.identity(), i -> new LinkedHashSet<>()));
        for (Player p : players) {
            p.saveData();
            p.teleport(course.getSpawnLocation());
            p.setHealth(6);
            p.setFoodLevel(6);

            ItemStack chestplate = new ItemStack(Material.ELYTRA);
            chestplate.addEnchantment(Enchantment.BINDING_CURSE, 1);
            p.getInventory().setChestplate(chestplate);
            p.setGameMode(GameMode.ADVENTURE);
            p.setFlying(false);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                p.setGliding(true);
            }, 5);
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

    public Map<Player, LinkedHashSet<Checkpoint>> getCheckpoints() {
        return checkpoints;
    }
}
