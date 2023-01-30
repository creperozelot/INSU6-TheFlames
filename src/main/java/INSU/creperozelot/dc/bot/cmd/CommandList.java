package INSU.creperozelot.dc.bot.cmd;

import INSU.creperozelot.dc.bot.botlogic;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.FileUpload;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class CommandList {
    public CommandList(SlashCommandInteractionEvent event) throws SQLException, IOException {

            if (utils.dchasrole(event.getMember(), "998618192257765376")) {
                String path = MYSQL.export();
                event.replyEmbeds(botlogic.sendEmbedMessage("Datenbank Export | Exportiere Einträge", "Diese Tabele enthält alle Daten von INSU, bitte fahre nur fort wenn du weist was du tust!", "Programmiert von creperozelot und PixelMandel", Color.ORANGE, true).build()).addFiles(FileUpload.fromData(new File(path))).queue();
            } else {
                event.reply(utils.permsmsgdc()).setEphemeral(true).queue();
            }
    }
}
