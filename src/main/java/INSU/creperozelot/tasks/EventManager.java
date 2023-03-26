package INSU.creperozelot.tasks;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.events.*;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class EventManager {
    public static void runEventManager() {

        if (main.getInstance().getConfig().getBoolean("main.autorunevents")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!StaticCache.eventrunning) {
                        int randomint = utils.random(0, 45);

                        switch (randomint) {
                            case 1:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        AcidRain.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§a§lAcid Rain", "§a§ld R", "§a§lid Ra", "§a§lcid Rai", "§a§lAcid Rain", "§a§lAcid Rain", "§a§lAcid Rain"));
                                break;
                            case 2:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        FindTheItem.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§1§lFind the Item", "§1§l the ", "§1§ld the I", "§1§lnd the It", "§1§lind the Ite", "§1§lFind the Item", "§1§lFind the Item"));
                                break;
                            case 3:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        FindThePlayer.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§2§lFind the Player", "§2§lthe", "§2§l the ", "§2§ld the P", "§2§lnd the Pl", "§2§lind the Play", "§2§lFind the Playe"));
                                break;
                            case 4:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        Glueckstreffer.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§a§llückstreffe", "§a§lt", "§a§lstr", "§a§lkstre", "§a§lckstref", "§a§lückstreff", "§a§lGlückstreffer"));
                                break;
                            case 5:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        HungersNot.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§b§lHungernot", "§b§le", "§b§lger", "§b§lngern", "§b§lungerno", "§b§lHungernot", "§b§lHungernot"));
                                break;
                            case 6:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        InsuQuiz.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§3§lInsu-Quiz", "§3§l-", "§3§lu-Q", "§3§lsu-Qu", "§3§lnsu-Qui", "§3§lInsu-Quiz", "§3§lInsu-Quiz"));
                                break;
                            case 7:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        RandomEffekt.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§4§lRandomeffekt", "§4§lme", "§4§lomef", "§4§ldomeff", "§4§lndomeffe", "§4§landomeffek", "§4§lRandomeffekt"));
                                break;
                            case 8:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        WeitEntfernt.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§5§lWeit Entfernt", "§5§ln", "§5§lEnt", "§5§l Entf", "§5§lt Entfe", "§5§lit Entfer", "§5§leit Entfern"));
                                break;
                            case 9:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        Kaktuse.run();
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§6§lKaktus", "§6§lkt", "§6§laktu", "§6§lKaktus", "§6§lKaktus", "§6§lKaktus", "§6§lKaktus"));
                                break;
                            case 10:
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        if (!Lootdrop.run()) {
                                            main.getInstance().getLogger().log(Level.WARNING, "[EVENT ERROR] Es ist ein Fehler mit dem Event aufgetreten! Die Chest Konnte nich Gespawnt werden...");
                                            StaticCache.eventrunning = false;
                                            StaticCache.bossBar.setTitle(StaticCache.bossbarmsg);
                                            Bukkit.getServer().broadcastMessage(StaticCache.prefix + "§c Event Abgebrochen... Lootdrop konnte nicht Gespawnt oder Implementiert werden. Fahre mit INSU fort.");
                                        }
                                    }
                                }.runTaskLater(main.getInstance(), utils.eventanimation("§9§lLootdrop", "§9§ltd", "§9§lotdr", "§9§lootdro", "§9§lLootdrop", "§9§lLootdrop", "§9§lLootdrop"));
                                break;
                            default:
                                break;
                        }

                    }
                }
            }.runTaskTimer(main.getInstance(), 20L * 60L * 5L, 20L * 60L * 5L);
        }
    }
}
