package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CommandMaintenance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (main.getInstance().getConfig().getBoolean("main.maintenance")) {
            sender.sendMessage(StaticCache.prefix + "§cWartungsmodus ist nun Deaktiviert");
            main.getInstance().getConfig().set("main.maintenance", false);
            main.getInstance().saveConfig();
            main.getInstance().reloadConfig();
        } else {
            sender.sendMessage(StaticCache.prefix + "§aWartungsmodus ist nun Aktiviert");
            main.getInstance().getConfig().set("main.maintenance", true);
            main.getInstance().saveConfig();
            main.getInstance().reloadConfig();

            try {
                for (Player AllOnlinePlayers : MYSQL.getNormalOnlinePlayers()) {
                    AllOnlinePlayers.kickPlayer("§c§lFehler:\n §cDer Wartungsmodus wurde Aktiviert!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
