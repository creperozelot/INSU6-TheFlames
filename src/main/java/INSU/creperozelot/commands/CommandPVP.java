package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPVP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {

            if (Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).getPVP()) {
                Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).setPVP(false);
                Bukkit.getServer().broadcastMessage(StaticCache.prefix + "§cPVP ist nun DEAKTIVIERT!");
                utils.broadcastTitle("§c§lPVP", "§a§lIst nun §c§lDeaktiviert", 0, 60, 20);
                utils.broadcastSound(Sound.ITEM_TOTEM_USE);
            } else {
                Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).setPVP(true);
                Bukkit.getServer().broadcastMessage(StaticCache.prefix + "§cPVP ist nun §aAKTIVIERT!");
                utils.broadcastTitle("§c§lPVP", "§a§lIst nun §a§lAktiviert", 0, 60, 20);
                utils.broadcastSound(Sound.ITEM_TOTEM_USE);
            }

        }
        return true;
    }
}
