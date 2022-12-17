package INSU.creperozelot.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

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

    public static void sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    public static void broadcastSound(Sound sound) {
        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            AllOnlinePlayers.playSound(AllOnlinePlayers.getLocation(), sound, 100, 0);
        }
    }
}
