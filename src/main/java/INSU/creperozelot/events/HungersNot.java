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

            new BukkitRunnable() {
                @Override
                public void run() {
                    StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
                    StaticCache.eventrunning = false;
                }
            }.runTaskLater(main.getInstance(),20 * 120);

            AllOnlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 180, 2));
        }

    }
}
