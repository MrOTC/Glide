package net.hackedlecterns.glide.persistence;

import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.util.CustomConfig;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseDAO {
    private static final CustomConfig CONFIG = new CustomConfig("courses.yml");

    public static Map<String, Course> courses = new HashMap<>();

    public static void save() throws IOException {
        CONFIG.getRoot().set("courses", courses.values().toArray());
        CONFIG.saveConfig();
    }

    @SuppressWarnings("unchecked")
    public static void read() throws IOException {
        var courseList = (List<Course>) CONFIG.getRoot().getList("courses");
        Objects.requireNonNull(courseList);
        courses = courseList.stream().collect(Collectors.toMap(Course::getName, Function.identity()));
    }
}
