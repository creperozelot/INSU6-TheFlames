package INSU.creperozelot.tasks;

import INSU.creperozelot.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;

public class AutoKick {
    public static void run() {
        int hours = new Date().getHours();
        int minutes = new Date().getMinutes();
        int conf_hour = main.getInstance().getConfig().getInt("main.kicktime.hours");
        int conf_min = main.getInstance().getConfig().getInt("main.kicktime.minutes");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hours >= conf_hour && minutes >= conf_min) {
                    for (Player allonlineplayers : Bukkit.getOnlinePlayers()) {
                        allonlineplayers.kickPlayer(main.getInstance().getConfig().getString("messages.endtime"));
                    }
                }
            }
        }.runTaskTimer(main.getInstance(), 0, 20);
    }
}
