package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FindThePlayer {
    static int runned = 12;
    public static void run() {

        StaticCache.eventrunning = true;

        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
            }
        }, 20 * 120);

        Player randomplayer = utils.randomOnlinePlayer();

        int istrue = utils.random(0, 1);

        if (istrue == 1) {

            Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new BukkitRunnable() {
                @Override
                public void run() {

                    if (runned == 0) cancel();

                    int x = (int) randomplayer.getLocation().getX();
                    int y = (int) randomplayer.getLocation().getY();
                    int z = (int) randomplayer.getLocation().getZ();

                    Bukkit.broadcastMessage(StaticCache.prefix + "§aDer Spieler befindet sich Hier! \n §lX: " + x + " §a§lY :" + y + " §a§lZ: " + z);

                    runned--;

                }
            }, 20 * 10, 0);
        }

        if (istrue == 0) {

            Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new BukkitRunnable() {

                int x = (int) randomplayer.getLocation().getX() + utils.random(0, 500);
                int y = (int) randomplayer.getLocation().getX() + utils.random(0, 500);
                int z = (int) randomplayer.getLocation().getX() + utils.random(0, 500);

                @Override
                public void run() {

                    if (runned == 0) cancel();

                    int xnow = x + utils.random(0, 60);
                    int ynow = y + utils.random(0, 60);
                    int znow = z + utils.random(0, 60);

                    int xnow1 = xnow - utils.random(0, 60);
                    int ynow1 = ynow - utils.random(0, 60);
                    int znow1 = znow - utils.random(0, 60);


                    Bukkit.broadcastMessage(StaticCache.prefix + "§aDer Spieler befindet sich Hier! \n §lX: " + xnow1 + " §a§lY :" + ynow1 + " §a§lZ: " + znow1);


                }
            }, 20 * 10, 0);




        }
    }
}
