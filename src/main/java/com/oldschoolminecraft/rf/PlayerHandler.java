package com.oldschoolminecraft.rf;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.Bukkit;

public class PlayerHandler extends PlayerListener
{
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        try
        {
            if (event.getPlayer().hasReceivedPacket0())
                kick(event.getPlayer());
        } catch (Exception ex) {}
    }

    @EventHandler
    public  void onPlayerJoin(PlayerJoinEvent event)
    {
        try
        {
            if (event.getPlayer().hasReceivedPacket0())
                kick(event.getPlayer());
        } catch (Exception ex) {}
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        try
        {
            if (event.getPlayer().hasReceivedPacket0())
                kick(event.getPlayer());
        } catch (Exception ex) {}
    }

    private void kick(Player player)
    {
        player.kickPlayer(ChatColor.RED + "Please connect using Beta 1.7.3.");
        System.out.println(ChatColor.RED + player.getName() + " was kicked for using DirtMultiversion.");
        for (Player ply : Bukkit.getServer().getOnlinePlayers())
            if (ply.hasPermission("rf.notify"))
                ply.sendMessage(ChatColor.RED + player.getName() + " was kicked for using DirtMultiversion.");
    }
}
