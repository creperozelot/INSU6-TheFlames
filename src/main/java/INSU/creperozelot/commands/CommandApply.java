package INSU.creperozelot.commands;

import INSU.creperozelot.dc.bot.utils.utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.time.LocalDateTime;

public class CommandApply {
    public CommandApply(String applytype, User dcname, String sender, String partner, String reason, SlashCommandInteractionEvent event) {
        if (utils.hasRole(event.getMember(), "1039607882192265276")) {

            User user = dcname;

            switch (applytype) {
                case "accept":

                    EmbedBuilder eb = new EmbedBuilder();

                    eb.setTitle("Angenommen | Bewerbungs Status");

                    eb.setDescription("**Herzlichen Glückwunsch** \n Du " + dcname.getAsMention() + " **(" + sender + ")** und dein Teampartner **" + partner + "** wurden bei INSU Staffel 6 angenommen!");

                    eb.setTimestamp(LocalDateTime.now());

                    eb.setColor(Color.GREEN);

                    user.openPrivateChannel()
                            .flatMap(channel -> channel.sendMessageEmbeds(eb.build()))
                            .queue();

                    Role teilnehmer = event.getGuild().getRoleById("1055874436655497267");
                    event.getGuild().addRoleToMember(user, teilnehmer).queue();

                    EmbedBuilder eb3 = new EmbedBuilder();

                    eb3.setTitle("Erfolg | Bewerbungs Status");

                    eb3.setDescription("Bewerbungs Status geändert! \n **Bitte führe nun den CMD /teamcreate aus und erstelle ein TEAM**");

                    eb3.setTimestamp(LocalDateTime.now());

                    eb3.setColor(Color.GREEN);

                    event.replyEmbeds(eb3.build()).setEphemeral(true).queue();


                    break;
                case "decline":

                    EmbedBuilder eb1 = new EmbedBuilder();

                    eb1.setTitle("Abgelehnt | Bewerbungs Status");

                    eb1.setDescription("**Es tut uns leid,** \n Du " + dcname.getAsMention() + " **(" + sender + ")** und dein Teampartner **" + partner + "** wurden bei INSU Staffel 6 abgelehnt! \n\n **Grund:** " + reason);

                    eb1.setTimestamp(LocalDateTime.now());

                    eb1.setColor(Color.GREEN);

                    user.openPrivateChannel()
                            .flatMap(channel -> channel.sendMessageEmbeds(eb1.build()))
                            .queue();

                    EmbedBuilder eb4 = new EmbedBuilder();

                    eb4.setTitle("Erfolg | Bewerbungs Status");

                    eb4.setDescription("Bewerbungs Status geändert!");

                    eb4.setTimestamp(LocalDateTime.now());

                    eb4.setColor(Color.GREEN);

                    event.replyEmbeds(eb4.build()).setEphemeral(true).queue();

                default:
                    EmbedBuilder eb2 = new EmbedBuilder();

                    eb2.setTitle("Fehler | Bewerbungs Status");

                    eb2.setDescription("Du musst bei applytype **accept/decline** eingeben!");

                    eb2.setTimestamp(LocalDateTime.now());

                    eb2.setColor(Color.RED);

                    event.replyEmbeds(eb2.build()).setEphemeral(true).queue();
                    break;
            }

        } else {
            event.reply(utils.noperms()).queue();
        }
    }
}
