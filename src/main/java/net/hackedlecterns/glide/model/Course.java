package net.hackedlecterns.glide.model;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String name;
    private final Location spawnLocation;
    private final List<CourseEvent> courseEvents = new ArrayList<>();

    public Course(String name, Location spawnLocation) {
        this.name = name;
        this.spawnLocation = spawnLocation;
    }

    public String getName() {
        return name;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public List<CourseEvent> getCourseEvents() {
        return courseEvents;
    }
}
