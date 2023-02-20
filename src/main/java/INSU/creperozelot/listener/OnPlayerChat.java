package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.dc.bot.botlogic;
import INSU.creperozelot.utils.MYSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OnPlayerChat implements Listener {
    @EventHandler
    public void OnPlayerChat(PlayerChatEvent event) throws SQLException {
        Player player = event.getPlayer();
        if (StaticCache.muted_players.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            player.sendMessage(StaticCache.prefix + "§cDu bist gemuted und die Nachricht wurde nicht versendet.");
        } else {
            String message = "**" + player.getName() + ":** " + event.getMessage();

            EmbedBuilder eb = new EmbedBuilder();

            eb.setTimestamp(LocalDateTime.now());
            eb.setDescription(message);
            eb.setColor(Color.ORANGE);

            botlogic.sendEmbedMessage(eb.build(), "732648259599728661");

            if (MYSQL.isGameMaster(player.getName())) {
                event.setFormat(MYSQL.getTeambyName(player.getName()) + " §8|§e " + player.getName() + " §7»§6 " + event.getMessage());
            } else {
                event.setFormat("§e" + player.getName() + " §7»§f " + event.getMessage());
            }
        }
    }
}
