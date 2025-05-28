package net.hackedlecterns.glide.model;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Course implements ConfigurationSerializable {
    private final String name;
    private Location spawnLocation;
    private final List<CourseEvent> courseEvents;

    public Course(String name, Location spawnLocation) {
        this.name = name;
        this.spawnLocation = spawnLocation;
        this.courseEvents = new ArrayList<>();
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

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        var m = new HashMap<String, Object>();
        m.put("name", name);
        m.put("spawnLocation", spawnLocation);
        m.put("courseEvents", courseEvents);
        return m;
    }

    // deserialize
    @SuppressWarnings("unchecked")
    public Course(Map<String, Object> data) {
        this.name = (String) data.getOrDefault("name", null);
        this.spawnLocation = (Location) data.getOrDefault("spawnLocation", null);
        this.courseEvents = (List<CourseEvent>) data.getOrDefault("courseEvents", Collections.emptyList());
    }
}
