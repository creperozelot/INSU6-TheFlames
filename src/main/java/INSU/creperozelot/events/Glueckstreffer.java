package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Glueckstreffer {
    public static void run() {

        StaticCache.eventrunning = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
                StaticCache.eventrunning = false;
            }
        }.runTaskLater(main.getInstance(),20 * 10);

        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
            int randomint = utils.random(1, 10);

            if (randomint == 8) {
                AllOnlinePlayers.sendMessage(StaticCache.prefix + "§aDu hast einen Gapp bekommen :)");
                ItemStack gapp = new ItemStack(Material.GOLDEN_APPLE);
                gapp.setAmount(1);
                AllOnlinePlayers.getInventory().addItem(gapp);
            } else {
                AllOnlinePlayers.sendMessage(StaticCache.prefix + "§aDu hast §ckeinen§a Gapp bekommen :)");
            }
        }

    }
}
