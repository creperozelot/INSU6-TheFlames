package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Glueckstreffer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
         if (sender instanceof Player) {
             Player player = (Player) sender;
             player.sendMessage(StaticCache.prefix + "§aDas Event Glückstreffer wurde Aktiviert...");


             Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
                 @Override
                 public void run() {
                     utils.broadcastTitle("§a§lEvent §cGlückstreffer", "§fAktivierung läuft...", 20, 80, 0);
                 }
             }, 80L);

             Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
                 @Override
                 public void run() {
                     utils.broadcastTitle("§a§lEvent §cGlückstreffer", "§45 Sekunden Verbleibend", 0, 20, 0);
                 }
             }, 20L);

             Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
                 @Override
                 public void run() {
                     utils.broadcastTitle("§a§lEvent §cGlückstreffer", "§c4 Sekunden Verbleibend", 0, 20, 0);
                 }
             }, 20L);

             Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
                 @Override
                 public void run() {
                     utils.broadcastTitle("§a§lEvent §cGlückstreffer", "§63 Sekunden Verbleibend", 0, 20, 0);
                 }
             }, 20L);

             Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
                 @Override
                 public void run() {
                     utils.broadcastTitle("§a§lEvent §cGlückstreffer", "§e2 Sekunden Verbleibend", 0, 20, 0);
                 }
             }, 20L);

             Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
                 @Override
                 public void run() {
                     utils.broadcastTitle("§a§lEvent §cGlückstreffer", "§a1 Sekunde Verbleibend", 0, 20, 0);
                 }
             }, 20L);

             Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
                 @Override
                 public void run() {
                     utils.broadcastTitle("§a§lEvent §cGlückstreffer", "§2Start!", 0, 20, 10);
                 }
             }, 20L);



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



         } else {
             sender.sendMessage("§cDies kann nur ein Spieler ausführen!");
         }
        return true;
    }
}
