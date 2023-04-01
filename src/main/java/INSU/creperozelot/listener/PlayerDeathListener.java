package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.dc.bot.botlogic;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws SQLException {
        Player player = event.getEntity().getPlayer();
        Entity killer = event.getEntity().getKiller();

        //FERTIG MACHEN

        if (!MYSQL.teammalive(player)) {
            System.out.println("DEBUG DEATH - TEAMCHEST - NO TEAMMATES [[SUCCESS]]");
            File file = new File(main.getInstance().getDataFolder().getAbsolutePath() + "/data/teamchest", "teamchest_" + MYSQL.getTeamIDbyName(player.getName()) + ".yml");
            File file_renamed = new File(main.getInstance().getDataFolder().getAbsolutePath() + "/data/teamchest", "backup_teamchest_" + MYSQL.getTeamIDbyName(player.getName()) + ".yml");

            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            ItemStack[] content = ((List<ItemStack>) configuration.get("inventory.content")).toArray(new ItemStack[0]);
            System.out.println(content.toString());
            for (ItemStack items : content) {
                if (items != null) {
                    player.getWorld().dropItemNaturally(player.getLocation(), items);
                }
            }
            file.renameTo(file_renamed);
            file.delete();
        }

        //MYSQL
        MYSQL.setDeath(player.getName(), 1);

        if (!MYSQL.isGameMaster(player.getName())) {
            player.kickPlayer("§c§lSystem: \n §cDu bist ausgeschieden und wurdest aus der Datenbank entfernt!");
        }

        if (event.getEntity().getKiller() != null) {
            MYSQL.addKill(event.getEntity().getKiller().getName());
        }




        switch (player.getLastDamageCause().getCause()) {
            case ENTITY_ATTACK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.entity_attack").replace("{player}", player.getName()).replace("{entity}", killer.getName()));
                sendEmbed(event);
                break;
            case FALL:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.fall").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case LAVA:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.lava").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case VOID:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.void").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case MAGIC:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.magic").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case POISON:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.poison").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case THORNS:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.thorns").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case WITHER:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.wither").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case MELTING:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.melting").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case CONTACT:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.contact").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case SUICIDE:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.suicide").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case CRAMMING:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.cramming").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case DROWNING:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.drowning").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case LIGHTNING:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.lightning").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case PROJECTILE:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.projectiles").replace("{player}", player.getName()).replace("{entity}", killer.getName()));
                sendEmbed(event);
                break;
            case STARVATION:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.starvation").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case SUFFOCATION:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.suffocation").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case ENTITY_EXPLOSION:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.entity_explosion").replace("{player}", player.getName()).replace("{entity}", killer.getName()));
                sendEmbed(event);
                break;
            case ENTITY_SWEEP_ATTACK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.entity_sweep_atack").replace("{player}", player.getName()).replace("{entity}", killer.getName()));
                sendEmbed(event);
                break;
            case FIRE:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.fire").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case FIRE_TICK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.fire_tick").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case CUSTOM:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.custom").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case HOT_FLOOR:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.hot_floor").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case DRAGON_BREATH:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.dragon_breath").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case FALLING_BLOCK:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.falling_block").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case FLY_INTO_WALL:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.fly_into_wall").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            case BLOCK_EXPLOSION:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.block_explosion").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
            default:
                event.setDeathMessage(StaticCache.prefix + main.getInstance().getConfig().getString("messages.death.others").replace("{player}", player.getName()));
                sendEmbed(event);
                break;
        }


    }

    private void sendEmbed(PlayerDeathEvent event) {
        if (!main.getInstance().getConfig().getBoolean("main.maintenance")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(event.getEntity().getPlayer().getName() + " ist Gestorben");
            eb.setDescription(":skull_crossbones:" + event.getDeathMessage().replace(StaticCache.prefix, ""));
            eb.setColor(Color.BLACK);
            botlogic.sendEmbedMessage(eb.build(), "732648259599728661");
            botlogic.sendEmbedMessage(eb.build(), "984755892794843206");
        }
    }
}
