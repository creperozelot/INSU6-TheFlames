package INSU.creperozelot.commands.strike;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class StrikeMaincommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                if (!StaticCache.onlineplayerlist.contains(args[0]) || !MYSQL.PlayerExist(args[0])) {
                    sender.sendMessage(StaticCache.prefix + "§cDer Angegebene Spieler " + args[0] + "§c ist nicht Online oder wurde nicht in der Datenbank gefunden!");
                    return true;
                }

                Player strikedplayer = Bukkit.getPlayer(args[0]);

                try {
                    if (MYSQL.isGameMaster(strikedplayer.getName())) {
                        sender.sendMessage(StaticCache.prefix + "§cDer Spieler " + args[0] + " §cist ein Gamemaster und kann nicht Gestriked werden.");
                        return true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                switch (MYSQL.getStrikes(strikedplayer.getName())) {
                    case 0:
                        int x = (int) strikedplayer.getLocation().getX();
                        int y = (int) strikedplayer.getLocation().getY();
                        int z = (int) strikedplayer.getLocation().getZ();

                        try {
                            MYSQL.addStrike(strikedplayer.getName());
                        } catch (SQLException e) {
                            sender.sendMessage(StaticCache.prefix + "§cEs ist ein Fehler mit der Datenbank aufgetreten: " + e.getMessage());
                            throw new RuntimeException(e);
                        }
                        Bukkit.broadcastMessage(StaticCache.prefix + "§6§lDer Spieler §e§l" + strikedplayer.getName() + " §6§l hat einen Strike kassiert. §aDie Koordinaten des Spielers sind X: " + x + " §aY: " + y + " §aZ: " + z);
                        break;
                    case 1:
                        try {
                            MYSQL.addStrike(strikedplayer.getName());
                        } catch (SQLException e) {
                            sender.sendMessage(StaticCache.prefix + "§cEs ist ein Fehler mit der Datenbank aufgetreten: " + e.getMessage());
                            throw new RuntimeException(e);
                        }
                        sender.sendMessage(StaticCache.prefix + "§aDer Spieler hat nun seinen 2 Strike kassiert. Sein Inventar und seine Teamchest wurde geleert!");
                        strikedplayer.sendMessage(StaticCache.prefix + "§cDu hast deinen 2 Strike kassiert, dein Inventar und eure Teamchest wurde geleert!");
                        strikedplayer.getInventory().clear();
                        try {
                            utils.deleteTeamChest(MYSQL.getTeamIDbyName(strikedplayer.getName()));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:
                        sender.sendMessage(StaticCache.prefix + "§aDer Spieler hat seinen 3 Strike kassiert und ist nun Ausgeschieden.");
                        try {
                            MYSQL.delete(strikedplayer.getName());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        strikedplayer.kickPlayer("§c§lFehler:\n§cDu wurdest wegen zuvielen Regelverstößen vom Projekt ausgeschloßen!");
                        break;
                    default:
                        break;

                }
            } else {
                sender.sendMessage(StaticCache.prefix + "§cBenutzung von Strike System: /strike <player> \n §c1. Koordinaten des Spielers werden veröffentlicht \n §c2. Strike Stuffe Verlieren \n 3. Spieler wird vom Projekt ausgeschloßen!");
            }
        } else {
            sender.sendMessage(StaticCache.prefix + StaticCache.consoleerr);
        }
        return true;
    }
}
