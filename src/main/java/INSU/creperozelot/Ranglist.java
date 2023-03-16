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

public class Ranglist {

    public static void run(){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {


                String timestamp = new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());

                if (timestamp.equals("21-05-00")){

                    try {
                        Statement getKills = MYSQL.con.createStatement();

                        if (getKills.executeQuery("SELECT * FROM INSU WHERE KILLS > 0 ORDER BY KILLS DESC LIMIT 10").next()) {

                            List<String> player = new ArrayList();
                            //Spielername - Teamname

                            ResultSet result = getKills.executeQuery("SELECT * FROM INSU WHERE KILLS > 0 ORDER BY KILLS DESC LIMIT 10");

                            result.first();

                            while (!result.isAfterLast()){

                                String playername = result.getString("PLAYER");

                                int kills = result.getInt("KILLS");


                                player.add(playername + " - " + kills + " Kills");

                                result.next();
                            }

                            String playerlist = "";

                            for (String string : player){

                                playerlist = playerlist + string + "\n";

                            }

                            EmbedBuilder eb = new EmbedBuilder();

                            eb.setTitle("Rangliste!");
                            eb.setTimestamp(LocalDateTime.now());
                            eb.setColor(Color.PINK);
                            eb.addField("Spieler:", playerlist, false);

                            botlogic.sendEmbedMessage(eb.build(), "984755892794843206");

                        } else {

                            EmbedBuilder eb = new EmbedBuilder();

                            eb.setTitle("Rangliste!");
                            eb.setTimestamp(LocalDateTime.now());
                            eb.setColor(Color.PINK);
                            eb.addField("Spieler:", "Keine Spieler gefunden!", false);

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
