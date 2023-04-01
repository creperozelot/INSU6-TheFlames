package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.Bukkit;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandsDevTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {

        } else {
            sender.sendMessage(StaticCache.prefix + StaticCache.consoleerr);
        }
        return true;
    }
}
