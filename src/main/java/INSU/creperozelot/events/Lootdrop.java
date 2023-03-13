package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lootdrop {
    public static boolean run() {

        new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
                StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
            }
        }.runTaskLater(main.getInstance(), 120 * 20);

        StaticCache.eventrunning = true;

        Player randomonlineplayer = utils.randomOnlinePlayer();
        int posx = utils.random((int) randomonlineplayer.getLocation().getX() - 75, (int) randomonlineplayer.getLocation().getX() + 75);
        int posz = utils.random((int) randomonlineplayer.getLocation().getZ() - 75, (int) randomonlineplayer.getLocation().getZ() + 75);
        int posy = Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).getHighestBlockYAt(posx, posz);
        Location chestloc = new Location(Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")), posx, posy, posz);


        List<Material> items = new ArrayList<>(Arrays.asList(Material.values()));


        // Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).getBlockAt(posx, posy + 1, posz).setType(Material.CHEST);

        Block block = (Block) chestloc.getBlock();
        chestloc.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) block.getState();

            ItemStack item1 = new ItemStack(items.get(utils.random(0, items.size())));
            item1.setAmount(1);
            ItemStack item2 = new ItemStack(items.get(utils.random(0, items.size())));
            item2.setAmount(1);
            ItemStack item3 = new ItemStack(items.get(utils.random(0, items.size())));
            item3.setAmount(1);
            chest.getBlockInventory().setItem(utils.random(0, 26), item1);
            chest.getBlockInventory().setItem(utils.random(0, 26), item2);
            chest.getBlockInventory().setItem(utils.random(0, 26), item3);
            chest.update();
            StaticCache.bossBar.setTitle("§cEvent » §aDie Chest ist hier X: " + (int) chestloc.getX() + " Y: " +  (int) chestloc.getY() + " Z: " + (int) chestloc.getZ());
            return true;

    }
}
