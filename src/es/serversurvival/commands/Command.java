package es.serversurvival.commands;

import es.serversurvival.data.HomesCache;
import org.bukkit.entity.Player;

public abstract class Command {
    protected final HomesCache homesCache = HomesCache.INSTANCE;

    public abstract String getName();

    public abstract String getDesc();

    public abstract String getSyntax();

    public abstract void execute(Player player, String[] args);
}
