package INSU.creperozelot.dc.bot;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.dc.bot.events.SlashCommandEvent;
import INSU.creperozelot.main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.awt.*;
import java.time.LocalDateTime;

public class botlogic {

    public static void createBot(){
        String TOKEN = main.getInstance().getConfig().getString("dc.bot.token");
        String PREFIX = main.getInstance().getConfig().getString("dc.bot.prefix");

        JDABuilder builder = JDABuilder.createDefault(TOKEN);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("Insu Staffel 6"));
        builder.addEventListeners(new SlashCommandEvent());
        StaticCache.jda = builder.build();

        StaticCache.jda.updateCommands().addCommands(

                Commands.slash("delteam", "Löscht ein team.")
                        .addOption(OptionType.STRING, "team", "Name von dem Team", true),

                Commands.slash("getinfo", "Info vom Spieler")
                        .addOption(OptionType.STRING, "spieler", "Spieler Name", true),

                Commands.slash("teamcreate", "Erstellt ein Team")
                        .addOption(OptionType.STRING, "spieler", "Spieler Name", true)
                        .addOption(OptionType.STRING, "teammate", "Team-Partner Name (NONE wenn nicht vorhanden)", true)
                        .addOption(OptionType.STRING, "teamname", "Der Team Name", true)
                        .addOption(OptionType.INTEGER, "id", "ID des Teams (MUSS FÜR TEAM EINDEUTIG SEIN)", true),

                Commands.slash("list", "Listet alles auf")

        ).queue();

    }

    public static void sendEmbedMessage(MessageEmbed embed, String channelid){

        TextChannel channel = StaticCache.jda.getTextChannelById(channelid);

        channel.sendMessageEmbeds(embed).queue();



    }

    public static EmbedBuilder sendEmbedMessage(String title, String desc, String footer, Color color, boolean timestamp)  {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(title);
        eb.setDescription(desc);
        eb.setColor(color);
        eb.setFooter(footer);

        if (timestamp)  eb.setTimestamp(LocalDateTime.now());


        return eb;
    }

}
