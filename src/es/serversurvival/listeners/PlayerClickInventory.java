package es.serversurvival.listeners;

import es.serversurvival.commands.commands.HomeCommand;
import es.serversurvival.data.HomesCache;
import es.serversurvival.objects.Home;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerClickInventory implements Listener {
    private final HomesCache homesCache = HomesCache.INSTANCE;

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        try {
            String inventoryName = event.getView().getTitle();
            Player player = (Player) event.getWhoClicked();

            if (inventoryName.equalsIgnoreCase(HomeCommand.title)) {
                event.setCancelled(true);
                ItemStack itemStack = event.getCurrentItem();

                if (itemStack.getType().toString() == null || itemStack.getType().toString().endsWith("AIR")) {
                    return;
                }

                int idHomeClicked = Integer.parseInt(itemStack.getItemMeta().getLore().get(1).split(" ")[1]);

                Home home = homesCache.getById(idHomeClicked).get();
                home.teleport();

                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + "Has sido teletransportado a" + home.getName());
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
        } catch (Exception ignored) {
            //Ignored exception
        }
    }
}
