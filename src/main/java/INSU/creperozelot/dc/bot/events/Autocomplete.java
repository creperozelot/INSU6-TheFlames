package INSU.creperozelot.dc.bot.events;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Autocomplete extends ListenerAdapter {

    private String[] apply_reasons = new String[]{"Deine Teambalance ist leider zu unausgeglichen. Bitte ändern!", "Teamname enthält wörter / zeichen oder andere objekte die nicht benutzt werden dürfen!", "Du hast dich Bereits beworben.", "Andere grund (Bitte angeben)"};
    private String[] apply_apply = new String[]{"accept", "decline"};

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        switch (event.getName()) {
            case "apply":
                if (event.getFocusedOption().getName().equals("reason")) {
                    List<Command.Choice> options = Stream.of(apply_reasons)
                            .filter(word -> word.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                            .map(word -> new Command.Choice(word, word)) // map the words to choices
                            .collect(Collectors.toList());
                    event.replyChoices(options).queue();
                }

                if (event.getFocusedOption().getName().equals("apply-type")) {
                    List<Command.Choice> options = Stream.of(apply_apply)
                            .filter(word -> word.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                            .map(word -> new Command.Choice(word, word)) // map the words to choices
                            .collect(Collectors.toList());
                    event.replyChoices(options).queue();
                }
                break;
        }
    }
}
