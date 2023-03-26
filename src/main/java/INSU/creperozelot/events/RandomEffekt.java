package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomEffekt {
    public static void run(){

        StaticCache.eventrunning = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
            }
        }.runTaskLater(main.getInstance(), 20 * 120);

        List<PotionEffectType> effects = new ArrayList<>(Arrays.asList(PotionEffectType.values()));
        List<PotionEffectType> removeeffects = new ArrayList<>();
        removeeffects.add(PotionEffectType.REGENERATION);
        removeeffects.add(PotionEffectType.FIRE_RESISTANCE);
        removeeffects.add(PotionEffectType.DAMAGE_RESISTANCE);
        removeeffects.add(PotionEffectType.INVISIBILITY);
        removeeffects.add(PotionEffectType.WITHER);
        removeeffects.add(PotionEffectType.INCREASE_DAMAGE);
        effects.removeAll(removeeffects);

        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            int randompotion = utils.random(0, effects.size());

            PotionEffectType potion = effects.get(randompotion);

            AllOnlinePlayers.addPotionEffect(new PotionEffect(potion, 20 * 60, 1));

        }
    }
}
