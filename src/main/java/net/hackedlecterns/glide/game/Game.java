package net.hackedlecterns.glide.game;

import net.hackedlecterns.glide.model.Course;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        WAITING,
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
        }
        this.state = GameState.PLAYING;
        Bukkit.getServer().getPluginManager().registerEvents(new GameEventListener(this), plugin);
    }

    public Course getCourse() {
        return course;
    }

    public Collection<Player> getPlayers() {
        return players;
    }
}
