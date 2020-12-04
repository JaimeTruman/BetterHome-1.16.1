package es.serversurvival.tasks;

import es.serversurvival.data.HomesCache;
import es.serversurvival.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public final class Backup extends BukkitRunnable {
    public final int delay = 20 * 60 * 30;

    @Override
    public void run() {
        try {
            Main.getDataHandler().saveHomes(HomesCache.INSTANCE.getAllHomes());
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Casas guardadas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
