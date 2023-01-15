package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class HungersNot {
    public static void run() {

        for (Player AllOnlinePlayer : Bukkit.getOnlinePlayers()) {

            StaticCache.eventrunning = true;

            Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
                @Override
                public void run() {
                    StaticCache.eventrunning = false;
                }
            }, 20 * 120);

            AllOnlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 180, 2));
        }

    }
}
