package INSU.creperozelot.events;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lootdrop {
    public static boolean run() {
        Player randomonlineplayer = utils.randomOnlinePlayer();
        int posx = utils.random((int) randomonlineplayer.getLocation().getX() - 75, (int) randomonlineplayer.getLocation().getX() + 75);
        int posz = utils.random((int) randomonlineplayer.getLocation().getZ() - 75, (int) randomonlineplayer.getLocation().getZ() + 75);
        int posy = Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).getHighestBlockYAt(posx, posz);
        Location chestloc = new Location(Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")), posx, posy, posz);


        List<Material> items = new ArrayList<>(Arrays.asList(Material.values()));


        Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")).getBlockAt(posx, posy + 1, posz).setType(Material.CHEST);

        if (chestloc.getBlock() instanceof Chest) {
            Chest chest = (Chest) chestloc.getBlock().getState();
            ItemStack item1 = new ItemStack(items.get(utils.random(0, items.size())));
            item1.setAmount(1);
            ItemStack item2 = new ItemStack(items.get(utils.random(0, items.size())));
            item2.setAmount(1);
            ItemStack item3 = new ItemStack(items.get(utils.random(0, items.size())));
            item3.setAmount(1);
            chest.getInventory().setItem(utils.random(0, 26), item1);
            chest.getInventory().setItem(utils.random(0, 26), item2);
            chest.getInventory().setItem(utils.random(0, 26), item3);
            chest.update();
            return true;

        } else {
            return false;
        }
    }
}
