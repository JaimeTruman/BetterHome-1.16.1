package es.serversurvival.commands.subcommands.home;

import es.serversurvival.objects.Home;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class RemoveHomeSubCommand extends HomeSubCommand {
    private final String SCName = "remove";
    private final String desc = "Borrar una casa dada una id que se ve en /home";
    private final String syntax = "/home remove <id>";

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
            player.sendMessage(ChatColor.DARK_RED + "Uso incorrecto: " + syntax);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (Exception e) {
            player.sendMessage(ChatColor.DARK_RED + "No puedes tener texto como id, las ids se ven en /home");
            return;
        }

        List<Home> homes = homesCache.getPlayerHomes(player.getName());

        if (homes.size() == 0 || homes == null) {
            player.sendMessage(ChatColor.DARK_RED + "No tienes ninguna casa en el server. Para nuevas casas /sethome [nombre]");
            return;
        }
        if(!homesCache.getById(id).isPresent()){
            player.sendMessage(ChatColor.DARK_RED + "Esa casa no esta en el servidor");
        }

        if (!homesCache.isOwnedByPlayer(player.getName(), id)) {
            player.sendMessage(ChatColor.DARK_RED + "Esa casa no es tuya");
            return;
        }

        Home home = homesCache.getById(id).get();
        homesCache.remove(home);

        player.sendMessage(ChatColor.GREEN + "Has borrado: " + home.getName() + "!");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
    }
}
