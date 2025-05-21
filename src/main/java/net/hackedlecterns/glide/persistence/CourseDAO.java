package net.hackedlecterns.glide.persistence;

import net.hackedlecterns.glide.model.Course;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static net.hackedlecterns.glide.Glide.plugin;

public class CourseDAO {
    private static final File FILE = new File(plugin.getDataFolder(), "courses.yml");
    public static Map<String, Course> courses = new HashMap<>();

    public static void save() throws IOException {
        if (!FILE.exists() && !FILE.createNewFile()) throw new IOException();
        new Yaml().dump(courses, new FileWriter(FILE));
    }

    public static void read() throws IOException {
        if (!FILE.exists() && !FILE.createNewFile()) throw new IOException();
        new Yaml().load(new FileReader(FILE));
    }
}
