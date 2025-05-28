package net.hackedlecterns.glide.util;

import com.google.gson.Gson;
import net.hackedlecterns.glide.model.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static net.hackedlecterns.glide.Main.plugin;

@SuppressWarnings("unchecked")
public class CourseImport {
    static Gson parser = new Gson();
    static File DIR = new File(plugin.getDataFolder(), "import");

    public static Course fromGrf(String courseName, String worldName, String filename) throws IOException {
        World world = Objects.requireNonNull(Bukkit.getWorld(worldName));
        Location spawn = world.getSpawnLocation(); // TODO should import a spawnLocation
        Course newCourse = new Course(courseName, spawn);

        List<Object> root = parser.fromJson(new FileReader(new File(DIR, filename)), List.class);
        var levelRulesSection = (Map<String, Object>) root.get(1);
        var levelRules = (List<Object>) levelRulesSection.get("ChildRules");
        for (Object ruleData : levelRules) {
            try {
                var rule = parseRule((Map<String, Object>) ruleData, world);
                if (rule != null) {
                    newCourse.getCourseEvents().add(rule);
                }
            } catch (Exception ex) {
                System.out.println("Skipped adding rule");
                ex.printStackTrace();
            }
        }
        return newCourse;
    }

    private static CourseEvent parseRule(Map<String, Object> data, World world) {
        var ruleType = (String) data.get("Name");
        var params = (Map<String, Object>) data.get("Parameters");
        var children = (List<Map<String, Object>>) data.get("ChildRules");

        if (ruleType.equals("Checkpoint")) {
            var region = regionFromMap(params, world);
            var spawnData = children.stream()
                    .filter(i -> Objects.equals(i.get("Name"), "UpdatePlayer"))
                    .findAny().orElse(null);
            if (spawnData == null) return null;
            var spawnParams = (Map<String, Object>) spawnData.get("Parameters");
            var respawn = new Location(
                    world,
                    Double.parseDouble((String) spawnParams.get("spawnX")),
                    Double.parseDouble((String) spawnParams.get("spawnY")),
                    Double.parseDouble((String) spawnParams.get("spawnZ")),
                    Float.parseFloat((String)   spawnParams.get("yRot")),
                    Float.parseFloat((String)   spawnParams.get("xRot"))
            );
            var desc = (String) params.get("name");
            return new Checkpoint(desc, region, respawn);
        }

        if (ruleType.equals("ThermalArea") && params.get("staticLift") != null) {
            var region = regionFromMap(params, world);
            var staticLift = Double.parseDouble((String) params.get("staticLift"));
            var targetHeight = Integer.parseInt((String) params.get("targetHeight"));
            var desc = (String) params.get("name");
            return new Thermal(desc, region, staticLift, targetHeight);
        }

        if (ruleType.equals("ThermalArea") && params.get("liftForceModifier") != null) {
            var region = regionFromMap(params, world);
            var liftForceModifier = Double.parseDouble((String) params.get("liftForceModifier"));
            var desc = (String) params.get("name");
            return new Thermal(desc, region, liftForceModifier);
        }

        if (ruleType.equals("ThermalArea") && params.get("speedBoost") != null) {
            var region = regionFromMap(params, world);
            var speedBoost = Double.parseDouble((String) params.get("speedBoost"));
            var dir = Boost.BoostDirection.valueOf(((String) params.get("boostDirection")).toUpperCase());
            var desc = (String) params.get("name");
            return new Boost(desc, region, speedBoost, dir);
        }

        return null;
    }

    private static CuboidRegion regionFromMap(Map<String, Object> params, World world) {
        var l1 = new Location(
                world,
                Double.parseDouble((String) params.get("x0")),
                Double.parseDouble((String) params.get("y0")),
                Double.parseDouble((String) params.get("z0"))
        );
        var l2 = new Location(
                world,
                Double.parseDouble((String) params.get("x1")),
                Double.parseDouble((String) params.get("y1")),
                Double.parseDouble((String) params.get("z1"))
        );
        return new CuboidRegion(l1, l2);
    }
}
