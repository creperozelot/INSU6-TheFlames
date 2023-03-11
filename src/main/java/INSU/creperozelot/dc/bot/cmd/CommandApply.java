package INSU.creperozelot.dc.bot.cmd;

import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.time.LocalDateTime;

public class CommandApply {
    public CommandApply(String applytype, User dcname, User dcnamepartner, String sender, String partner, String reason, SlashCommandInteractionEvent event) {
        if (utils.dchasrole(event.getMember(), "998618192257765376")) {

            User user = dcname;
            User user2 = dcnamepartner;

            switch (applytype) {
                case "accept":

                    EmbedBuilder eb = new EmbedBuilder();

                    eb.setTitle("Angenommen | Bewerbungs Status");

                    eb.setDescription("**Herzlichen Glückwunsch** \n Du " + dcname.getAsMention() + " **(" + sender + ")** und dein Teampartner **" + partner + "** wurden bei INSU Staffel 6 angenommen! \n\n Bitte denk dran noch niemanden zu erzählen, dass du mit deinen jeweiligen Teampartner-Teams, weil wir dies bis zur Veröffentlichung (18.03.23) **__nicht bekannt haben möchten!__** Bitte schicke zudem **__ᦔRollyLP#1700__** deinen und den Skin von deinem Teampartner über die Privatnachrichten Funktion von Discord (PN), weil Toad dies für den Content Bereich von INSU braucht.\n **__Bei Fragen bitte ich diese in dem Teilnehmer Chat von INSU stellen!__** \n\n **PS: Du und dein Teampartner müssten auf dem INSU Discord jetzt den Teilnehmer Rang haben!**");

                    eb.setTimestamp(LocalDateTime.now());

                    eb.setColor(Color.GREEN);

                    user.openPrivateChannel()
                            .flatMap(channel -> channel.sendMessageEmbeds(eb.build()))
                            .queue();

                    EmbedBuilder eb6 = new EmbedBuilder();

                    eb6.setTitle("Angenommen | Bewerbungs Status");

                    eb6.setDescription("**Herzlichen Glückwunsch** \n Du " + dcnamepartner.getAsMention() + " **(" + partner + ")** und dein Teampartner **" + sender + "** wurden bei INSU Staffel 6 angenommen! \n\n Bitte denk dran noch niemanden zu erzählen, dass du mit deinen jeweiligen Teampartner-Teams, weil wir dies bis zur Veröffentlichung (18.03.23) **__nicht bekannt haben möchten!__** Bitte schicke zudem **__ᦔRollyLP#1700__** deinen und den Skin von deinem Teampartner über die Privatnachrichten Funktion von Discord (PN), weil Toad dies für den Content Bereich von INSU braucht.\n **__Bei Fragen bitte ich diese in dem Teilnehmer Chat von INSU stellen!__** \n\n **PS: Du und dein Teampartner müssten auf dem INSU Discord jetzt den Teilnehmer Rang haben!**");

                    eb6.setTimestamp(LocalDateTime.now());

                    eb6.setColor(Color.GREEN);

                    user2.openPrivateChannel()
                            .flatMap(channel -> channel.sendMessageEmbeds(eb6.build()))
                            .queue();

                    Role teilnehmer = event.getGuild().getRoleById("998616111358685244");
                    event.getGuild().addRoleToMember(user, teilnehmer).queue();
                    event.getGuild().addRoleToMember(user2, teilnehmer).queue();

                    int id = MYSQL.getHighestID() + 1;

                    EmbedBuilder eb3 = new EmbedBuilder();

                    eb3.setTitle("Erfolg | Bewerbungs Status");

                    eb3.setDescription("Bewerbungs Status geändert! \n **Bitte führe nun den CMD /teamcreate aus und erstelle ein TEAM** \n **Empfohlene ID: **" + id);

                    eb3.setTimestamp(LocalDateTime.now());

                    eb3.setColor(Color.GREEN);

                    event.replyEmbeds(eb3.build()).setEphemeral(true).queue();


                    break;
                case "decline":

                    EmbedBuilder eb1 = new EmbedBuilder();

                    eb1.setTitle("Abgelehnt | Bewerbungs Status");

                    eb1.setDescription("**Es tut uns leid,** \n Du " + dcname.getAsMention() + " **(" + sender + ")** und dein Teampartner **" + partner + "** wurden bei INSU Staffel 6 abgelehnt! \n\n **Grund:** " + reason + "\n\n Bei Fragen bitte melde dich bei den **__Teilnehmermanagern__**:\n" +
                            "Entweder **__ᦔRollyLP#1700__** oder **__MaximalFlame#0327__** über die Privatnachrichten Funktion von Discord (PN)!\n" +
                            "\n" +
                            "Vielen Dank für dein Verständnis!");

                    eb1.setTimestamp(LocalDateTime.now());

                    eb1.setColor(Color.RED);

                    user.openPrivateChannel()
                            .flatMap(channel -> channel.sendMessageEmbeds(eb1.build()))
                            .queue();

                    EmbedBuilder eb5 = new EmbedBuilder();

                    eb5.setTitle("Abgelehnt | Bewerbungs Status");

                    eb5.setDescription("**Es tut uns leid,** \n Du " + dcnamepartner.getAsMention() + " **(" + partner + ")** und dein Teampartner **" + sender + "** wurden bei INSU Staffel 6 abgelehnt! \n\n **Grund:** " + reason + "\n\n Bei Fragen bitte melde dich bei den **__Teilnehmermanagern__**:\n" +
                                    "Entweder **__ᦔRollyLP#1700__** oder **__MaximalFlame#0327__** über die Privatnachrichten Funktion von Discord (PN)!\n" +
                                    "\n" +
                                    "Vielen Dank für dein Verständnis!");

                    eb5.setTimestamp(LocalDateTime.now());

                    eb5.setColor(Color.RED);

                    user2.openPrivateChannel()
                            .flatMap(channel -> channel.sendMessageEmbeds(eb5.build()))
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
            event.reply(utils.permsmsgdc()).setEphemeral(true).queue();
        }
    }
}
