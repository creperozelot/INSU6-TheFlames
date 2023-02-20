package INSU.creperozelot.tasks;

import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class WaitingForHost {
    public static int run() {
        int hostscheduler = 0;
        if  (!main.getInstance().getConfig().getBoolean("main.started")) {
            hostscheduler = new BukkitRunnable() {
                @Override
                public void run() {
                    if (Bukkit.getPlayer(main.getInstance().getConfig().getString("main.host").replace(" ", "_")) != null) {
                        utils.broadcastActionbar("§e§lWarte auf Start");
                    } else {
                        utils.broadcastActionbar("§6§lWarte auf Hoster");
                    }
                }
            }.runTaskTimer(main.getInstance(), 0, 20).getTaskId();
        }

        return hostscheduler;
    }


}
