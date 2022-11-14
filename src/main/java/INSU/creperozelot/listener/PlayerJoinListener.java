package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.Bukkit;
import org.bukkit.Color;
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
        Player player = event.getPlayer();
        int OnlinePlayers = Bukkit.getOnlinePlayers().size();

        event.setJoinMessage(StaticCache.prefix + "Der Spieler §e" + player.getName() + "§6 ist dem Projekt Beigetreten.");
        player.sendMessage(StaticCache.prefix + "§cBEACHTE! Alles was du sagst wird geloggt! Wähle deine Worte weise.");
        player.sendMessage(StaticCache.prefix + "Es sind §e" + OnlinePlayers + "§f Spieler §aOnline...");
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 15, 255, false, false, Color.BLACK), false);
        player.sendTitle("§6Achtung...", "§cDu bist für 15 Skunden nicht Verwundbar", 0, 80, 20);

        //MYSQL
        if (!MYSQL.PlayerExist(player.getName())) {
            MYSQL.update("INSERT INTO `INSU`(`PLAYER`, `DEATH`, `ID`, `TEAM`, `ISGAMEMASTER`) VALUES ('" + player.getName() + "','0','-1','NONE','false');");
        }
    }
}
