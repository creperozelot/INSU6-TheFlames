package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.sql.SQLException;

public class CommandTeamchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            try {
                if (MYSQL.getTeammatebyPlayer(player.getPlayer()) != null) {
                    Player teammate = Bukkit.getPlayerExact(MYSQL.getTeammatebyPlayer(player));
                    System.out.println(MYSQL.getTeammatebyPlayer(player));
                    System.out.println(teammate.getOpenInventory().getTitle());
                    if (teammate.getOpenInventory().getTitle().equals("Teamchest " + MYSQL.getIDbyName(player.getName()))) {
                        player.sendMessage(StaticCache.prefix + "§cDie Teamchest wird gerade verwendet");
                    } else {
                        utils.openTeamChest(MYSQL.getTeamIDbyName(player.getName()), player);
                    }
                } else {
                    utils.openTeamChest(MYSQL.getTeamIDbyName(player.getName()), player);
                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }


        } else {
            sender.sendMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.errconsolerun"));
        }
        return true;
    }
}
