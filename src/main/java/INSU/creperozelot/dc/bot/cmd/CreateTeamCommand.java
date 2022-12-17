package INSU.creperozelot.dc.bot.cmd;

import INSU.creperozelot.dc.bot.botlogic;
import INSU.creperozelot.utils.MYSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.yaml.snakeyaml.events.Event;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CreateTeamCommand {

    public CreateTeamCommand(String spieler, String teammate, String teamname, int id, SlashCommandInteractionEvent event)  {

        event.getChannel().sendMessage("test").queue();

        if (MYSQL.PlayerExist(spieler)) {
            /**
            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Fehler | Team Erstellung");

            eb.setDescription("Der Spieler **" + spieler + "** Existiert bereits.");

            eb.setTimestamp(LocalDateTime.now());

            eb.setColor(Color.RED);
             event.replyEmbeds(eb.build()).queue();
            **/
            event.getChannel().sendMessage("Failed Player!").queue();


        } else if (MYSQL.PlayerExist(teammate)) {
            /**
            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Fehler | Team Erstellung");

            eb.setDescription("Der Teampartner **" + teammate + "** Existiert bereits.");

            eb.setTimestamp(LocalDateTime.now());

            eb.setColor(Color.RED);

            event.replyEmbeds(eb.build()).queue();
            **/

            event.getChannel().sendMessage("Failed Teammate!").queue();

        } else {
            MYSQL.update("INSERT INTO `INSU` (`PLAYER`, `DEATH`, `ID`, `TEAM`, `ISGAMEMASTER`, `STARTED`) VALUES ('" + spieler + "', 0, " + id + ", '" + teamname + "', 'false', 'false');");
            MYSQL.update("INSERT INTO `INSU` (`PLAYER`, `DEATH`, `ID`, `TEAM`, `ISGAMEMASTER`, `STARTED`) VALUES ('" + teammate + "', 0, " + id + ", '" + teamname + "', 'false', 'false');");

            EmbedBuilder eb = new EmbedBuilder();

            /**
            eb.setTitle("Erfolg | Team Erstellung");

            eb.setDescription("Dem Spieler **" + spieler + "** \n und dem Teampartner **" + teammate + "** wurde das Team **" + teamname + "** zugewisen, mit der ID **" + id + "**.");

            eb.setTimestamp(LocalDateTime.now());

            eb.setColor(Color.GREEN);

            event.replyEmbeds(eb.build()).queue();
             **/
            event.getChannel().sendMessage("Command Completed").queue();

        }
    }
}
