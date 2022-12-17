package INSU.creperozelot.listener;

import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.sql.SQLException;
import java.util.List;

public class PlayerTeamLiveListener implements Listener {
    @EventHandler
    public void PlayerTeamLiveListener(PlayerMoveEvent event) throws SQLException {
        Player player = event.getPlayer();

        if (main.getInstance().getConfig().getBoolean("main.started")) {

        //If Teamplayer death...
            if (MYSQL.PlayerHasTeamMate(player.getName())) {
                List<String> teammatename = MYSQL.getPlayerbyTeam(MYSQL.getTeambyName(player.getName()));
                teammatename.remove(player.getName());
                Player teammate = Bukkit.getPlayer(teammatename.toString().replace("[", "").replace("]", ""));


                utils.sendActionbar(player, "§c§lDein Partner hat noch " + teammate.getHealth() + "§c§l Leben...");
            }
            utils.sendActionbar(player, "§e§lDu hast keinen Partner oder er ist Gestorben...");

        }
    }
}
