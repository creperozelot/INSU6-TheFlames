package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetStoryAirplaneStart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int x = (int) player.getLocation().getX();
            int y = (int) player.getLocation().getY();
            int z = (int) player.getLocation().getZ();


            player.sendMessage(StaticCache.prefix + "§aErfolgreich! Neuer Ort für Airplane_STORY_start gesetzt auf §r§a§lX: " + x +  "§r§a§l Y: " + y + "§r§a§l Z:" + z);
            main.getInstance().getConfig().set("story.airplane.start.x", x);
            main.getInstance().getConfig().set("story.airplane.start.y", y);
            main.getInstance().getConfig().set("story.airplane.start.z", z);
            main.getInstance().saveConfig();

        } else {
            sender.sendMessage("§cDu musst diesen Befehl als Spieler ausführen!");
        }
        return true;
    }
}
