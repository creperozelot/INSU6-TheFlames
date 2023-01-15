package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.SQLException;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws SQLException {
        Player player = event.getEntity().getPlayer();
        Entity killer = event.getEntity().getKiller();

        //MYSQL
        MYSQL.setDeath(player.getName(), 1);


        switch (player.getLastDamageCause().getCause()) {
            case ENTITY_ATTACK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.entity_attack").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case FALL:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.fall").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case LAVA:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.lava").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case VOID:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.void").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case MAGIC:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.magic").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case POISON:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.poison").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case THORNS:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.thorns").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case WITHER:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.wither").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case MELTING:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.melting").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case CONTACT:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.contact").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case SUICIDE:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.suicide").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case CRAMMING:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.cramming").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case DROWNING:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.drowning").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case LIGHTNING:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.lightning").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case PROJECTILE:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.projectiles").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case STARVATION:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.starvation").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case SUFFOCATION:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.suffocation").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case ENTITY_EXPLOSION:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.entity_explosion").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case ENTITY_SWEEP_ATTACK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.entity_sweep_atack").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case FIRE:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.fire").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case FIRE_TICK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.fire_tick").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case CUSTOM:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.custom").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case HOT_FLOOR:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.hot_floor").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case DRAGON_BREATH:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.dragon_breath").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case FALLING_BLOCK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.falling_block").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case FLY_INTO_WALL:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.fly_into_wall").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            case BLOCK_EXPLOSION:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.block_explosion").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
            default:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.others").replace("{player}", killer.getName()).replace("{killer}", killer.getName()));
                break;
        }

        if (!MYSQL.isGameMaster(player.getName())) {
            player.kickPlayer("§c§lSystem: \n §cDu bist ausgeschieden und wurdest aus der Datenbank entfernt!");
            MYSQL.update("DELETE FROM INSU WHERE PLAYER='" + player.getName() + "';");
        }

    }
}
