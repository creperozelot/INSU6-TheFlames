package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsDevSoundTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                AllOnlinePlayers.playSound(AllOnlinePlayers.getLocation(), "mob.ghast.death", SoundCategory.VOICE, 1.0f, 1.0f);
            }
        } else {
            sender.sendMessage(StaticCache.prefix + StaticCache.consoleerr);
        }
        return true;
    }
}
