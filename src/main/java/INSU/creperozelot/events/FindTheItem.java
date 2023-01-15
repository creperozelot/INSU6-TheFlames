package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class FindTheItem implements Listener {
    @EventHandler
    public void FindTheItem(EntityPickupItemEvent event) {
        Player player = (Player) event.getEntity();

        if (StaticCache.pickupevent) {
            if (event.getItem().getType().equals(StaticCache.pickupitem)) {
                StaticCache.pickupevent = false;
                StaticCache.pickupitem = Material.BEDROCK;

                List<Material> materials = Arrays.asList(Material.values());
                int randomMaterial = utils.random(0, materials.size());

                ItemStack newitems = new ItemStack(materials.get(randomMaterial));
                newitems.setAmount(1);

                    player.getInventory().addItem(newitems);
                    player.sendMessage(StaticCache.prefix + "§aDu hast das Item " + StaticCache.pickupitem.name() + " §r§aBekommen.");
                    Bukkit.broadcastMessage(StaticCache.prefix + "§aDer Spieler §a§l" + player.getName() + "§r§ahat Gewonnen");

            }

        }
    }


    public static void run() {

        StaticCache.eventrunning = true;

        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
            }
        }, 20 * 120);

        StaticCache.pickupevent = true;
        List<Material> materials = Arrays.asList(Material.values());
        int randomitem = utils.random(0, materials.size());
        StaticCache.pickupitem = materials.get(randomitem);

        Bukkit.broadcastMessage(StaticCache.prefix + "§aDas Item ist §a§l" + StaticCache.pickupitem.name());
    }
}
