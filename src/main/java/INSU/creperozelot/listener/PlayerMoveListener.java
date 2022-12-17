package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void PlayerMoveListener(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (StaticCache.freeze) {
            event.setCancelled(true);
        }
    }
}
