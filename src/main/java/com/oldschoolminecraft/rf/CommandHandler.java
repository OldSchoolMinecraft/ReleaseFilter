package com.oldschoolminecraft.rf;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import ru.tehkode.permissions.commands.Command;
import ru.tehkode.permissions.commands.CommandListener;

import java.util.Map;

public class CommandHandler implements CommandListener
{
    @Command(name = "rf", syntax = "allow <username>", description = "Allow a user to use release", permission = "rf.admin")
    public void onAllowCommand(final Plugin plugin, final CommandSender sender, final Map<String, String> args)
    {
        String username = args.get("username");

        if (username != null || !username.isEmpty())
        {
            ReleaseFilter.instance.addAllowedUser(username);
            sender.sendMessage("Allowed user");
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid parameters");
            return;
        }
    }

    @Command(name = "rf", syntax = "deny <username>", description = "Block a user from using release", permission = "rf.admin")
    public void onDenyCommand(final Plugin plugin, final CommandSender sender, final Map<String, String> args)
    {
        String username = args.get("username");

        if (username != null || !username.isEmpty())
        {
            ReleaseFilter.instance.removeAllowedUser(username);
            sender.sendMessage("Denied user");
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid parameters");
            return;
        }
    }

    @Command(name = "rf", syntax = "help", description = "ReleaseFilter help info", permission = "rf.admin")
    public void onHelpCommand(final Plugin plugin, final CommandSender sender, final Map<String, String> args)
    {
        sender.sendMessage("ReleaseFilter commands:");
        sender.sendMessage("/allow - Allow a user to use release");
        sender.sendMessage("/deny - Block a user  from using release");
        sender.sendMessage("/help - Display this message");
    }
}
