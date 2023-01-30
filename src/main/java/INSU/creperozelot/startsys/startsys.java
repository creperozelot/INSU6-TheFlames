package INSU.creperozelot.startsys;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class startsys {
    static int countdown_sec = 60 * main.getInstance().getConfig().getInt("main.countdown.minutes");

    static int countdown_min = main.getInstance().getConfig().getInt("main.countdown.minutes");
    public static void countdown(int CountdownMinutes) {



        new BukkitRunnable() {
            @Override
            public void run() {

                if  (countdown_min == 0) cancel();

                countdown_min--;
            }
        }.runTaskTimer(main.getInstance(), 0, 20 * 59);

         new BukkitRunnable() {
             @Override
             public void run() {
                 utils.broadcastActionbar("§e§lIN§6§lSU§r§a§l Startet in §2§l" + countdown_sec + "§a§l Sekunden...");

                 if (countdown_sec == 300 || countdown_sec == 240 || countdown_sec == 180 || countdown_sec == 120 || countdown_sec == 60) {
                     Bukkit.broadcastMessage(StaticCache.prefix + "Start in §f" + countdown_min + "§6 Minuten...");
                 }

                 if ( countdown_sec == 30 || countdown_sec == 20 || countdown_sec <= 10) {
                     Bukkit.broadcastMessage(StaticCache.prefix + "Start in §f" + countdown_sec + "§6 Sekunden...");
                 }

                 if (countdown_sec == 0) {
                     try {
                         startsys.initStory();
                     } catch (SQLException e) {
                         throw new RuntimeException(e);
                     }
                     cancel(); //Funfact bukkit hat eine cancel methode bereits integriert; Einfach cancel(); schreiben in der Bukkit Runnable
                 }

                 countdown_sec--;

             }
         }.runTaskTimer(main.getInstance(),0, 20);
    }

    public static void Countdown_insustart() throws SQLException {
        int countdown_s = 30;

        StaticCache.freeze = true;
        try {
            List<String> teams = MYSQL.getTeams();

            for (String team : teams){

                List<String> players = MYSQL.getPlayerbyTeam(team);

                int x = utils.random(-750, 750);

                int z = utils.random(-750, 750);

                Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).loadChunk(x,z);

                int y = Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).getHighestBlockYAt(x,z);

                Location loc = new Location(Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")) ,x,y,z);

                for (String playername : players){

                    if (Bukkit.getPlayer(playername) != null){

                        Player player = Bukkit.getPlayer(playername);

                        player.teleport(loc);

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
                    StaticCache.freeze = false;
                    utils.broadcastTitle("§e§lIN§6§lSU", "ist §aGESTARTET!", 10, 80, 20);

                }

                for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                    AllOnlinePlayers.sendMessage(StaticCache.prefix + "§a§lStart in §2§l " + countdown_s + "§a§lSekunden!");
                    AllOnlinePlayers.playSound(AllOnlinePlayers.getLocation(), Sound.BLOCK_NOTE_BELL, 100, 0);
                }

                utils.broadcastActionbar("§a§lStart in §2§l " + countdown_s + "§a§lSekunden!");
            }
        }.runTaskTimer(main.getInstance(), 0, 20);
    }

    public static void initStory() throws SQLException {
        int Airplane_start_x = main.getInstance().getConfig().getInt("story.airplane.start.x");
        int Airplane_start_y = main.getInstance().getConfig().getInt("story.airplane.start.y");
        int Airplane_start_z = main.getInstance().getConfig().getInt("story.airplane.start.z");

        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            Location airplane_start = new Location(AllOnlinePlayers.getWorld(), Airplane_start_x, Airplane_start_y, Airplane_start_z);
            //add effect
            AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 250, false, false));
            AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 999999, 250, false, false));

            //teleport players to airplane
            AllOnlinePlayers.teleport(airplane_start);

            //Start Story

            AllOnlinePlayers.playSound(AllOnlinePlayers.getLocation(), Sound.ENTITY_GHAST_DEATH, 100, 0);

        }


        new BukkitRunnable() {
            @Override
            public void run() {

                int Airplane_inair_x = main.getInstance().getConfig().getInt("story.airplane.inair.x");
                int Airplane_inair_y = main.getInstance().getConfig().getInt("story.airplane.inair.y");
                int Airplane_inair_z = main.getInstance().getConfig().getInt("story.airplane.inair.z");


                for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                    AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 250, false, false));
                    Bukkit.broadcastMessage(StaticCache.prefix + "§2Zwei Stunden später...");
                    AllOnlinePlayers.teleport(new Location (AllOnlinePlayers.getWorld(), Airplane_inair_x, Airplane_inair_y, Airplane_inair_z));
                }
            }
        }.runTaskLater(main.getInstance(), 20 * 30);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player OnlinePlayer : Bukkit.getOnlinePlayers()){
                    OnlinePlayer.playSound(OnlinePlayer.getLocation(), Sound.ENTITY_BLAZE_DEATH, 100, 0);
                }
            }
        }.runTaskLater(main.getInstance(), 20 * 10);

        new BukkitRunnable() {
            @Override
            public void run() {

                for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                    AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 250, false, false));
                }

            }
        }.runTaskLater(main.getInstance(), 20 * 110);

        startsys.Countdown_insustart();


    }

}
