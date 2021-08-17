package me.spixa.duels_plugin.Commands;

import me.spixa.duels_plugin.Inventories.SelectionInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4Error: §cOnly §dPlayers §ccan use this");
            return true;
        }
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("duel")) {
            if (args.length >= 1) {
                boolean status = false;
                for (Player x : Bukkit.getOnlinePlayers()) {
                    if (args[0].equalsIgnoreCase(x.getName())) {
                        SelectionInventory gui = new SelectionInventory(x, p);

                        // Open inventory for the person you are requesting to
                        x.openInventory(gui.getInventory());
                        status = true;
                    }
                }
                if (!status) {
                        p.sendMessage("§4Error: §cPlayer not found.");
                        return true;
                }
            } else {
                p.sendMessage("§4Error: §cToo few arguments.");
            }
        }

        return true;
    }
}
