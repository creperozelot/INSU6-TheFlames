package INSU.creperozelot.dc.bot.cmd;

import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CreateTeamCommand {
    public CreateTeamCommand(String spieler, String teammate, String teamname, int id, SlashCommandInteractionEvent event) throws SQLException {
        if (utils.dchasrole(event.getMember(), "998618192257765376")) {
            if (!MYSQL.PlayerExist(spieler) && !MYSQL.PlayerExist(teammate)) {

                EmbedBuilder eb = new EmbedBuilder();

                eb.setTitle("Erfolg | Team Erstellung");

                eb.setDescription("Das Team **" + teamname + "** wurde mit dem Spieler **" + spieler + "**, dem Teammante **" + teammate + "** und der ID **" + id + "** erstellt.");

                eb.setTimestamp(LocalDateTime.now());

                eb.setColor(Color.GREEN);

                event.replyEmbeds(eb.build()).setEphemeral(true).queue();

                MYSQL.CreatePlayer(spieler.replace(" ", "_"), 0, teamname, id, "false", "false");
                MYSQL.CreatePlayer(teammate.replace(" ", "_"), 0, teamname, id, "false", "false");
            } else {
                EmbedBuilder eb = new EmbedBuilder();

                eb.setTitle("Fehler | Team Erstellung");

                eb.setDescription("Das Team konnte nicht erstellt werden.\n Der Spieler oder der Teammate ist bereits vorhanden!");

                eb.setTimestamp(LocalDateTime.now());

                eb.setColor(Color.RED);

                event.replyEmbeds(eb.build()).setEphemeral(true).queue();
            }
        } else {
            event.reply(utils.permsmsgdc()).setEphemeral(true).queue();
        }
    }
}
