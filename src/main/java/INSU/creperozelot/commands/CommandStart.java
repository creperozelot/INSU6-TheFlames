package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.startsys.startsys;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!StaticCache.startconfirmed) {
                StaticCache.startconfirmed = true;
                player.sendMessage(StaticCache.prefix + "§cBitte bestätige denn Start, bitte überorüfe ob alle Story punkte gesetzt sind. Um zu Starten füre den Befehl erneut aus.");
            } else {
                startsys.countdown(main.getInstance().getConfig().getInt("main.countdown.minutes"));
                player.sendMessage(StaticCache.prefix + "§aProjekt Gestartet!");
                Bukkit.getScheduler().cancelTask(StaticCache.Task_WaitingforHost_id);
            }
        } else {
            sender.sendMessage(StaticCache.prefix + StaticCache.consoleerr);
        }



        return true;
    }



}
