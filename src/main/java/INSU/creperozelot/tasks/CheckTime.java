package INSU.creperozelot.tasks;

import INSU.creperozelot.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckTime {
    public static void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                String timestamp = new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());
                String time = main.getInstance().getConfig().getString("main.playtime");
                if(timestamp.equals(time)) {
                    main.getInstance().getConfig().set("intime", true);
                    main.getInstance().saveConfig();
                }
            }
        }.runTaskTimer(main.getInstance(), 0, 20);
    }
}
