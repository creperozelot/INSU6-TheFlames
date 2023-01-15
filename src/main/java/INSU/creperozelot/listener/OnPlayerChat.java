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
        eb.addField("", message, false);
        eb.setColor(Color.ORANGE);


        botlogic.sendEmbedMessage(eb.build(), "1040334586397347870");

        event.setFormat("§e" + player.getName() + " §7|§6 " + event.getMessage());
    }
}
