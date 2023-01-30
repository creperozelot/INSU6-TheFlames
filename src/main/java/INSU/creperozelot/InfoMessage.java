package INSU.creperozelot;

import INSU.creperozelot.dc.bot.botlogic;
import INSU.creperozelot.utils.MYSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class InfoMessage {

    public static void deathInfo(){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {

                // DEAHT = 0 oder 2 => nichts || DEATH = 1 => Schreibe Nachricht und setze auf 2

                String timestamp = new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());

                if (timestamp.equals("20-30-00")){

                    try {
                        Statement getDeathPlayer = MYSQL.con.createStatement();

                        if (getDeathPlayer.executeQuery("SELECT * FROM `INSU` WHERE `DEATH`='1'").next()) {

                            List<String> player = new ArrayList();
                            //Spielername - Teamname

                            ResultSet result = getDeathPlayer.executeQuery("SELECT * FROM `INSU` WHERE `DEATH`='1'");

                            result.first();

                            while (!result.isAfterLast()){

                                String playername = result.getString("PLAYER");

                                String team = result.getString("TEAM");

                                //INFO >>>> Kannst auch MYSQL.setDeath(playername, deathid); benutzen. Also MYSQL.setDeath(player.getName(), 2);
                                MYSQL.con.createStatement().execute("UPDATE `INSU` SET `DEATH` = '2' WHERE `PLAYER` = '" + playername + "'");

                                player.add(playername + " - " + team);

                                result.next();
                            }

                            String playerlist = "";

                            for (String string : player){

                                playerlist = playerlist + string + "\n";

                            }

                            EmbedBuilder eb = new EmbedBuilder();

                            eb.setTitle("Gestorbene Spieler!");
                            eb.setTimestamp(LocalDateTime.now());
                            eb.setColor(Color.ORANGE);
                            eb.addField("Spieler:", playerlist, false);

                            botlogic.sendEmbedMessage(eb.build(), "984755892794843206");

                        } else {

                            EmbedBuilder eb = new EmbedBuilder();

                            eb.setTitle("Gestorbene Spieler!");
                            eb.setTimestamp(LocalDateTime.now());
                            eb.setColor(Color.ORANGE);
                            eb.addField("Spieler:", "Keiner ist gestorben!", false);

                            botlogic.sendEmbedMessage(eb.build(), "984755892794843206");

                        }


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }



            }
        }, 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats

    }
}
