package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Kaktuse {

    public static void run() {

        StaticCache.eventrunning = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
                StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
            }
        }.runTaskLater(main.getInstance(), 20 * 5);

        utils.broadcastTitle("§cErr...", "$c->sendMessage('PHP Error CODE: Verarscht');", 0, 100, 20);
    }
}
