package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.dc.bot.botlogic;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
    @EventHandler
    public void PlayerLeaveListener(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        StaticCache.bossBar.removePlayer(player);
        event.setQuitMessage(StaticCache.prefix + "Der Spieler §e" + player.getName() + "§6 hat das Projekt verlassen.");



        StaticCache.onlineplayerlist.remove(player.getName());

        if (!StaticCache.kickedplayerlist.contains(player.getName())) {
            StaticCache.kickedplayerlist.remove(player.getName());
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Server Leave");
            eb.setDescription("Der Spieler **" + player.getName() + "** hat den INSU Server verlassen!");
            eb.setColor(java.awt.Color.RED);
            botlogic.sendEmbedMessage(eb.build(), "732648259599728661");
            botlogic.sendEmbedMessage(eb.build(), "984755892794843206");
        } else {
            event.setQuitMessage("");
        }
    }
}
