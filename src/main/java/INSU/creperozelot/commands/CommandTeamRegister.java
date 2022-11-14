package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CommandTeamRegister implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!(args.length == 3)) {
                player.sendMessage(StaticCache.prefix + "§cBenutzung: /registerteam <playername> <teamname> <teamid>");
            } else {

                try {
                    if (MYSQL.PlayerExist(args[0])) {

                        boolean isint = false;
                        int id = 0;
                        try {


                            id = Integer.parseInt(args[2]);

                            isint = true;
                        } catch (NumberFormatException e) {
                            //throw new RuntimeException(e);
                        }

                        if (isint) {
                            try {
                                MYSQL.setTeam(args[0], args[1], id);
                                player.sendMessage(StaticCache.prefix + "§aTeam §6" + args[1] + " §awurde mit dem Spieler §6" + args[0] + " §aund der ID §6" + args[2] + " §aerstellt.");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            player.sendMessage(StaticCache.prefix + "§cDie Team-ID ist keine Zahl.");
                        }


                    } else {
                        player.sendMessage(StaticCache.prefix + "§cDer Spieler wurde nicht in der Datenbank gefunden!");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        } else {
            sender.sendMessage(StaticCache.prefix + "§cDu kannst diesen Befehl nur als Spieler ausführen.");
        }
        return true;
    }
}
