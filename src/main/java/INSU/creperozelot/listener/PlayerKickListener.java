package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.dc.bot.botlogic;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKickListener implements Listener {
    @EventHandler

    public void PlayerKickListener(PlayerKickEvent event) {
        String reason = event.getReason();
        Player player = event.getPlayer();
        StaticCache.kickedplayerlist.add(player.getName());
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Server Kick");
        eb.setDescription("Der Spieler **" + player.getName() + "** wurde vom INSU Server Gekickt! \n\n Grund: " + reason);
        eb.setColor(java.awt.Color.RED);
        botlogic.sendEmbedMessage(eb.build(), "732648259599728661");
        botlogic.sendEmbedMessage(eb.build(), "984755892794843206");
    }
}
