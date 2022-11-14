package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandSpec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE || player.getGameMode() == GameMode.CREATIVE) {
                player.sendMessage(StaticCache.prefix + "§aDu bist nun in §bSpectator§a.");
                player.setGameMode(GameMode.SPECTATOR);
            } else {
                player.sendMessage(StaticCache.prefix + "§aDu bist nun §cNicht Mehr§a in §bSpectator§a.");
                player.setGameMode(GameMode.CREATIVE);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 255, false, false, Color.BLACK), false);
            }
        } else {
            sender.sendMessage(StaticCache.prefix + "§cDu kannst diesen Befehl nur als Spieler ausführen.");
        }
        return true;
    }
}
