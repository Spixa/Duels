package me.spixa.duels_plugin.Arena;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockVector;

public class Arena {
    public Arena(String arenaName,BlockVector pos1, BlockVector pos2, World world, Player p1, Player p2) {
        m_pos1 = pos1;
        m_pos2 = pos2;
        w = world;
        name = arenaName;
        this.p1 = p1;
        this.p2 = p2;

    }
    public BlockVector getPos1() {
        return m_pos1;
    }
    public BlockVector getPos2() {
        return m_pos2;
    }
    public World getWorld() {
        return w;
    }
    public String getName() {
        return name;
    }
    public Player getRequester() {
        return p1;
    }
    public Player getAccepter() {
        return p2;
    }

    public void setRequester(Player p) {
        p1 = p;
    }
    public void setAccepter(Player p) {
        p2 = p;
    }

    private BlockVector m_pos1;
    private BlockVector m_pos2;
    private World w;
    private String name;
    private Player p1;
    private Player p2;

}
