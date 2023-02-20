package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandDBReconnect implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MYSQL.keepalive();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (MYSQL.isConnected()) {
                    sender.sendMessage(StaticCache.prefix + "§aMYSQL Verbindung erfolgreich neugestartet.");
                } else {
                    sender.sendMessage(StaticCache.prefix + "§cMYSQL Verbindung wurde nicht erfolgreich neugestartet.");
                }
            }
        }.runTaskLater(main.getInstance(), 20 * 5);
        return true;
    }
}
