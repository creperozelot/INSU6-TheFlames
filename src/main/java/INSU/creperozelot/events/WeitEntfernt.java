package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class WeitEntfernt {

    //MYSQL mysql = new MYSQL();

    public static void run(){

        StaticCache.eventrunning = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
                StaticCache.eventrunning = false;
            }
        }.runTaskLater(main.getInstance(),20 * 120);

        weitEntfernt();

    }

    public static void weitEntfernt(){

        try {


            double distance = 230987465;

            Player playerdistance = null;

            for (Player player : Bukkit.getOnlinePlayers()){

                //player soll nächsten spieler tracken, prüfen ob im team und ob nächster

                String mate = null;

                if (MYSQL.getPlayerbyTeam(MYSQL.getTeambyName(player.getName())).get(0).equals(player.getName())){

                    mate = MYSQL.getPlayerbyTeam(MYSQL.getTeambyName(player.getName())).get(1);

                } else {

                    mate = MYSQL.getPlayerbyTeam(MYSQL.getTeambyName(player.getName())).get(0);


                }



                for (Player player1 : Bukkit.getOnlinePlayers()){

                    if (!player.getName().equals(player1.getName())){

                        if (!player1.getName().equals(mate)){

                            double distanceplayer = player.getLocation().distance(player1.getLocation());

                            if (distanceplayer < distance){

                                distance = distanceplayer;

                                playerdistance = player1;

                            }

                        }
                    }

                }

                StaticCache.bossBar.setTitle("§cEvent » §a§lX: " + playerdistance.getLocation().getX() + " §a§lY: " + playerdistance.getLocation().getBlockY() + "§a§lZ: " + playerdistance.getLocation().getBlockZ());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
