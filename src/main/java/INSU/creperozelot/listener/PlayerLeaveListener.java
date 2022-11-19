package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
    @EventHandler
    public void PlayerLeaveListener(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(StaticCache.prefix + "Der Spieler §e" + player.getDisplayName() + "§6 hat das Projekt verlassen.");
    }
}
