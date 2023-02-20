package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.SQLException;


public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPLayerJoin(PlayerJoinEvent event) throws SQLException {

        if (!MYSQL.isConnected()) {
            event.getPlayer().kickPlayer("§c§lSystemfehler: \n §r§cDatenbank nicht verbunden!");
        } else if (StaticCache.storyrunning) {
            event.getPlayer().kickPlayer("§c§lFehler: \n §cINSU Startet gerade, bitte warte kurz und versuche es nach dem Start erneut.");
        } else if (!main.getInstance().getConfig().getBoolean("main.maintenance") || MYSQL.isGameMaster(event.getPlayer().getName())) {
            if (!MYSQL.isStarted(event.getPlayer().getName()) && main.getInstance().getConfig().getBoolean("main.started")) {
                event.getPlayer().teleport(new Location(Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")), 1, 1, 1));
                MYSQL.setStarted(event.getPlayer().getName(), "true");
            }

            Player player = event.getPlayer();
            int OnlinePlayers = Bukkit.getOnlinePlayers().size();
            StaticCache.bossBar.addPlayer(player);

            event.setJoinMessage(StaticCache.prefix + "Der Spieler §e" + player.getDisplayName() + "§6 ist dem Projekt Beigetreten.");
            player.sendMessage(StaticCache.prefix + "§cBEACHTE! Alles was du sagst wird geloggt! Wähle deine Worte weise.");
            player.sendMessage(StaticCache.prefix + "Es sind §e" + OnlinePlayers + "§f Spieler §aOnline...");
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 15, 255, false, false, Color.BLACK), false);
            player.sendTitle("§6Achtung...", "§cDu bist für 15 Skunden nicht Verwundbar", 0, 80, 20);
            player.sendMessage(StaticCache.prefix + "§aDu bist im Team §6" + MYSQL.getTeambyName(player.getName()));

            //players stuff
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "title " + player.getName() + " timings 10 60 10");


            if (MYSQL.isGameMaster(player.getName())) {
                player.setDisplayName("§e§lGamemaster §r§8|§r§f " + player.getName());
                player.setCustomName("§e§lGamemaster §r§8|§r§f " + player.getName());
            } else {
                String teamname = MYSQL.getTeambyName(player.getName());
                player.setDisplayName("§6" + teamname + " §8§r|§f " + player.getName());
                player.setCustomName("§6" + teamname + " §8§r|§f " + player.getName());
            }

            if (!MYSQL.PlayerExist(player.getName())) {
                player.kickPlayer("§c§lFehler: \n §r§cDu bist nicht in der Datenbank eingetragen. Dies kann folgende Gründe haben: \n §c1. Du hast dich nicht bei INSU beworben. \n §c2. Du wurdest nicht bei INSU aktzeptiert. \n §c3. Du bist Tot und bist damit AUsgeschieden.");
            }
        } else {
            Player player = event.getPlayer();
                player.kickPlayer("§c§lWartungen: \n §r§cGerade befindet sich INSU in den Wartungen, versuche es später noch einmal.");
        }


    }
}
