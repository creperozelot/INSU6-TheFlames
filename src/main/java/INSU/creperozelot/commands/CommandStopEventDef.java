package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandStopEventDef implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(StaticCache.prefix + "§aStaticCache.evenmtrunning = false");
        StaticCache.eventrunning = false;
        return true;
    }
}
