package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class AcidRain {
    static int runned = 120;

    public static void run() {
        StaticCache.eventrunning = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
                StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
            }
        }.runTaskLater(main.getInstance(), 20L * 120L);


        Bukkit.getWorld((String) main.getInstance().getConfig().get("main.map")).setThundering(true);
        Bukkit.getWorld((String) main.getInstance().getConfig().get("main.map")).setThunderDuration(20 * 120);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (runned == 0) cancel();

                for (Player all : Bukkit.getOnlinePlayers()) {
                    Location playerpos = all.getLocation();
                    int heigehstblock = Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).getHighestBlockYAt(playerpos.getBlockX(), playerpos.getBlockZ());
                    Block heigehstblockblock = new Location(Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")), playerpos.getX(), playerpos.getY(), playerpos.getZ()).getBlock();
                    Block playerblockpos = new Location(Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")), playerpos.getX(), playerpos.getY() + 1, playerpos.getZ()).getBlock();

                    if (!utils.ifblockabove(playerpos)) {
                        all.damage(1.0);
                        all.sendTitle("§a§lToxischer Regenen", "§a§lStelle dich unter!", 0, 40, 20);
                    }

                }
                runned--;
            }


        }.runTaskTimer(main.getInstance(), 0,20L*1L);
    }
}





