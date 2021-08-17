package me.spixa.duels_plugin.Kits;

import com.sun.tools.javac.jvm.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class Kit {
    public void giveKit(Player p)
    {}
    protected static ItemStack createItem(Material mat, String name, int count, List<String> lore) {
        ItemStack item = new ItemStack(mat,count);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (lore == null) {
            item.setItemMeta(meta);
            return item;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    // Predefined kit items:
    protected static ItemStack helmet = null;
    protected static ItemStack chestplate = null;
    protected static ItemStack leggings = null;
    protected static ItemStack boots = null;
    protected static ItemStack sword = null;
    protected static ItemStack food = null;
}
