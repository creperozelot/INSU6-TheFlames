package INSU.creperozelot.tasks;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckTime {

    public static void run() {


        new BukkitRunnable() {
            int countdown_s = 0;
            int countdown_m = 5;
            int task2 = 0;
            @Override
            public void run() {

                String timestamp = new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());
                String time = main.getInstance().getConfig().getString("main.playtime");
                String time2 = "20-25-01";
                if(timestamp.equals(time)) {
                    main.getInstance().getConfig().set("intime", true);
                    main.getInstance().saveConfig();
                }

                if (time2.equals(time)) {
                      task2 = new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (countdown_s == 0) {
                                if (countdown_m == 5 || countdown_m == 4 || countdown_m == 3 || countdown_m == 2 || countdown_m == 1) {
                                 Bukkit.getServer().broadcastMessage(StaticCache.prefix + "Projekt Endet in " + countdown_m + " Minuten...");
                                }
                            }

                            if (countdown_s == 1 && countdown_m == 0) {
                                Bukkit.getScheduler().cancelTask(task2);
                            }

                            if (countdown_m == 0) {
                                if (countdown_s == 30 || countdown_s == 20 || countdown_s <= 10) {
                                    Bukkit.getServer().broadcastMessage(StaticCache.prefix + "INSU ist in §e" + countdown_s + "§6 Sekunden vorbei!");
                                }
                            }

                            if (countdown_s == 0) {
                                countdown_s = 59;
                                countdown_m--;
                            }

                            countdown_s--;
                        }
                    }.runTaskTimer(main.getInstance(), 0, 20).getTaskId();
                }
            }
        }.runTaskTimer(main.getInstance(), 0, 20);
    }
}
