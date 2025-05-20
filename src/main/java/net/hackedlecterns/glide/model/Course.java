package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.game.Game;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Course {
    String name;
    Location spawnLocation;

    CourseEvent finish = new CourseEvent("Finish", null) {
        @Override
        public void onEnter(Player p, Game game) {
            // finish line behavior
        }
    };

    List<CourseEvent> courseEvents = new ArrayList<>();

    public List<CourseEvent> getCourseEvents() {
        return courseEvents;
    }

    void tmpAddEvent(CourseEvent ce) {
        courseEvents.add(ce);
    }
}
