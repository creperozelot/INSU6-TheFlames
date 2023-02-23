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
import java.util.ArrayList;
import java.util.List;

public class CommandTeamchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {

            Player player = (Player) sender;


            try {
                List<String> teammatenames = new ArrayList<>(MYSQL.getTeammateNamesbyPlayer(player));
                List<Player> teammates = new ArrayList<>();
                if (!teammatenames.isEmpty()) {

                    for (String teammatenames2 : teammatenames) {
                        teammates.add(Bukkit.getPlayerExact(teammatenames2));
                    }

                    System.out.println(teammatenames);

                    for (Player allteammates : teammates) {
                        if (allteammates != null) {
                            if (allteammates.getOpenInventory().getTitle().contains("Teamchest")) {
                                player.sendMessage(StaticCache.prefix + "§cDein Teammate " + allteammates.getName() + "§c hat gerade die Teamchest offen.");
                                return true;
                            }
                        }
                    }

                    utils.openTeamChest(MYSQL.getTeamIDbyName(player.getName()), player);


                } else {
                    sender.sendMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.errconsolerun"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
