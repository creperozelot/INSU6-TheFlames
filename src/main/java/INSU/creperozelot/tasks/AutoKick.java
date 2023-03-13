package INSU.creperozelot.tasks;

import INSU.creperozelot.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AutoKick {
    public static void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                String timestamp = new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());
                String time = main.getInstance().getConfig().getString("main.kicktime");
                if(timestamp.equals(time)) {
                    for (Player allonlineplayers : Bukkit.getOnlinePlayers()) {
                        allonlineplayers.kickPlayer(main.getInstance().getConfig().getString("messages.endtime.title") + "\n" + main.getInstance().getConfig().getString("messages.endtime.message"));
                    }
                    main.getInstance().getConfig().set("intime", false);
                    main.getInstance().saveConfig();

                    if (main.getInstance().getConfig().getBoolean("intime")) {
                        Bukkit.getServer().shutdown();
                    }
                }
            }
        }.runTaskTimer(main.getInstance(), 0, 20);
    }
}
