package INSU.creperozelot.listener;

import INSU.creperozelot.InterfaceManager;
import INSU.creperozelot.StaticCache;
import INSU.creperozelot.dc.bot.botlogic;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import INSU.creperozelot.utils.utils;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.geysermc.cumulus.form.ModalForm;
import org.geysermc.floodgate.api.FloodgateApi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PlayerJoinListener implements Listener {

    ScoreboardManager manager = Bukkit.getScoreboardManager();
    @EventHandler
    public void onPLayerJoin(PlayerJoinEvent event) throws SQLException {

        if (!MYSQL.isConnected()) {
            event.getPlayer().kickPlayer("§c§lSystemfehler: \n §r§cDatenbank nicht verbunden!");
            event.setJoinMessage("");
        } else if (StaticCache.storyrunning) {
            event.getPlayer().kickPlayer("§c§lFehler: \n §cINSU Startet gerade, bitte warte kurz und versuche es nach dem Start erneut.");
            event.setJoinMessage("");
        } else if (!MYSQL.PlayerExist(event.getPlayer().getName())) {
            event.getPlayer().kickPlayer("§c§lFehler: \n §r§cDu bist nicht in der Datenbank eingetragen. Dies kann folgende Gründe haben: \n §c1. Du hast dich nicht bei INSU beworben. \n §c2. Du wurdest nicht bei INSU aktzeptiert. \n §c3. Du bist Tot und bist damit AUsgeschieden.");
        } else if (MYSQL.getDeath(event.getPlayer().getName()) != 0 && !MYSQL.isGameMaster(event.getPlayer().getName())) {
            event.getPlayer().kickPlayer("§c§lFehler: \n §r§cDu bist aus INSU ausgeschieden!");
        } else if (!main.getInstance().getConfig().getBoolean("intime") && !MYSQL.isGameMaster(event.getPlayer().getName())) {
            event.getPlayer().kickPlayer("§c§lFehler: \n §r§cDas Projekt läuft zwischen " + main.getInstance().getConfig().getString("main.playtime") + "§c Uhr und " + main.getInstance().getConfig().getString("main.kicktime") + "§c Uhr.");
        } else if (!main.getInstance().getConfig().getBoolean("main.maintenance") || MYSQL.isGameMaster(event.getPlayer().getName())) {
            if (!MYSQL.isStarted(event.getPlayer().getName()) && main.getInstance().getConfig().getBoolean("main.started")) {

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId())) {
                            FloodgateApi.getInstance().sendForm(event.getPlayer().getUniqueId(), InterfaceManager.startedform(event.getPlayer()));
                        }
                    }
                }.runTaskLater(main.getInstance(), 20 * 10);


            } else if (!MYSQL.isStarted(event.getPlayer().getName()) && !main.getInstance().getConfig().getBoolean("main.started")) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getPlayer().teleport(new Location(Bukkit.getWorld(main.getInstance().getConfig().getString("main.map")), main.getInstance().getConfig().getInt("main.lobbyspawncoords.x"), main.getInstance().getConfig().getInt("main.lobbyspawncoords.y"), main.getInstance().getConfig().getInt("main.lobbyspawncoords.z")));
                    }
                }.runTaskLater(main.getInstance(), 20 * 5);
            }

            Player player = event.getPlayer();
            int OnlinePlayers = Bukkit.getOnlinePlayers().size();
            StaticCache.bossBar.addPlayer(player);




            event.setJoinMessage(StaticCache.prefix + "Der Spieler §e" + player.getDisplayName() + "§6 ist dem Projekt Beigetreten.");
            player.sendMessage(StaticCache.prefix + "§cBEACHTE! Alles was du sagst wird geloggt! Wähle deine Worte weise.");
            player.sendMessage(StaticCache.prefix + "Es sind §e" + OnlinePlayers + "§f Spieler §aOnline...");
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 15, 255, false, false, Color.BLACK), false);
            player.sendTitle("§cInformation...", "§cDu bist für 15 Skunden nicht Verwundbar", 0, 80, 20);
            player.sendMessage(StaticCache.prefix + "§aDu bist im Team §6" + MYSQL.getTeambyName(player.getName()));

            if (!main.getInstance().getConfig().getBoolean("main.maintenance")) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Server Beitrit");
                eb.setDescription("Der Spieler **" + player.getName() + "** ist dem INSU Server beigetreten!");
                eb.setColor(java.awt.Color.GREEN);
                botlogic.sendEmbedMessage(eb.build(), "732648259599728661");
                botlogic.sendEmbedMessage(eb.build(), "984755892794843206");
            }

            //players stuff
            if (!StaticCache.onlineplayerlist.contains(player.getName())) {
                StaticCache.onlineplayerlist.add(player.getName());
            }

            if (MYSQL.isGameMaster(player.getName())) {
                player.setDisplayName("§e§lGamemaster §r§8|§r§f " + player.getName());
                player.setCustomName("§e§lGamemaster §r§8|§r§f " + player.getName());
            } else {
                String teamname = MYSQL.getTeambyName(player.getName());
                player.setDisplayName("§f" + teamname + " §8§r|§f " + player.getName());
                player.setCustomName("§f" + teamname + " §8§r|§f " + player.getName());
            }

        } else {
            Player player = event.getPlayer();
                player.kickPlayer("§c§lWartungen: \n §r§cGerade befindet sich INSU in den Wartungen, versuche es später noch einmal.");
        }


    }
}
