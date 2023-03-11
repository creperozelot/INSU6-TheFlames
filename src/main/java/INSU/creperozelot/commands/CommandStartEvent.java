package INSU.creperozelot.commands;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.events.*;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandStartEvent implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!StaticCache.eventrunning) {

                if (args.length != 1) {
                    player.sendMessage(StaticCache.prefix + "§cUsage: /startevent <acidrain | findtheitem | findtheplayer | glückstreffer | hungersnot | insuquiz | randomeffekt | tpup | weitentfernt>");
                }

                switch(args[0]) {
                    case "acidrain":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                AcidRain.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§a§lAcid Rain", "§a§ld R", "§a§lid Ra", "§a§lcid Rai", "§a§lAcid Rain", "§a§lAcid Rain", "§a§lAcid Rain"));
                        break;
                    case "findtheitem":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                FindTheItem.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§1§lFind the Item", "§1§l the ", "§1§ld the I", "§1§lnd the It", "§1§lind the Ite", "§1§lFind the Item", "§1§lFind the Item"));
                        break;
                    case "findtheplayer":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                FindThePlayer.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§2§lFind the Player", "§2§lthe", "§2§l the ", "§2§ld the P", "§2§lnd the Pl", "§2§lind the Play", "§2§lFind the Playe"));
                        break;
                    case "glückstreffer":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Glueckstreffer.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§a§llückstreffe", "§a§lt", "§a§lstr", "§a§lkstre", "§a§lckstref", "§a§lückstreff", "§a§lGlückstreffer"));
                        break;
                    case "hungersnot":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                HungersNot.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§b§lHungernot", "§b§le", "§b§lger", "§b§lngern", "§b§lungerno", "§b§lHungernot", "§b§lHungernot"));
                        break;
                    case "insuquiz":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                InsuQuiz.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§3§lInsu-Quiz", "§3§l-", "§3§lu-Q", "§3§lsu-Qu", "§3§lnsu-Qui", "§3§lInsu-Quiz", "§3§lInsu-Quiz"));
                        break;
                    case "randomeffekt":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                RandomEffekt.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§4§lRandomeffekt", "§4§lme", "§4§lomef", "§4§ldomeff", "§4§lndomeffe", "§4§landomeffek", "§4§lRandomeffekt"));
                        break;
                    case "tpup":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                TPUP.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§c§lTPUP", "§cU§l", "§c§lPU", "§c§lTPUP", "§c§lTPUP", "§c§lTPUP", "§c§lTPUP"));
                        break;
                    case "weitentfernt":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                WeitEntfernt.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§5§lWeit Entfernt", "§5§ln", "§5§lEnt", "§5§l Entf", "§5§lt Entfe", "§5§lit Entfer", "§5§leit Entfern"));
                        break;
                    case "kaktus":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Kaktuse.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§6§lKaktus", "§6§lkt", "§6§laktu", "§6§lKaktus", "§6§lKaktus", "§6§lKaktus", "§6§lKaktus"));
                        break;
                    case "lootdrop":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (!Lootdrop.run()) {
                                 player.sendMessage(StaticCache.prefix + "§cEs ist ein Fehler mit dem Event aufgetreten! Die Chest Konnte nich Gespawnt werden...");
                                }
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§9§lLootdrop", "§9§ltd", "§9§lotdr", "§9§lootdro", "§9§lLootdrop", "§9§lLootdrop", "§9§lLootdrop"));
                        break;
                    default:
                        player.sendMessage(StaticCache.prefix + "§cUsage: /startevent <acidrain | findtheitem | findtheplayer | glückstreffer | hungersnot | insuquiz | randomeffekt | tpup | weitentfernt>");
                        break;
                }
            } else {
                player.sendMessage(StaticCache.prefix + "§cEs ist bereits ein event Gestartet!");
            }


        } else {
            sender.sendMessage("§cDu kannst diesen Befehl nur als Spieler ausführen!");
        }

        return true;
    }
}
