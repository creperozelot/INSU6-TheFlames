package INSU.creperozelot.dc.bot.events;

import INSU.creperozelot.dc.bot.cmd.DeleteTeamCommand;
import INSU.creperozelot.dc.bot.cmd.GetInfoCommand;
import INSU.creperozelot.main;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandEvent extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        //main.getInstance().getLogger().info("System Geht");

        switch (event.getName()){

            case "delteam":
                new DeleteTeamCommand(event.getOption("team").getAsString(), event);
                break;
            case "getinfo":
                new GetInfoCommand(event.getOption("spieler").getAsString(), event);
                break;

        }

    }
}
