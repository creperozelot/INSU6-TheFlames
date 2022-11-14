package INSU.creperozelot.dc.bot;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class botlogic {

    public static void createBot(){
        String TOKEN = main.getInstance().getConfig().getString("dc.bot.token");
        String PREFIX = main.getInstance().getConfig().getString("dc.bot.prefix");

        JDABuilder builder = JDABuilder.createDefault(TOKEN);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("Insu Staffel 6"));
        StaticCache.jda = builder.build();
        builder.build();

    }

    public static void sendEmbedMessage(MessageEmbed embed, TextChannel channel){

        channel.sendMessageEmbeds(embed).queue();

    }

    public static void sendEmbedMessage(MessageEmbed embed, String channelid){

        TextChannel channel = StaticCache.jda.getTextChannelById(channelid);

        channel.sendMessageEmbeds(embed).queue();



    }



}
