package me.spixa.duels_plugin.Kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class ClassicKit extends Kit {
    @Override
    public void giveKit(Player p) {
        // initialize built-in items
        boots = createItem(Material.IRON_BOOTS,"§c§lClassic Boots",1,null);
        leggings = createItem(Material.IRON_LEGGINGS,"§c§lClassic Leggings",1,null);
        chestplate = createItem(Material.IRON_CHESTPLATE,"§c§lClassic Chestplate",1,null);
        helmet = createItem(Material.IRON_HELMET,"§c§lClassic Helmet",1,null);

        sword = createItem(Material.IRON_SWORD,"§eClassic Sword",1, Collections.singletonList("§7Protect yourself from the enemy with this"));
        food = createItem(Material.GOLDEN_CARROT,"§eGolden Carrot",64,null);

        // Classic's own items
        // None

        p.getInventory().setBoots(boots);
        p.getInventory().setLeggings(leggings);
        p.getInventory().setChestplate(chestplate);
        p.getInventory().setHelmet(helmet);

        p.getInventory().addItem(sword);

    }
}
