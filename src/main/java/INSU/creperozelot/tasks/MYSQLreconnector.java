package INSU.creperozelot.tasks;

import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class MYSQLreconnector {
    public static void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (MYSQL.isConnected()) {
                    MYSQL.disconnect();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            MYSQL.connect();
                            MYSQL.update("CREATE TABLE IF NOT EXISTS INSU(PLAYER varchar(64), DEATH int, ID int, TEAM varchar(64), ISGAMEMASTER varchar(32), STARTED varchar(32));");

                        }
                    }.runTaskLater(main.getInstance(), 20 * 5);
                } else {
                    MYSQL.disconnect();

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            MYSQL.connect();
                        }
                    }.runTaskLater(main.getInstance(), 20 * 5);
                }
            }
        }.runTaskTimer(main.getInstance(), 0, 20 * 60 * 40);
    }
}
