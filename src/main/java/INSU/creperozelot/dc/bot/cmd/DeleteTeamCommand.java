package INSU.creperozelot.dc.bot.cmd;

import INSU.creperozelot.utils.MYSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DeleteTeamCommand {

    public DeleteTeamCommand(String team, SlashCommandInteractionEvent event){

        //main.getInstance().getLogger().info("System Geht3");

        List<String> list;

        try {
            list = MYSQL.getTeams();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (list.contains(team)){

            try {
                MYSQL.con.createStatement().execute("UPDATE `INSU` SET `TEAM`='' WHERE `TEAM`='" + team + "';");

                EmbedBuilder eb = new EmbedBuilder();

                eb.setTitle("Erfolg | Team gelöscht");

                eb.addField("", "Du hast erfolgreich das team **" + team + "** gelöscht", false);

                eb.setTimestamp(LocalDateTime.now());

                eb.setColor(Color.GREEN);

                event.replyEmbeds(eb.build()).queue();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



        } else {

            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Fehler | Team");

            eb.addField("", "Das team konnte nicht gefunden werden.", false);

            eb.setTimestamp(LocalDateTime.now());

            eb.setColor(Color.ORANGE);

            event.replyEmbeds(eb.build()).queue();

        }

    }

}
