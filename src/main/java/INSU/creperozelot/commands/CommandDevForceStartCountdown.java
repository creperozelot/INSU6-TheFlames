package INSU.creperozelot.commands;

import INSU.creperozelot.startsys.startcountdown;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandDevForceStartCountdown implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        startcountdown.run();
        return true;
    }
}
