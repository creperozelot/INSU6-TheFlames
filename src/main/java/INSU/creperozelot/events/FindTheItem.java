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
            if (event.getItem().getItemStack().getType() == Material.getMaterial(StaticCache.pickupitem)) {
                StaticCache.pickupevent = false;
                StaticCache.eventrunning = false;
                StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
                StaticCache.pickupitem = -1;

                List<Material> materials = Arrays.asList(Material.values());
                int randomMaterial = utils.random(0, materials.size());

                ItemStack newitems = new ItemStack(materials.get(randomMaterial));

                if (newitems.getType() == Material.DIAMOND_BLOCK || newitems.getType() == Material.GOLD_BLOCK || newitems.getType() == Material.ENDER_PEARL) {
                    newitems.setAmount(utils.random(1, 3));
                } else if (newitems.getType() == Material.DIAMOND || newitems.getType() == Material.GOLD_INGOT) {
                    newitems.setAmount(utils.random(1, 32));
                } else {
                    newitems.setAmount(utils.random(1, 64));
                }

                    player.getInventory().addItem(newitems);
                    player.sendMessage(StaticCache.prefix + "§aDu hast das Item " + Material.getMaterial(newitems.getTypeId()).name().replace("_", "") + " §r§aBekommen.");
                    Bukkit.broadcastMessage(StaticCache.prefix + "§aDer Spieler §a§l" + player.getName() + "§r§ahat Gewonnen");

            }

        }
    }


    public static void run() {

        StaticCache.eventrunning = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
                StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
            }
        }.runTaskLater(main.getInstance(),20 * 120);

        StaticCache.pickupevent = true;
        List<Material> materials = Arrays.asList(Material.values());
        int randomitem = utils.random(0, materials.size());
        StaticCache.pickupitem = materials.get(randomitem).getId();

        Bukkit.broadcastMessage(StaticCache.prefix + "§aDas Item ist §a§l" + Material.getMaterial(StaticCache.pickupitem).name().replace("_", ""));
        StaticCache.bossBar.setTitle("§6Event §8» §aDas Item ist §a§l" + Material.getMaterial(StaticCache.pickupitem).name().replace("_", " "));
    }
}
