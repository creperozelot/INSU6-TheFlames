package INSU.creperozelot.startsys;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class startsys {
    static int countdown_sec = 60 * main.getInstance().getConfig().getInt("main.countdown.minutes");
    public static void countdown(int CountdownMinutes) {

        int countdown_current_min = (int) TimeUnit.SECONDS.toMinutes(countdown_sec);
         Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                utils.broadcastActionbar("§e§lIN§6§lSU§r§a§l Startet in §2§l" + countdown_sec + "§a§l Sekunden...");

                if (countdown_sec == 300 || countdown_sec == 240 || countdown_sec == 180 || countdown_sec == 120 || countdown_sec == 60) {
                    Bukkit.broadcastMessage(StaticCache.prefix + "Start in §f" + countdown_current_min + "§6 Minuten...");
                }

                if ( countdown_sec == 30 || countdown_sec == 20 || countdown_sec <= 10) {
                    Bukkit.broadcastMessage(StaticCache.prefix + "Start in §f" + countdown_sec + "§6 Sekunden...");
                }

                if (countdown_sec == 0) {
                    startsys.initStory();
                    cancel(); //Funfact bukkit hat eine cancel methode bereits integriert; Einfach cancel(); schreiben in der Bukkit Runnable
                }

                countdown_sec--;

            }
        }, 0, 20);
    }

    public static void initStory() {
        int Airplane_start_x = main.getInstance().getConfig().getInt("story.airplane.start.x");
        int Airplane_start_y = main.getInstance().getConfig().getInt("story.airplane.start.y");
        int Airplane_start_z = main.getInstance().getConfig().getInt("story.airplane.start.z");

        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            Location airplane_start = new Location(AllOnlinePlayers.getWorld(), Airplane_start_x, Airplane_start_y, Airplane_start_z);
            //add effect
            AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 250, false, false));
            AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 999999, 250, false, false));
            AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 250, false, false));

            //teleport players to airplane
            AllOnlinePlayers.teleport(airplane_start);

            //Start Story

            AllOnlinePlayers.playSound(AllOnlinePlayers.getLocation(), Sound.ENTITY_GHAST_HURT, 100, 0);

        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {

            }
        });


    }

}
