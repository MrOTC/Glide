package net.hackedlecterns.glide.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Glide implements CommandExecutor, TabCompleter {

    SubCommand.Condition checkEdit = s -> s instanceof Player p && EditCourse.isEditing(p);

    SubCommand[] subCommands = {
            new SubCommand("testCommand",   null,                  new TestCommand(),   null),
            new SubCommand("start",         "glide.start",         new Start(),         null),
            new SubCommand("createcourse",  "glide.createcourse",  new CreateCourse(),  null),
            new SubCommand("removecourse",  "glide.removecourse",  new RemoveCourse(),  null),
            new SubCommand("editcourse",    "glide.editcourse",    new EditCourse(),    null),

            new SubCommand("addboost",      "glide.addboost",      new AddBoost(),      checkEdit),
            new SubCommand("addcheckpoint", "glide.addcheckpoint", new AddCheckpoint(), checkEdit),
            new SubCommand("addthermal",    "glide.addthermal",    new AddThermal(),    checkEdit),
            new SubCommand("finishcourse",  "glide.finishcourse",  new FinishCourse(),  checkEdit),
            new SubCommand("setstart",      "glide.setstart",      new SetStart(),      checkEdit),
            new SubCommand("setfinish",     "glide.setfinish",     new SetFinish(),     checkEdit),
            new SubCommand("pos1",          "glide.pos1",          new Pos1(),          checkEdit),
            new SubCommand("pos2",          "glide.pos2",          new Pos2(),          checkEdit),
    };

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Missing argument");
            return false;
        }

        // get matching subcommand
        var cmd = Arrays.stream(subCommands)
                .filter(c -> c.command.equals(args[0]))
                .findAny().orElse(null);

        if (cmd == null) {
            sender.sendMessage("Invalid command");
            return false;
        }

        if (cmd.permission != null && !sender.hasPermission(cmd.permission)) {
            sender.sendMessage("Insufficient permission");
            return false;
        }

        // pass command handler onto subcommand
        return cmd.executor.onCommand(sender, command, args[0], Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length < 1) {
            return Collections.emptyList();
        }

        if (args.length == 1) {
            // build list of valid subcommands
            return Arrays.stream(subCommands)
                    .filter(c -> c.conditional.isVisible(sender)
                            && (c.permission == null || sender.hasPermission(c.permission))
                            && (c.command.startsWith(args[0].toLowerCase())))
                    .map(c -> c.command).toList();
        }

        // get matching subcommand
        var cmd = Arrays.stream(subCommands)
                .filter(c -> c.command.equals(args[0]))
                .findAny().orElse(null);

        if (cmd == null) {
            return Collections.emptyList();
        }

        // pass tab complete handler to subcommand
        return cmd.completer.onTabComplete(sender, command, args[0], Arrays.copyOfRange(args, 1, args.length));
    }

    static class SubCommand {
        interface Condition {
            boolean isVisible(CommandSender s);
        }

        static TabCompleter nullCompleter = (a,b,c,d) -> null;

        String command;
        String permission;
        CommandExecutor executor;
        TabCompleter completer;
        Condition conditional;

        public SubCommand(String command, String permission, CommandExecutor executor, @Nullable Condition conditional) {
            this.command = command;
            this.permission = permission;
            this.executor = executor;
            this.completer = executor instanceof TabCompleter completer2 ? completer2 : nullCompleter;
            this.conditional = conditional != null ? conditional : ignored -> true;
        }
    }
}
