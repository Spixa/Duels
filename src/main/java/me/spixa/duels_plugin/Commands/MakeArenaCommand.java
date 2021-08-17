package me.spixa.duels_plugin.Commands;

import me.spixa.duels_plugin.Arena.Arena;
import me.spixa.duels_plugin.Duels_plugin;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockVector;

public class MakeArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof  Player)) {
            sender.sendMessage("§4Error: §cCommand meant for players only. Not Type.CONSOLE");
            return false;
        }
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("mkarena")) {
            if (args.length >= 7) {
                Integer pos1x = 0,pos1y = 0,pos1z = 0;
                Integer pos2x = 0,pos2y = 0,pos2z = 0;
                try {
                    pos1x = Integer.parseInt(args[1]);
                    pos1y = Integer.parseInt(args[2]);
                    pos1z = Integer.parseInt(args[3]);

                    pos2x = Integer.parseInt(args[4]);
                    pos2y = Integer.parseInt(args[5]);
                    pos2z = Integer.parseInt(args[6]);
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("§4Error: §cAn unexpected error occurred, did you enter all arguments in integer?");
                    return true;
                }
                sender.sendMessage("§eSuccessfully made §f" + args[0] + " §earena!");
                Duels_plugin.arenas.add(new Arena(args[0],new BlockVector(pos1x,pos1y,pos1z), new BlockVector(pos2x,pos2y,pos2z),p.getWorld(),null ,null));
            } else {
                sender.sendMessage("§2Usage: §a/mkarena <name> [x1] [y1] [z1] [x2] [y2] [z2]");
                sender.sendMessage("§2Glossary: String= < >, Int= [ ], Boolean: { } ");
            }
        }
        return true;
    }
}
