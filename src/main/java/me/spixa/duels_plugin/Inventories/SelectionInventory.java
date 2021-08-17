package me.spixa.duels_plugin.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class SelectionInventory implements InventoryHolder {
    private Inventory inv;
    private Player requester;
    public SelectionInventory(Player ps, Player pr) {
        requester = pr;
        inv = Bukkit.createInventory(this, 9, "Duel request from " + pr.getDisplayName());
        ItemStack item;

        // Left
        for (int i = 0; i < 4;i++) {
            item = createItem(Material.LIME_STAINED_GLASS_PANE, "§a§lAccept duel", 1, Collections.singletonList("§7Accept " + pr.getDisplayName() + "'s duel"));
            inv.setItem(i, item);
        }

        // Center
        List<String> lore = new ArrayList<>();
        lore.add("§7To duel §f" + pr.getDisplayName() + "§7 please §cLeft-click");
        lore.add("§a§lAccept duel §r§7to accept, or");
        lore.add("§c§lDeny duel §r§7to deny request.");
        item = createItem(Material.ENCHANTED_BOOK, "§d§lDuel Request from " + pr.getDisplayName(), 1, lore);
        inv.setItem(inv.firstEmpty(), item);

        // Right
        for (int i = 5; i < 9;i++) {
            item = createItem(Material.RED_STAINED_GLASS_PANE, "§c§lDeny duel", 1, Collections.singletonList("§7Deny " + pr.getDisplayName() + "'s duel"));
            inv.setItem(i, item);
        }

    }

    public Player getRequester() {
        return requester;
    }

    private ItemStack createItem(Material mat, String name, int count, List<String> lore) {
        ItemStack item = new ItemStack(mat,count);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
