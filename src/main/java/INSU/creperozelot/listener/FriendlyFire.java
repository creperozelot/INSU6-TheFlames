package INSU.creperozelot.listener;

import INSU.creperozelot.utils.MYSQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.sql.SQLException;

public class FriendlyFire implements Listener {
    @EventHandler
    public void FriendlyFire(EntityDamageByEntityEvent event) throws SQLException {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            System.out.println("DEBUG 1");
            if (MYSQL.arePlayersinTeams(event.getEntity().getName(), event.getDamager().getName())) {
                event.setCancelled(true);
            }
        }
    }
}
