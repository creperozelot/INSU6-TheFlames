package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.events.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStartEvent implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!StaticCache.eventrunning) {
                switch(args[0]) {
                    case "acidrain":
                        AcidRain.run();
                        break;
                    case "findtheitem":
                        FindTheItem.run();
                        break;
                    case "findtheplayer":
                        FindThePlayer.run();
                        break;
                    case "glückstreffer":
                        Glueckstreffer.run();
                        break;
                    case "hungersnot":
                        HungersNot.run();
                        break;
                    case "insuquiz":
                        InsuQuiz.run();
                        break;
                    case "randomeffekt":
                        RandomEffekt.run();
                        break;
                    case "tpup":
                        TPUP.run();
                        break;
                    case "weitentfernt":
                        WeitEntfernt.run();
                        break;
                    default:
                        player.sendMessage("§cUsage: /eventstart <acidrain | findtheitem | findtheplayer | glückstreffer | hungersnot | insuquiz | randomeffekt | tpup | weitentfernt>");
                        break;
                }
            } else {
                player.sendMessage(StaticCache.prefix + "§cEs ist bereits ein event Gestartet!");
            }


        } else {
            sender.sendMessage("§cDu kannst diesen Befehl nur als Spieler ausführen!");
        }

        return true;
    }
}
