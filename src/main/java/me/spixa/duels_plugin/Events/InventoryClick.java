package me.spixa.duels_plugin.Events;

import me.spixa.duels_plugin.Arena.Arena;
import me.spixa.duels_plugin.Duels_plugin;
import me.spixa.duels_plugin.Inventories.SelectionInventory;
import me.spixa.duels_plugin.Kits.ClassicKit;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryClick implements Listener {
    @EventHandler
    public void onDie(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() == null) return;
        if (e.getEntity().getKiller() instanceof Player) {
            Player p1 = e.getEntity();
            Player p2 = e.getEntity().getKiller();
            List<Player> duelers = new ArrayList<>();
            duelers.add(p1);
            duelers.add(p2);
            if (Duels_plugin.duelHashmap.get(duelers) != null) {
                Arena a = Duels_plugin.duelHashmap.get(duelers);
                e.getDrops().clear();
                p2.getInventory().clear();
                p2.sendTitle("§e§lGG","§aYou won the duel",0,3,0);


                if (a.getRequester() == e.getEntity().getKiller()) {
                    Bukkit.getServer().broadcastMessage("§eHost §f" + a.getRequester().getDisplayName() + "§e killed §f" + e.getEntity().getDisplayName() + "§e in the duel §f" + a.getName());
                }
                else if (a.getAccepter() == e.getEntity().getKiller()) {
                    Bukkit.getServer().broadcastMessage("§f" + a.getAccepter().getDisplayName() + "§e killed the host §f" + a.getRequester().getDisplayName() + "§e in the duel §f" + a.getName());
                }
                else {
                    Bukkit.getServer().broadcastMessage("§4Server error: §cMatch §f" + a.getName() + "§c illegally ended.");
                }
                return;
            }

            List<Player> duelers2 = new ArrayList<>();
            duelers2.add(p2);
            duelers2.add(p1);

            if (Duels_plugin.duelHashmap.get(duelers2) != null) {
                Arena a = Duels_plugin.duelHashmap.get(duelers2);
                e.getDrops().clear();
                p2.getInventory().clear();
                p2.sendTitle("§e§lGG","§aYou won the duel",1,3,1);


                if (a.getRequester() == e.getEntity().getKiller()) {
                    Bukkit.getServer().broadcastMessage("§eHost §f" + a.getRequester().getDisplayName() + "§e killed §f" + e.getEntity().getDisplayName() + "§e in the duel §f" + a.getName());
                }
                else if (a.getAccepter() == e.getEntity().getKiller()) {
                    Bukkit.getServer().broadcastMessage("§f" + a.getAccepter().getDisplayName() + "§e killed the host §f" + a.getRequester().getDisplayName() + "§e in the duel §f" + a.getName());
                }
                else {
                    Bukkit.getServer().broadcastMessage("§4Server error: §cMatch §f" + a.getName() + "§c illegally ended.");
                }
                return;
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null ) { return; }
        if (e.getClickedInventory().getHolder() instanceof SelectionInventory) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem() == null) { return; }
            if (e.isLeftClick()) {
                if(e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
                    p.sendMessage("§bYou accepted the request");
                    p.closeInventory();
                    SelectionInventory inv = (SelectionInventory) e.getClickedInventory().getHolder();
                    List<Player> duelers = new ArrayList<>();
                    duelers.add(inv.getRequester());
                    duelers.add(p);

                    if (Duels_plugin.arenas.isEmpty()) {
                        p.sendMessage("§4Error: §cDid not find any valid arenas.");
                        inv.getRequester().sendMessage("§4Error: §cReceiver did accept but there were no valid arenas to join.");
                    }

                    Random randomizer = new Random();
                    int random_arena = randomizer.nextInt() % Duels_plugin.arenas.size();

                    Duels_plugin.duelHashmap.put(duelers, Duels_plugin.arenas.get(random_arena));

                    Player p1 = p, p2 = inv.getRequester();

                    Arena arena = Duels_plugin.arenas.get(random_arena);

                    arena.setRequester(p2);
                    arena.setAccepter(p1);

                    Location p1_tp = new Location(arena.getWorld(),arena.getPos1().getX(),arena.getPos1().getY(),arena.getPos1().getZ());
                    Location p2_tp = new Location(arena.getWorld(),arena.getPos2().getX(),arena.getPos2().getY(),arena.getPos2().getZ());

                    p1.getInventory().clear();
                    p2.getInventory().clear();

                    p1.teleport(p1_tp);
                    p2.teleport(p2_tp);

                    p1.sendMessage("§ePlaying on arena §f" + arena.getName() + "§e against §f " + p2.getDisplayName());
                    p2.sendMessage("§ePlaying on arena §f" + arena.getName() + "§e against §f " + p1.getDisplayName());

                    p1.setGameMode(GameMode.SURVIVAL);
                    p2.setGameMode(GameMode.SURVIVAL);
                    ClassicKit kit = new ClassicKit();
                    kit.giveKit(p1);
                    kit.giveKit(p2);

                }
                else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    p.sendMessage("§bYou denied the request");
                    p.closeInventory();
                }
                else if (e.getCurrentItem().getType().equals(Material.ENCHANTED_BOOK)) {
                    p.sendMessage("§bPlease make a selection.");
                }
             }
        }
    }
}
