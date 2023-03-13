package INSU.creperozelot.tasks;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import org.bukkit.boss.BarColor;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarBlinking {
    public static void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.bossBar.setColor(BarColor.YELLOW);

                if (StaticCache.eventrunning) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            StaticCache.bossBar.setColor(BarColor.RED);
                        }
                    }.runTaskLater(main.getInstance(), 20);
                }
            }
        }.runTaskTimer(main.getInstance(), 0, 20 * 2);
    }
}
