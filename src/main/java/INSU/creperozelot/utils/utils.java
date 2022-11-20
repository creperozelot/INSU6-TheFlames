package INSU.creperozelot.utils;

import INSU.creperozelot.main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class utils {
    public static void broadcastTitle(String title, String subtitle, int fadein, int stay, int fadeout) {
        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            AllOnlinePlayers.sendTitle(title, subtitle, fadein, stay, fadeout);
        }
    }

    public static int random(int min, int max) {

        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static void broadcastActionbar(String message) {
        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            AllOnlinePlayers.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }
    }


}
