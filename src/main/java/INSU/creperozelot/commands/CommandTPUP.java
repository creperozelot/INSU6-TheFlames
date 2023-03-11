package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.events.TPUP;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandTPUP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    TPUP.run();
                }
            }.runTaskLater(main.getInstance(), utils.eventanimation("§c§lTPUP", "§cU§l", "§c§lPU", "§c§lTPUP", "§c§lTPUP", "§c§lTPUP", "§c§lTPUP"));
        } else {
            sender.sendMessage(StaticCache.prefix + StaticCache.consoleerr);
        }
        return true;
    }
}
