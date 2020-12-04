package es.serversurvival.data;

import es.serversurvival.objects.Home;

import java.util.*;
import java.util.stream.Collectors;

public class HomesCache {
    public static final int maxHomesPerPlayer = 18;

    private List<Home> homes = new ArrayList<>();
    public static final HomesCache INSTANCE = new HomesCache();

    private HomesCache () {}

    public boolean add (Home home) {
        return this.homes.add(home);
    }

    public void replace (int id, Home home) {
        this.homes.set(homes.indexOf(home), home);
    }

    public void remove (Home home) {
        this.homes.remove(homes.indexOf(home));
    }

    public List<Home> getAllHomes () {
        return this.homes;
    }

    public Optional<Home> getById(int id) {
        return this.homes.stream()
                .filter(home -> home.getId() == id)
                .findFirst();
    }

    public List<Home> getPlayerHomes(String playerName) {
        return this.homes.stream()
                .filter(home -> home.getPlayer().equalsIgnoreCase(playerName))
                .collect(Collectors.toList());
    }

    public int getNumberHomesPlayer(String playerName) {
        return (int) this.homes.stream()
                .filter(home -> home.getPlayer().equalsIgnoreCase(playerName))
                .count();
    }

    public int getNumberAllHomes () {
        return this.homes.size();
    }

    public boolean isOwnedByPlayer(String name, int id) {
        Optional<Home> home = this.getById(id);

        return home.isPresent() && home.get().getPlayer().equalsIgnoreCase(name);
    }

    public void setHomes (List<Home> homes) {
        this.homes = homes;
    }

    public Home getLastHomeCreated () {
        return this.homes.get(getNumberAllHomes() - 1);
    }
}
