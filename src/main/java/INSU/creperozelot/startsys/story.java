package INSU.creperozelot.startsys;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class story {

    public static void run() {
        int Airplane_start_x = main.getInstance().getConfig().getInt("story.airplane.start.x");
        int Airplane_start_y = main.getInstance().getConfig().getInt("story.airplane.start.y");
        int Airplane_start_z = main.getInstance().getConfig().getInt("story.airplane.start.z");
        int Airplane_inair_x = main.getInstance().getConfig().getInt("story.airplane.inair.x");
        int Airplane_inair_y = main.getInstance().getConfig().getInt("story.airplane.inair.y");
        int Airplane_inair_z = main.getInstance().getConfig().getInt("story.airplane.inair.z");
        String sound_pilotspeak = "story.announcment.pilot";
        String sound_main_story = "story.announcment.main";
        Location airplane_start = new Location(Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")), Airplane_start_x, Airplane_start_y, Airplane_start_z);

        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 250, false, false));
        }

        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            AllOnlinePlayers.teleport(airplane_start);
        }

        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()){
            AllOnlinePlayers.playSound(AllOnlinePlayers.getLocation(), sound_pilotspeak, 1.0f, 1.0f);
        }

        //Add Blindniss Potion Effect
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                    AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 5, true, false));
                }
            }
        }.runTaskLater(main.getInstance(), 20 * 21);

        new BukkitRunnable() {
            @Override
            public void run() {


                for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                    AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 250, false, false));
                    AllOnlinePlayers.sendMessage(StaticCache.prefix + "§2Zwei Stunden später...");
                    AllOnlinePlayers.teleport(new Location (AllOnlinePlayers.getWorld(), Airplane_inair_x, Airplane_inair_y, Airplane_inair_z));
                }
            }
        }.runTaskLater(main.getInstance(), 20 * 25
        );

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()){
                    AllOnlinePlayers.playSound(AllOnlinePlayers.getLocation(), sound_main_story, 1.0f, 1.0f);
                }
            }
        }.runTaskLater(main.getInstance(), 20 * 10 + 20 * 25);


        new BukkitRunnable() {
            @Override
            public void run() {

                for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                    AllOnlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 50, 250, false, false));
                }

            }
        }.runTaskLater(main.getInstance(), 20 * 145);

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    insustart.run();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskLater(main.getInstance(), 20 * 155);
    }
}
