package es.serversurvival.main;

import es.serversurvival.commands.CommandManager;
import es.serversurvival.data.HomesCache;
import es.serversurvival.data.HomesFileManager;
import es.serversurvival.listeners.PlayerClickInventory;
import es.serversurvival.objects.Home;
import es.serversurvival.tasks.Backup;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static HomesFileManager dataHandler;

    @Override
    public void onEnable() {
        dataHandler = new HomesFileManager();
        instance = this;

        setUpCommands();
        setUpEvents();
        setUpDataHandler();
        setUpTasks();
    }

    private void setUpCommands () {
        CommandManager commandManager = new CommandManager();
        getCommand("sethome").setExecutor(commandManager);
        getCommand("home").setExecutor(commandManager);
        getCommand("viewhomes").setExecutor(commandManager);
    }

    private void setUpTasks() {
        Backup backup = new Backup();
        backup.runTaskTimer(this, backup.delay, backup.delay);
    }

    private void setUpEvents () {
        getServer().getPluginManager().registerEvents(new PlayerClickInventory(), this);
    }

    private void setUpDataHandler() {
        try {
            dataHandler.setUpFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        try {
            dataHandler.saveHomes(HomesCache.INSTANCE.getAllHomes());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public static HomesFileManager getDataHandler() {
        return dataHandler;
    }
}
