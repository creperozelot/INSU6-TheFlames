package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class AcidRain {
    static int runned = 120;
    public static void run() {
        StaticCache.eventrunning = true;

        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
            }
        }, 20 * 120);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {

                if (runned == 0) {
                    cancel();
                };


                for (Player AllOnlinePlayer : Bukkit.getOnlinePlayers()) {
                    Location loc = AllOnlinePlayer.getEyeLocation().add(0,1,0);

                    while(loc.getY() < 256)
                    {

                        if (runned == 0) break;

                        if(loc.getBlock().getType() == Material.AIR)
                        {


                            AllOnlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 2, 1), true);
                            AllOnlinePlayer.sendTitle("§a§lToxischer Regen!", "§aStelle dich UNTER!", 0, 40, 20);

                        }
                        loc.add(0,1,0);

                    }
                }

                runned--;
            }
        }, 20, 0);

    }


}
