package es.serversurvival.commands.commands;

import es.serversurvival.commands.Command;
import es.serversurvival.objects.Home;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HomeCommand extends Command {
    public static final String title = ChatColor.DARK_RED + "" + ChatColor.BOLD + "         /home help";

    private final String name = "home";
    private final String desc = "Ver todas tus casas";
    private final String syntax = "/home";


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    @Override
    public void execute(Player player, String[] args) {
        List<Home> homes = homesCache.getPlayerHomes(player.getName());

        if (homes == null || homes.size() == 0) {
            player.sendMessage(ChatColor.DARK_RED + "No tienes ninguna casa en el servidor");
            return;
        }

        Inventory inventory = buildInv(homes);
        player.openInventory(inventory);
    }

    private Inventory buildInv(List<Home> homes) {
        Inventory inventory = Bukkit.createInventory(null, 27, title);

        int pos = 0;

        for (Home home : homes) {
            Location location = home.getLocation();
            String material = home.getIcon();

            ItemStack item = new ItemStack(Material.getMaterial(material));
            ItemMeta itemMeta = item.getItemMeta();

            itemMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + home.getName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "" + Math.floor(location.getX()) + ", " + Math.floor(location.getY()) + ", " + Math.floor(location.getZ()));
            lore.add("ID: " + home.getId());
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);

            inventory.setItem(pos, item);
            pos++;
        }

        inventory.setItem(26, buildItemInfo());

        return inventory;
    }

    private ItemStack buildItemInfo() {
        ItemStack info = new ItemStack(Material.PAPER);
        ItemMeta infoMeta = info.getItemMeta();

        infoMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "INFO");
        List<String> lore = new ArrayList<>();
        lore.add("Para poner una casa: /sethome <nombre>");
        lore.add("Para ver tus casas: /home");
        lore.add("Para borrar una casa: /home remove <id>");
        lore.add("   la id se ve en /home (este menu)");
        lore.add("Para cambiar el item de la casa: /home icon <id>");
        lore.add(" donde la id se ve en este menu, el item sera el que");
        lore.add(" tengas en la mano");

        infoMeta.setLore(lore);
        info.setItemMeta(infoMeta);

        return info;
    }
}
