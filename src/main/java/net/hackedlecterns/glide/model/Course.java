package net.hackedlecterns.glide.model;

import net.hackedlecterns.glide.util.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Course {
    String name;
    Location spawnLocation;

    CourseEvent finish = new CourseEvent("Finish", null) {
        @Override
        public void onEnter(Player p) {
            // finish line behavior
        }
    };

    List<CourseEvent> mapItems = new ArrayList<>();
}
