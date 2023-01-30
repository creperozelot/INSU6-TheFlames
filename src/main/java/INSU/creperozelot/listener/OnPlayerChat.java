package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.dc.bot.botlogic;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.awt.*;
import java.time.LocalDateTime;

public class OnPlayerChat implements Listener {
    @EventHandler
    public void OnPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = "**" + player.getName() + ":** " + event.getMessage();

        EmbedBuilder eb = new EmbedBuilder();

        eb.setTimestamp(LocalDateTime.now());
        eb.setDescription(message);
        eb.setColor(Color.ORANGE);

        if  (StaticCache.muted_players.contains(player.getName())) {
            event.setCancelled(true);
            player.sendMessage(StaticCache.prefix + "§cDu bist gemuted und die Nachricht wurde nicht versendet.");
        }


        botlogic.sendEmbedMessage(eb.build(), "732648259599728661");

        event.setFormat("§e" + player.getName() + " §7|§6 " + event.getMessage());
    }
}
