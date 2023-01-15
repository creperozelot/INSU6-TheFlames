package INSU.creperozelot.events;


import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TPUP {
    public static void run() {

        StaticCache.eventrunning = true;

        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
            }
        }, 20 * 120);

        for (Player AllOnlinePlayer : Bukkit.getOnlinePlayers()) {

            int HeighestPosz = AllOnlinePlayer.getLocation().getWorld().getHighestBlockYAt( (int) AllOnlinePlayer.getLocation().getX(), (int) AllOnlinePlayer.getLocation().getBlockZ());

            AllOnlinePlayer.teleport(new Location(AllOnlinePlayer.getLocation().getWorld(), AllOnlinePlayer.getLocation().getX(), HeighestPosz, AllOnlinePlayer.getLocation().getZ()));

        }

    }
}
