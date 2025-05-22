package net.hackedlecterns.glide;

import net.hackedlecterns.glide.commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Arrays;

public final class Glide extends JavaPlugin {

    public static Glide plugin;

    public Glide() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        this.getCommand("testCommand").setExecutor(new TestCommand());
        this.getCommand("addboost").setExecutor(new AddBoost());
        this.getCommand("addcheckpoint").setExecutor(new AddCheckpoint());
        this.getCommand("addthermal").setExecutor(new AddThermal());
        this.getCommand("createcourse").setExecutor(new CreateCourse());
        this.getCommand("editcourse").setExecutor(new EditCourse());
        this.getCommand("finishcourse").setExecutor(new FinishCourse());
        this.getCommand("pos1").setExecutor(new Pos1());
        this.getCommand("pos2").setExecutor(new Pos2());
        this.getCommand("removecourse").setExecutor(new RemoveCourse());
        this.getCommand("setfinish").setExecutor(new SetFinish());
        this.getCommand("setstart").setExecutor(new SetStart());
        this.getCommand("start").setExecutor(new Start());
    }

    @Override
    public void onDisable() {

    }

    float velocity = 1.5F;
    int setDelay = 4;

    public void power2(Player player){
        //System.out.println("Executeing power 2...");
        //this.delay.put(player.getName(), System.currentTimeMillis() + (this.setDelay * 1000L));
        //Vector pv = player.getLocation().getDirection();
        //double px = pv.getX();
        //double py = pv.getY();
        //double pz = pv.getZ();
        double vx = player.getVelocity().getX();
        double vy = player.getVelocity().getY();
        double vz = player.getVelocity().getZ();
        //System.out.println(" - vx:" + vx);
        //System.out.println(" - vy:" + vy);
        //System.out.println(" - vz:" + vz);
        Vector vNew = new Vector();
        vNew.setX(vx);
        vNew.setY(1);
        vNew.setZ(vz);
        //Vector v = pv.multiply(velocity);
        player.setVelocity(vNew);
    }

    public void power3x(Player player){
        //System.out.println("Executeing power 2...");
        //this.delay.put(player.getName(), System.currentTimeMillis() + (this.setDelay * 1000L));
        //Vector pv = player.getLocation().getDirection();
        //double px = pv.getX();
        //double py = pv.getY();
        //double pz = pv.getZ();
        double vx = player.getVelocity().getX();
        double vy = player.getVelocity().getY();
        double vz = player.getVelocity().getZ();
        //System.out.println(" - vx:" + vx);
        //System.out.println(" - vy:" + vy);
        //System.out.println(" - vz:" + vz);
        Vector vNew = new Vector();
        vNew.setX(vx+1);
        vNew.setY(vy);
        vNew.setZ(vz);
        //Vector v = pv.multiply(velocity);
        player.setVelocity(vNew);
    }

    public void power3nx(Player player){
        //System.out.println("Executeing power 2...");
        //this.delay.put(player.getName(), System.currentTimeMillis() + (this.setDelay * 1000L));
        //Vector pv = player.getLocation().getDirection();
        //double px = pv.getX();
        //double py = pv.getY();
        //double pz = pv.getZ();
        double vx = player.getVelocity().getX();
        double vy = player.getVelocity().getY();
        double vz = player.getVelocity().getZ();
        //System.out.println(" - vx:" + vx);
        //System.out.println(" - vy:" + vy);
        //System.out.println(" - vz:" + vz);
        Vector vNew = new Vector();
        vNew.setX(vx-1);
        vNew.setY(vy);
        vNew.setZ(vz);
        //Vector v = pv.multiply(velocity);
        player.setVelocity(vNew);
    }

    public void power3nz(Player player){
        //System.out.println("Executeing power 2...");
        //this.delay.put(player.getName(), System.currentTimeMillis() + (this.setDelay * 1000L));
        //Vector pv = player.getLocation().getDirection();
        //double px = pv.getX();
        //double py = pv.getY();
        //double pz = pv.getZ();
        double vx = player.getVelocity().getX();
        double vy = player.getVelocity().getY();
        double vz = player.getVelocity().getZ();
        //System.out.println(" - vx:" + vx);
        //System.out.println(" - vy:" + vy);
        //System.out.println(" - vz:" + vz);
        Vector vNew = new Vector();
        vNew.setX(vx);
        vNew.setY(vy);
        vNew.setZ(vz-1);
        //Vector v = pv.multiply(velocity);
        player.setVelocity(vNew);
    }

    public void power3z(Player player){
        //System.out.println("Executeing power 2...");
        //this.delay.put(player.getName(), System.currentTimeMillis() + (this.setDelay * 1000L));
        //Vector pv = player.getLocation().getDirection();
        //double px = pv.getX();
        //double py = pv.getY();
        //double pz = pv.getZ();
        double vx = player.getVelocity().getX();
        double vy = player.getVelocity().getY();
        double vz = player.getVelocity().getZ();
        //System.out.println(" - vx:" + vx);
        //System.out.println(" - vy:" + vy);
        //System.out.println(" - vz:" + vz);
        Vector vNew = new Vector();
        vNew.setX(vx);
        vNew.setY(vy);
        vNew.setZ(vz+1);
        //Vector v = pv.multiply(velocity);
        player.setVelocity(vNew);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        System.out.println(sender.getName() + cmd + label + Arrays.toString(args));
        if (label.equals("boost2")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                power2(player);
                return true;
            }
        }
        if (label.equals("boost3x")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                power3x(player);
                return true;
            }
        }
        if (label.equals("boost3z")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                power3z(player);
                return true;
            }
        }
        if (label.equals("boost3nx")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                power3nx(player);
                return true;
            }
        }
        if (label.equals("boost3nz")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                power3nz(player);
                return true;
            }
        }
        //else {
        //sender.sendMessage("helo console");
        //return true;
        //}

        return false;
    }
}
