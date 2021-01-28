package com.oldschoolminecraft.rf;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerHandler extends PlayerListener
{
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        if (event.getPlayer().hasReceivedPacket0())
            kick(event.getPlayer());
    }

    @EventHandler
    public  void onPlayerJoin(PlayerJoinEvent event)
    {
        if (event.getPlayer().hasReceivedPacket0())
            kick(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if (event.getPlayer().hasReceivedPacket0())
            kick(event.getPlayer());
    }

    private void kick(Player player)
    {
        player.kickPlayer(ChatColor.RED + "Please use Beta 1.7.3");
    }
}
