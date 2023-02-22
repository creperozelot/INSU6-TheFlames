package INSU.creperozelot.utils;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
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

    public static boolean ifblockabove(Location loc){
        boolean bol = false;
        for (int i = loc.getBlockY(); i < 256; i++){
            Block block = loc.getWorld().getBlockAt(loc.getBlockX(), i, loc.getBlockZ());

            if(block != null && !block.isLiquid()
                    && block.getType() != Material.AIR) {
                bol = true;
            }
        }

        return bol;
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

    //TEAMCHEST INV

    public static void openTeamChest(int teamid, HumanEntity ent) throws IOException {
        File file = new File(main.getInstance().getDataFolder().getAbsolutePath() + "/data/teamchest", "teamchest_" + teamid + ".yml");
        Inventory inv = Bukkit.createInventory(null, 27, "Teamchest " + teamid);
        if (file.exists()) {
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            ItemStack[] content = ((List<ItemStack>) configuration.get("inventory.content")).toArray(new ItemStack[0]);
            inv.setContents(content);
            ent.openInventory(inv);
        } else {
            file.createNewFile();
            ItemStack inititem = new ItemStack(Material.DIRT);
            inititem.setAmount(1);
            inv.addItem(inititem);
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("inventory.content", inv.getContents());
            configuration.save(file);
            ent.sendMessage(StaticCache.prefix + "§aDeine Teamchest wurde erstellt, führe den befehlt erneut aus um sie zu öffnen!");
        }
    }

    public static void eventanimation(String eventname, String eventname1, String eventname2, String eventname3, String eventname4, String eventname5, String eventname6) {
        broadcastTitle("§6§le", eventname1, 10, 10, 0);
        broadcastSound(Sound.BLOCK_NOTE_BASS);
        StaticCache.bossBar.setTitle(StaticCache.prefix + "§cEvent §a" + eventname + " §cstatet...");


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
