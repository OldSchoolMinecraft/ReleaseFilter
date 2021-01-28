package com.oldschoolminecraft.rf;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minecraft.server.Packet0KeepAlive;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spout.packet.standard.MCCraftPacket;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.packet.listener.PacketListener;
import org.getspout.spoutapi.packet.standard.MCPacket;
import ru.tehkode.permissions.commands.CommandsManager;

import java.io.File;
import java.util.ArrayList;

public class ReleaseFilter extends JavaPlugin implements PacketListener
{
    public static ReleaseFilter instance;

    private CommandsManager commandManager = new CommandsManager(this);
    private ArrayList<String> allowedUsers = new ArrayList<String>();

    private File dir = new File("plugins/ReleaseFilter");
    private File allowedUsersFile = new File(dir, "allowedUsers.json");

    public void onEnable()
    {
        instance = this;

        if (!dir.exists())
            dir.mkdirs();

        try
        {
            if (allowedUsersFile.exists())
            {
                ObjectMapper mapper = new ObjectMapper();
                allowedUsers = mapper.readValue(allowedUsersFile, ArrayList.class);
            } else {
                saveList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SpoutManager.getPacketManager().addListener(0, this);

        System.out.println("ReleaseFilter enabled.");
    }

    public boolean checkPacket(final Player player, final MCPacket packet)
    {
        Packet0KeepAlive packet0 = (Packet0KeepAlive) ((MCCraftPacket) packet).getPacket();
        if (!player.hasPermission("rf.bypass"))
            player.kickPlayer(ChatColor.RED + "Please connect on Beta 1.7.3");

        return true;
    }

    public void onDisable()
    {
        System.out.println("ReleaseFilter disabled.");
    }

    public void addAllowedUser(String username)
    {
        try
        {
            allowedUsers.add(username.toLowerCase());
            saveList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeAllowedUser(String username)
    {
        try
        {
            allowedUsers.remove(username.toLowerCase());
            saveList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveList()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(allowedUsersFile, allowedUsers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
