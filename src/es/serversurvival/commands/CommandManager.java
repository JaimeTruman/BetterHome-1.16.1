package es.serversurvival.commands;

import es.serversurvival.commands.commands.HomeCommand;
import es.serversurvival.commands.commands.SetHomeCommand;
import es.serversurvival.commands.subcommands.home.IconHomeSubCommand;
import es.serversurvival.commands.subcommands.home.RemoveHomeSubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public final class CommandManager implements CommandExecutor {
    private Set<es.serversurvival.commands.Command> commands = new HashSet<>();
    private Set<SubCommand> subCommands = new HashSet<>();

    public CommandManager() {
        commands.add(new SetHomeCommand());
        commands.add(new HomeCommand());
        
        subCommands.add(new IconHomeSubCommand());
        subCommands.add(new RemoveHomeSubCommand());

    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        es.serversurvival.commands.Command commandTyped = this.getCommand(command.getName());
        if (commandTyped == null) {
            player.sendMessage(ChatColor.DARK_RED + "Unknown command /help");
            return true;
        }
        if (args.length > 0) {
            SubCommand subCommand = this.getSubCommand(command.getName(), args[0]);
            if (subCommand != null) {
                subCommand.execute(player, args);
                return true;
            }
        }
        commandTyped.execute(player, args);
        return true;
    }

    private es.serversurvival.commands.Command getCommand(String name) {
        for (es.serversurvival.commands.Command command : commands) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return null;
    }

    private SubCommand getSubCommand(String CName, String SCName) {
        for (SubCommand subCommand : subCommands) {
            if (subCommand.getSCName().equalsIgnoreCase(SCName) && subCommand.getName().equalsIgnoreCase(CName)) {
                return subCommand;
            }
        }
        return null;
    }
}
