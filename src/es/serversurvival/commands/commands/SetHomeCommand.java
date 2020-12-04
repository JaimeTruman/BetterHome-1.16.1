package es.serversurvival.commands.commands;

import es.serversurvival.commands.Command;
import es.serversurvival.data.HomesCache;
import es.serversurvival.objects.Home;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SetHomeCommand extends Command {
    private final String name = "sethome";
    private final String desc = "Fijar una nueva casa";
    private final String syntax = "/sethome [nombre]";

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
        if (args.length != 1 && args.length != 0) {
            player.sendMessage(ChatColor.DARK_RED + "Uso incorrecto: " + syntax);
            return;
        }

        int numeroCasaTotalesJugador = homesCache.getNumberHomesPlayer(player.getName());

        String name = "Tu casa: " + (numeroCasaTotalesJugador + 1);

        if (args.length == 1) name = args[0];

        if ((numeroCasaTotalesJugador + 1) > HomesCache.maxHomesPerPlayer) {
            player.sendMessage(ChatColor.DARK_RED + "No puedes tener mas de " + HomesCache.maxHomesPerPlayer + " casas!");
            return;
        }

        Home home = new Home(player.getName(), name, player.getLocation());
        homesCache.add(home);

        player.sendMessage(ChatColor.GREEN + "Has puesto una casa mas: /home");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
    }
}
