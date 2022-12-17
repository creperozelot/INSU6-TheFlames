package INSU.creperozelot.dc.bot.cmd;

import INSU.creperozelot.utils.MYSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommandTest {
    public CommandTest(String spieler, String teammate, String teamname, int id, SlashCommandInteractionEvent event) throws SQLException {
        if (!MYSQL.PlayerExist(spieler) && !MYSQL.PlayerExist(teammate)) {

            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Erfolg | Team Erstellung");

            eb.setDescription("Das Team **" + teamname +  "** wurde mit dem Spieler **" + spieler + "**, dem Teammante **" + teammate + "** und der ID **" + id + "** erstellt.");

            eb.setTimestamp(LocalDateTime.now());

            eb.setColor(Color.GREEN);

            event.getChannel().sendMessageEmbeds(eb.build()).queue();

            MYSQL.CreatePlayer(spieler, 0, teamname, id, "false", "false");
            MYSQL.CreatePlayer(teammate, 0, teamname, id, "false", "false");
        } else {
            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Fehler | Team Erstellung");

            eb.setDescription("Das Team konnte nicht erstellt werden.\n Der Spieler oder der Teammate ist bereits vorhanden!");

            eb.setTimestamp(LocalDateTime.now());

            eb.setColor(Color.RED);

            event.getChannel().sendMessageEmbeds(eb.build()).queue();
        }
    }
}
