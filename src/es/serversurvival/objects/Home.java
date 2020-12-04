package es.serversurvival.objects;

import es.serversurvival.data.HomesCache;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;
import java.util.*;

public class Home implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final HomesCache homesCache = HomesCache.INSTANCE;

    private final String player;
    private final String name;
    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private int id;
    private String icon;

    public Home(String player, String name, Location location) {
        this.player = player;
        this.name = name;
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();

        if(homesCache.getNumberAllHomes() == 0){
            this.id = id;
        }else{
            this.id = homesCache.getLastHomeCreated().getId() + 1;
        }

        if (location.getWorld().getEnvironment() == World.Environment.NETHER) {
            icon = "BLACK_BED";
        } else if (location.getWorld().getEnvironment() == World.Environment.THE_END) {
            icon = "GRAY_BED";
        } else {
            icon = "RED_BED";
        }
    }
    
    public String getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void teleport() {
        Bukkit.getPlayer(player).teleport(getLocation());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Home home = (Home) o;
        return id == home.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
