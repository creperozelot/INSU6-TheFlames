package INSU.creperozelot.dc.bot.cmd;

import com.tjplaysnow.discord.object.ProgramCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.List;

public abstract class CommandRegister extends ProgramCommand {

    @Override
    public String getLabel() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Einfacher Ping Command";
    }

    protected boolean run(User user, MessageChannel channel, Guild guild, String label, List<String> args) {
        channel.sendMessage("Pong " + "<" + user.getIdLong() + ">").queue(delete());
        return false;
    }

}
