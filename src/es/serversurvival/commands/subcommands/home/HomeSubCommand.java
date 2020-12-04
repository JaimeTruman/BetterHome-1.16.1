package es.serversurvival.commands.subcommands.home;

import es.serversurvival.commands.SubCommand;

public abstract class HomeSubCommand extends SubCommand {
    private final String name = "home";

    public String getName() {
        return name;
    }
}
