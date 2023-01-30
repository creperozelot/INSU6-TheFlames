package INSU.creperozelot.utils;

import INSU.creperozelot.main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class utils {
    public static void broadcastTitle(String title, String subtitle, int fadein, int stay, int fadeout) {
        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            AllOnlinePlayers.sendTitle(title, subtitle, fadein, stay, fadeout);
        }
    }

    public static boolean dchasrole(Member member, String RoleID) {
        List<String> ids = new ArrayList<>();

        for (Role role : member.getRoles()){

            ids.add(role.getId());

        }

        return ids.contains(RoleID);
    }

    public static String permsmsgdc() {
        return "<:Madge:961579692341747783> **__Du hast auf diesen Befehl keine Berechtigung!__**";
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

    public static Player randomOnlinePlayer() {
        ArrayList<String> players = new ArrayList<>();
        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            players.add(AllOnlinePlayers.getName());
        }

        int randomaplyer = utils.random(0, players.size());

        return Bukkit.getPlayer(players.get(randomaplyer));
    }

    public static void eventanimation(String eventname, String eventname1, String eventname2, String eventname3, String eventname4, String eventname5, String eventname6) {
        broadcastTitle("§6§le", eventname1, 10, 10, 0);
        broadcastSound(Sound.BLOCK_NOTE_BASS);


        (new BukkitRunnable() {
            public void run() {
                broadcastTitle("§6§lven", eventname2, 0, 10, 0);
                broadcastSound(Sound.BLOCK_NOTE_BASS);

            }
        }).runTaskLater((Plugin) main.getInstance(), 20L);

        (new BukkitRunnable() {
            public void run() {
                broadcastTitle("§6§lEvent", eventname3, 0, 10, 0);
                broadcastSound(Sound.BLOCK_NOTE_BASS);

            }
        }).runTaskLater((Plugin) main.getInstance(), 30L);

        (new BukkitRunnable() {
            public void run() {
                broadcastTitle("§6§lEvent", eventname4, 0, 10, 0);
                broadcastSound(Sound.BLOCK_NOTE_BASS);

            }
        }).runTaskLater((Plugin) main.getInstance(), 40L);

        (new BukkitRunnable() {
            public void run() {
                broadcastTitle("§6§lEvent", eventname5, 0, 10, 0);
                broadcastSound(Sound.BLOCK_NOTE_BASS);

            }
        }).runTaskLater((Plugin) main.getInstance(), 50L);

        (new BukkitRunnable() {
            public void run() {
                broadcastTitle("§6§lEvent", eventname6, 0, 10, 0);
                broadcastSound(Sound.BLOCK_NOTE_BASS);
            }
        }).runTaskLater((Plugin) main.getInstance(), 60L);

        (new BukkitRunnable() {
            public void run() {
                broadcastTitle("§6§lEvent", eventname, 0, 100, 20);
                broadcastSound(Sound.BLOCK_NOTE_BASS);
            }
        }).runTaskLater((Plugin) main.getInstance(), 70L);

    }
}
