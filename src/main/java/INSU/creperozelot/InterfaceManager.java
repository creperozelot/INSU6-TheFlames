package INSU.creperozelot;

import INSU.creperozelot.events.*;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.geysermc.cumulus.form.CustomForm;
import org.geysermc.cumulus.form.Form;
import org.geysermc.cumulus.form.ModalForm;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.floodgate.api.FloodgateApi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class InterfaceManager {
    public static SimpleForm eventsui(Player player) {
        return SimpleForm.builder().title("§9§lEvents").content("Wähle ein Event aus das du Starten möchtest.").button("Acid-Rain").button("Find-the-Item").button("Find-the-Player").button("Glückstreffer").button("Hungersnot").button("Insu-Quiz").button("Kaktus").button("Lootdrop").button("RandomEffekt").button("TPUP").button("Weit-Entfernt").validResultHandler(result -> {
            if (StaticCache.eventrunning) {
                FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cEs ist bereits ein Event am Laufen!").button1("Zurück").validResultHandler(result2 -> FloodgateApi.getInstance().sendForm(player.getUniqueId(), eventsui(player))).build());
            } else {

                switch (result.clickedButton().text()) {

                    case "Acid-Rain":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                AcidRain.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§a§lAcid Rain", "§a§ld R", "§a§lid Ra", "§a§lcid Rai", "§a§lAcid Rain", "§a§lAcid Rain", "§a§lAcid Rain"));
                        break;
                    case "Find-the-Item":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                FindTheItem.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§1§lFind the Item", "§1§l the ", "§1§ld the I", "§1§lnd the It", "§1§lind the Ite", "§1§lFind the Item", "§1§lFind the Item"));
                        break;
                    case "Find-the-Player":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                FindThePlayer.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§2§lFind the Player", "§2§lthe", "§2§l the ", "§2§ld the P", "§2§lnd the Pl", "§2§lind the Play", "§2§lFind the Playe"));
                        break;
                    case "Glückstreffer":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Glueckstreffer.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§a§llückstreffe", "§a§lt", "§a§lstr", "§a§lkstre", "§a§lckstref", "§a§lückstreff", "§a§lGlückstreffer"));
                        break;
                    case "Hungersnot":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                HungersNot.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§b§lHungernot", "§b§le", "§b§lger", "§b§lngern", "§b§lungerno", "§b§lHungernot", "§b§lHungernot"));
                        break;
                    case "Insu-Quiz":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                InsuQuiz.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§3§lInsu-Quiz", "§3§l-", "§3§lu-Q", "§3§lsu-Qu", "§3§lnsu-Qui", "§3§lInsu-Quiz", "§3§lInsu-Quiz"));
                        break;
                    case "RandomEffekt":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                RandomEffekt.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§4§lRandomeffekt", "§4§lme", "§4§lomef", "§4§ldomeff", "§4§lndomeffe", "§4§landomeffek", "§4§lRandomeffekt"));
                        break;
                    case "Weit-Entfernt":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                WeitEntfernt.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§5§lWeit Entfernt", "§5§ln", "§5§lEnt", "§5§l Entf", "§5§lt Entfe", "§5§lit Entfer", "§5§leit Entfern"));
                        break;
                    case "Kaktus":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Kaktuse.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§6§lKaktus", "§6§lkt", "§6§laktu", "§6§lKaktus", "§6§lKaktus", "§6§lKaktus", "§6§lKaktus"));
                        break;
                    case "Lootdrop":
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
                    case "TPUP":
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                TPUP.run();
                            }
                        }.runTaskLater(main.getInstance(), utils.eventanimation("§c§lTPUP", "§cU§l", "§c§lPU", "§c§lTPUP", "§c§lTPUP", "§c§lTPUP", "§c§lTPUP"));
                        break;
                    default:
                        break;
                }
            }
        }).build();
    }

    public static CustomForm unmuteui(Player player) {
        return CustomForm.builder().title("§a§lUnmute-Player").input("Spieler").validResultHandler(result -> {
            if (Bukkit.getPlayer(result.asInput(0)) != null) {
                if (StaticCache.muted_players.contains(result.asInput(0))) {
                    StaticCache.muted_players.remove(result.asInput(0));

                    Bukkit.getPlayer(result.asInput(0)).sendMessage(StaticCache.prefix + "§cDu wurdest entmuted!");

                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§a§lErfolg").content("§aDer Spieler " + result.asInput(0) + "wurde entmuted!").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                        FloodgateApi.getInstance().sendForm(player.getUniqueId(), muteui(player));
                    }));
                } else {
                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cDer Spieler " + result.asInput(0) + "ist nicht gemuted!").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                        FloodgateApi.getInstance().sendForm(player.getUniqueId(), muteui(player));
                    }));
                }
            } else {
                FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cDer Spieler " + result.asInput(0) + "ist nicht Online!").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), muteui(player));
                }));
            }
        }).build();
    }

    public static CustomForm muteui(Player player) {
        return CustomForm.builder().title("§c§lMute-Player").input("Spieler", "creperozelot").validResultHandler(result -> {
            System.out.println(result.asInput(0));
            if (Bukkit.getPlayer(result.asInput(0)) != null) {
                if (!StaticCache.muted_players.contains(result.asInput(0))) {
                    StaticCache.muted_players.add(result.asInput(0));

                    Bukkit.getPlayer(result.asInput(0)).sendMessage(StaticCache.prefix + "§cDu wurdest gemuted!");

                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§a§lErfolg").content("§aDer Spieler " + result.asInput(0) + " wurde gemuted!").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                        FloodgateApi.getInstance().sendForm(player.getUniqueId(), muteui(player));
                    }));
                } else {
                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cDer Spieler " + result.asInput(0) + " ist bereits gemuted!").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                        FloodgateApi.getInstance().sendForm(player.getUniqueId(), muteui(player));
                    }));
                }
            } else {
                FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cDer Spieler " + result.asInput(0) + " ist nicht Online!").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), muteui(player));
                }));
            }
        }).build();
    }

    public static CustomForm strikeui(Player player) {
        return CustomForm.builder().title("§4§lStirke-Player").input("Spieler", "creperozelot").validResultHandler(result -> {
            if (Bukkit.getPlayer(result.asInput(0)) != null || MYSQL.PlayerExist(result.asInput(0))) {
                Player strikedplayer = Bukkit.getPlayer(result.asInput(0));
                switch (MYSQL.getStrikes(result.asInput(0))) {
                    case 0:
                        int x = (int) strikedplayer.getLocation().getX();
                        int y = (int) strikedplayer.getLocation().getY();
                        int z = (int) strikedplayer.getLocation().getZ();

                        try {
                            MYSQL.addStrike(strikedplayer.getName());
                        } catch (SQLException e) {

                            FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cEs ist ein Fehler mit der Datenbank aufgetreten: \n\n" + e.getMessage()).button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                                FloodgateApi.getInstance().sendForm(player.getUniqueId(), strikeui(player));
                            }));

                            throw new RuntimeException(e);
                        }

                        FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§a§lErfolg").content("§a Der Spieler " + result.asInput(0) + " §awurde Gestriked.").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                            FloodgateApi.getInstance().sendForm(player.getUniqueId(), strikeui(player));
                        }));

                        Bukkit.broadcastMessage(StaticCache.prefix + "§6§lDer Spieler §e§l" + strikedplayer.getName() + " §6§l hat einen Strike kassiert. §aDie Koordinaten des Spielers sind X: " + x + " §aY: " + y + " §aZ: " + z);
                        break;
                    case 1:
                        try {
                            MYSQL.addStrike(strikedplayer.getName());
                        } catch (SQLException e) {

                            FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cEs ist ein Fehler mit der Datenbank aufgetreten: \n\n" + e.getMessage()).button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                                FloodgateApi.getInstance().sendForm(player.getUniqueId(), strikeui(player));
                            }));

                            throw new RuntimeException(e);
                        }


                        FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§a§lErfolg").content("§a Der Spieler " + result.asInput(0) + " §ahat nun seinen 2 Strike kassiert. Sein Inventar und seine Teamchest wurde geleert!").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                            FloodgateApi.getInstance().sendForm(player.getUniqueId(), strikeui(player));
                        }));


                        strikedplayer.sendMessage(StaticCache.prefix + "§cDu hast deinen 2 Strike kassiert, dein Inventar und eure Teamchest wurde geleert!");
                        strikedplayer.getInventory().clear();
                        try {
                            utils.deleteTeamChest(MYSQL.getTeamIDbyName(strikedplayer.getName()));
                        } catch (SQLException e) {

                            FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cEs ist ein Fehler mit der Teamchest aufgetreten: \n\n" + e.getMessage()).button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                                FloodgateApi.getInstance().sendForm(player.getUniqueId(), strikeui(player));
                            }));

                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:

                        FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§a§lErfolg").content("§a Der Spieler " + result.asInput(0) + " §ahat seinen 3 Strike kassiert und ist nun Ausgeschieden.").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                            FloodgateApi.getInstance().sendForm(player.getUniqueId(), strikeui(player));
                        }));


                        try {
                            MYSQL.delete(strikedplayer.getName());
                        } catch (SQLException e) {

                            FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cEs ist ein Fehler mit der Datenbank aufgetreten: \n\n" + e.getMessage()).button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                                FloodgateApi.getInstance().sendForm(player.getUniqueId(), strikeui(player));
                            }));


                            throw new RuntimeException(e);
                        }
                        strikedplayer.kickPlayer("§c§lFehler:\n§cDu wurdest wegen zuvielen Regelverstößen vom Projekt ausgeschloßen!");

                        break;
                }
            } else {
                FloodgateApi.getInstance().sendForm(player.getUniqueId(), ModalForm.builder().title("§c§lFehler").content("§cDer Spieler " + result.asInput(0) + " §cist nicht Online oder wurde nicht in der Datenbank gefunden.").button1("Zurück").button2("Schließen").validResultHandler(result2 -> {
                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), strikeui(player));
                }));
            }
        }).build();
    }

    public static ModalForm startedform(Player player) {
        return ModalForm.builder()
                .title("§a§lNachstart")
                .content("Willkommen bei INSU §a§lNachstarter§r. Hier findest du nun einige Optionen die deinen Start vereinfachen. Bitte wähle einer der unteren Optionen... \n\n§cDu kannst dich nur zu deinem Teammate Teleportieren wenn dein Teammate Online ist...")
                .button1("An zufälligen ort Teleportieren")
                .button2("Zu deinem Teammate Teleportieren")
                .validResultHandler(result -> {

                    switch (result.clickedButtonText()) {
                        case "An zufälligen ort Teleportieren":
                            player.teleport(utils.generateStartupLocation());
                            try {
                                MYSQL.setStarted(player.getName(), "true");
                            } catch (SQLException e) {
                                player.kickPlayer("§c§lFehler:\n Bitte an Admin senden: DB_SETSTART-ERR: " + e.getMessage());
                                throw new RuntimeException(e);
                            }
                            break;

                        case "Zu deinem Teammate Teleportieren":

                            try {
                                List<String> teammate = new ArrayList<>(MYSQL.getTeammateNamesbyPlayer(player));
                                teammate.remove(player.getName());

                                if (teammate.size() != 1) {
                                    player.kickPlayer("§c§lFehler:\n §cDiese Option steht für dich nicht zur verfügung!");
                                    break;
                                }


                                Player mate = Bukkit.getPlayerExact(teammate.toString().replace("[", "").replace("]", ""));

                                if (mate == null) {
                                    player.sendMessage("§c§lFehler: \n§cTeammate wurde nicht gefunden! Err: TP_ERR");
                                }

                                player.teleport(mate.getLocation());
                                player.sendMessage(StaticCache.prefix + "Du wurdest zu deinem Mate Teleportiert.");
                                MYSQL.setStarted(player.getName(), "true");
                                break;
                            } catch (SQLException e) {
                                player.kickPlayer("§c§lFehler:\n Bitte an Admin senden: DB_ALLG-ERR: " + e.getMessage());
                                throw new RuntimeException(e);
                            }

                    }
                }).closedOrInvalidResultHandler(result -> {
                    FloodgateApi.getInstance().sendForm(player.getUniqueId(), startedform(player));
                }).build();
    }
}
