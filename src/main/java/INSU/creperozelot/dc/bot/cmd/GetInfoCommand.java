package INSU.creperozelot.dc.bot.cmd;

import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class GetInfoCommand {
    public GetInfoCommand(String spieler, SlashCommandInteractionEvent event) {
        if (utils.dchasrole(event.getMember(), "998618192257765376")) {
            try {
                if (MYSQL.PlayerExist(spieler)) {

                    EmbedBuilder eb = new EmbedBuilder();
                    String teamname = MYSQL.getTeambyName(spieler);
                    List<String> teamplayers = MYSQL.getPlayerbyTeam(teamname);
                    teamplayers.remove(spieler);
                    String ID = MYSQL.getIDbyName(spieler);
                    String DeathState = MYSQL.getDeathStatebyName(spieler);
                    boolean gamemaster = MYSQL.isGameMaster(spieler);

                    if (teamplayers.size() == 0) {
                        teamplayers.add("Kein Teampartner gefunden");
                    }

                    String isgamemaster = "";

                    if (gamemaster) {
                        isgamemaster = "Ja";
                    } else {
                        isgamemaster = "Nein";
                    }




                    eb.setTitle(spieler + " | Get Info");
                    eb.setDescription("Name: **" + spieler + "**\n Teampartner: **" + teamplayers.toString().replace("[","").replace("]", "") + "**\n" + "Teamname: **" + teamname + "** \n ID: **" + ID + "**\n Todes Status: **" + DeathState + "** \n Gamemaster: **" + isgamemaster + "**");
                    eb.setTimestamp(LocalDateTime.now());
                    eb.setColor(Color.ORANGE);
                    event.replyEmbeds(eb.build()).queue();
                } else {
                    EmbedBuilder eb = new EmbedBuilder();

                    eb.setTitle("Fehler | Get Info");
                    eb.setDescription("Der Spieler **" + spieler + "** wurde nicht in der Datenbank gefunden.");
                    eb.setTimestamp(LocalDateTime.now());
                    eb.setColor(Color.RED);
                    event.replyEmbeds(eb.build()).queue();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            event.reply(utils.permsmsgdc()).setEphemeral(true).queue();
        }

    }
}
