package com.oldschoolminecraft.rf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.commands.CommandsManager;

import java.io.File;
import java.util.ArrayList;

public class ReleaseFilter extends JavaPlugin
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

        getServer().getPluginManager().registerEvents(new PlayerHandler(), this);

        System.out.println("ReleaseFilter enabled.");
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
