package INSU.creperozelot.tasks;

import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.List;

public class LiveDisplayTask {
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        for (Player AllOnlinePlayers : Bukkit.getOnlinePlayers()) {
                            try {
                                if (MYSQL.PlayerHasTeamMate(AllOnlinePlayers.getName())) {
                                    List<String> Teammate = MYSQL.getPlayerbyTeam(MYSQL.getTeambyName(AllOnlinePlayers.getName()));
                                    Teammate.remove(AllOnlinePlayers.getName());
                                    String Teammatename = Teammate.toString().replace("[", "").replace("]", "");
                                    Player teammate = Bukkit.getPlayer(Teammatename);

                                    if (teammate != null) utils.sendActionbar(AllOnlinePlayers, "§c§lDein Partner hat noch §4§l" + (int) teammate.getHealth() + " §c§lLeben.");
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }, 20, 20);

    }
}
