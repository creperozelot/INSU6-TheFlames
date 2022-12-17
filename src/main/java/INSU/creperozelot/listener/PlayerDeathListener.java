package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.SQLException;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws SQLException {
        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        //MYSQL
        MYSQL.setDeath(player.getName(), 1);


        switch (player.getLastDamageCause().getCause()) {
            case ENTITY_ATTACK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.entity_attack").replace("{player}", killer.getName()));
                break;
            case FALL:
                break;
            case LAVA:
                break;
            case VOID:
                break;
            case MAGIC:
                break;
            case POISON:
                break;
            case THORNS:
                break;
            case WITHER:
                break;
            case MELTING:
                break;
            case CONTACT:
                break;
            case SUICIDE:
                break;
            case CRAMMING:
                break;
            case DROWNING:
                break;
            case LIGHTNING:
                break;
            case PROJECTILE:
                break;
            case STARVATION:
                break;
            case SUFFOCATION:
                break;
            case ENTITY_EXPLOSION:
                break;
            case ENTITY_SWEEP_ATTACK:
                break;
            case FIRE:
                break;
            case FIRE_TICK:
                break;
            case CUSTOM:
                break;
            case HOT_FLOOR:
                break;
            case DRAGON_BREATH:
                break;
            case FALLING_BLOCK:
                break;
            case FLY_INTO_WALL:
                break;
            case BLOCK_EXPLOSION:
                break;
        }

        if (!MYSQL.isGameMaster(player.getName())) {
            MYSQL.update("DELETE FROM `INSU` WHERE `PLAYER`='" + player.getName() + "';");
        }

    }
}
