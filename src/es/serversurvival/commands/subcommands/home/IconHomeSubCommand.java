package es.serversurvival.commands.subcommands.home;

import es.serversurvival.objects.Home;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class IconHomeSubCommand extends HomeSubCommand {
    private String SCName = "icon";
    private String desc = "Poner un objeto a tu casa para verlo en el menu /home";
    private String syntax = "/home icon <id>";

    @Override
    public String getSCName() {
        return SCName;
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
        if (args.length != 2) {
            player.sendMessage(ChatColor.DARK_RED + "Incorrect usage: " + syntax);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (Exception e) {
            player.sendMessage(ChatColor.DARK_RED + "No puedes tener texto en la id, para ver la id /home");
            return;
        }
        List<Home> homes = homesCache.getPlayerHomes(player.getName());

        if (homes.size() == 0 || homes == null) {
            player.sendMessage(ChatColor.DARK_RED + "No tienes ninguna casa en el servidor, para nuevas casas /sethome [nombre]");
            return;
        }
        if (!homesCache.isOwnedByPlayer(player.getName(), id)) {
            player.sendMessage(ChatColor.DARK_RED + "Id de casa no encontrada, para ver las ids: /home");
            return;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getType().toString() == "AIR") {
            player.sendMessage(ChatColor.DARK_RED + "Necesitas tener un item en la mano para cambiar el item de la casa");
            return;
        }

        Home home = homesCache.getById(id).get();

        home.setIcon(itemStack.getType().toString());

        homesCache.replace(id, home);

        player.sendMessage(ChatColor.GREEN + "Has cambiado el item de tu casa: " + home.getName() + " (ver en /home)");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
    }
}
