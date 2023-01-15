package INSU.creperozelot.dc.bot.events;

import INSU.creperozelot.commands.CommandApply;
import INSU.creperozelot.commands.CommandTeamRegister;
import INSU.creperozelot.dc.bot.cmd.*;
import INSU.creperozelot.main;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.sql.SQLException;

public class SlashCommandEvent extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) throws RuntimeException {

        if (!event.getUser().isBot()) {

            //main.getInstance().getLogger().info("System Geht");

            switch (event.getName()) {

                case "delteam":
                    new DeleteTeamCommand(event.getOption("team").getAsString(), event);
                    break;
                case "getinfo":
                    new GetInfoCommand(event.getOption("spieler").getAsString(), event);
                    break;
                case "teamcreate":
                    try {
                        new CommandTest(event.getOption("spieler").getAsString(), event.getOption("teammate").getAsString(), event.getOption("teamname").getAsString(), event.getOption("id").getAsInt(), event);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "list":
                    try {
                        new CommandList(event);
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "apply":
                    new CommandApply(event.getOption("apply-type").getAsString(), event.getOption("dcname").getAsUser(), event.getOption("sender").getAsString(), event.getOption("partner").getAsString(), event.getOption("reason").getAsString(), event);
                    break;
            }

        }
    }
}
