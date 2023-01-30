package INSU.creperozelot.commands;

        import INSU.creperozelot.StaticCache;
        import org.bukkit.Bukkit;
        import org.bukkit.command.Command;
        import org.bukkit.command.CommandExecutor;
        import org.bukkit.command.CommandSender;

public class CommandUnmute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(StaticCache.prefix + "§Usage: /unmute <player>");
        } else {
            if (!StaticCache.muted_players.contains(args[0])) {
                sender.sendMessage(StaticCache.prefix + "§Usage: Der Spieler " + args[0] + " §cwurde nicht gefunden!");
            } else {
                sender.sendMessage(StaticCache.prefix + "§aDu hast den Spieler §l " + args[0] + " §r§awurde geunmuted.");
                StaticCache.muted_players.remove(args[0]);
                Bukkit.getPlayer(args[0]).sendMessage(StaticCache.prefix + "§aDu wurdest unmuted!");
            }
        }
        return true;
    }
}
