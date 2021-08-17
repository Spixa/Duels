package me.spixa.duels_plugin;

import me.spixa.duels_plugin.Arena.Arena;
import me.spixa.duels_plugin.Commands.DuelCommand;
import me.spixa.duels_plugin.Commands.MakeArenaCommand;
import me.spixa.duels_plugin.Events.InventoryClick;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Duels_plugin extends JavaPlugin {
    public static List<Arena> arenas = new ArrayList<>();
    public static HashMap<List<Player>, Arena> duelHashmap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("duel").setExecutor(new DuelCommand());
        getCommand("mkarena").setExecutor(new MakeArenaCommand());
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
