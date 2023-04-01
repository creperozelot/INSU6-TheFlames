package INSU.creperozelot.startsys;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.List;

public class insustart {
    private static int countdown_s = 30;
    public static void run() throws SQLException {


        for (Player AllOnlinePlayer : Bukkit.getOnlinePlayers()) {
            AllOnlinePlayer.removePotionEffect(PotionEffectType.INVISIBILITY);
            AllOnlinePlayer.removePotionEffect(PotionEffectType.SLOW);
        }

        StaticCache.freeze = true;
        try {
            List<String> teams = MYSQL.getTeams();

            for (String team : teams){

                List<String> players = MYSQL.getPlayerbyTeam(team);

                for (String playername : players){

                    if (Bukkit.getPlayer(playername) != null){

                        Player player = Bukkit.getPlayer(playername);

                        player.teleport(utils.generateStartupLocation());

                    }

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            MYSQL.setStarted(AllOnlinePlayers.getName(), "true");
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (countdown_s == 0) {
                    cancel();
                    StaticCache.storyrunning = false;
                    StaticCache.freeze = false;
                    utils.broadcastTitle(StaticCache.prefix, "ist §aGESTARTET!", 10, 80, 20);
                    utils.broadcastSound("story.countdown.final", SoundCategory.VOICE);
                }

                for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                    AllOnlinePlayers.sendMessage(StaticCache.prefix + "§a§lStart in §2§l " + countdown_s + " §a§lSekunden!");
                    utils.broadcastSound("story.countdown", SoundCategory.VOICE);
                }

                utils.broadcastActionbar("§a§lStart in §2§l " + countdown_s + " §a§lSekunden!");
                countdown_s--;
            }
        }.runTaskTimer(main.getInstance(), 0, 20);
    }
}
