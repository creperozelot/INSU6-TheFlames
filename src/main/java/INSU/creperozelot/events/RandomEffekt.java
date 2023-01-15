package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class RandomEffekt {
    public static void run(){

        StaticCache.eventrunning = true;

        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
            }
        }, 20 * 120);

        List<PotionEffectType> effects = Arrays.asList(PotionEffectType.values());

        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            int randompotion = utils.random(0, effects.size());

            PotionEffectType potion = effects.get(randompotion);

            AllOnlinePlayers.addPotionEffect(new PotionEffect(potion, 60, 1));

        }
    }
}
