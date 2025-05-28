package net.hackedlecterns.glide.persistence;

import net.hackedlecterns.glide.model.Course;
import net.hackedlecterns.glide.util.CustomConfig;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.hackedlecterns.glide.Main.plugin;

public class CourseDAO {
    private static final CustomConfig CONFIG = new CustomConfig("courses.yml");

    private static Map<String, Course> courses = new HashMap<>();

    public static boolean addCourse(Course course) {
        if (courses.containsKey(course.getName())) {
            return false;
        } else {
            courses.put(course.getName(), course);
            save();
            return true;
        }
    }

    public static boolean removeCourse(String name) {
        if (courses.containsKey(name)) {
            courses.remove(name);
            save();
            return true;
        } else {
            return false;
        }
    }

    public static boolean containsCourse(String name) {
        return courses.containsKey(name);
    }

    public static Course getCourse(String name) {
        return courses.get(name);
    }

    public static Collection<Course> getCourses() {
        return courses.values();
    }

    public static void save() {
        CONFIG.getRoot().set("courses", courses.values().toArray());
        CONFIG.saveConfig();
    }

    @SuppressWarnings("unchecked")
    public static void read() {
        var courseList = (List<Course>) CONFIG.getRoot().getList("courses");
        Objects.requireNonNull(courseList);
        courses = courseList.stream().collect(Collectors.toMap(Course::getName, Function.identity()));
        plugin.getLogger().info(String.format("Loaded %s course(s)", courses.size()));
    }
}
