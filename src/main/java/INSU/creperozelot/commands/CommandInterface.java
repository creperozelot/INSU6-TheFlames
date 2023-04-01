package INSU.creperozelot.commands;

import INSU.creperozelot.InterfaceManager;
import INSU.creperozelot.StaticCache;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.floodgate.api.FloodgateApi;

public class CommandInterface implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {

                FloodgateApi.getInstance().sendForm(player.getUniqueId(),
                        SimpleForm.builder()
                                .title("§6§lInterface")
                                .content("Administrations Menu für INSU")
                                .button("§9§lEvents")
                                .button("§c§lMute-Player")
                                .button("§a§lUnmute-Player")
                                .button("§4§lStrike-Player")
                                .validResultHandler( result ->

                                        {
                                            switch (result.clickedButton().text()) {
                                                case "§9§lEvents":
                                                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), InterfaceManager.eventsui(player));
                                                    break;
                                                case "§c§lMute-Player":
                                                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), InterfaceManager.muteui(player));
                                                    break;
                                                case "§a§lUnmute-Player":
                                                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), InterfaceManager.unmuteui(player));
                                                    break;
                                                case "§4§lStrike-Player":
                                                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), InterfaceManager.strikeui(player));
                                                    break;
                                            }

                                        }






                                )
                                .build()
                        );

            } else {
                player.sendMessage(StaticCache.prefix + "§cDu musst ein Bedrock Spieler sein um diesen Befehl zu benutzen!");
            }
        } else {
            sender.sendMessage(StaticCache.prefix + StaticCache.consoleerr);
        }
        return true;
    }
}
