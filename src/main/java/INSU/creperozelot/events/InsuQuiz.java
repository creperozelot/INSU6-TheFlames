package INSU.creperozelot.events;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class InsuQuiz implements Listener {

    static boolean isQuiz = false;

    static int questionnum = 1;

    static String question;

    static String answer;


    public static void run(){

        StaticCache.eventrunning = true;

        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                StaticCache.eventrunning = false;
            }
        }, 20 * 120);


        StaticCache.eventrunning = true;

        isQuiz = true;

        sendQuestion(getQuestion(1));

    }

    public void end(){

        StaticCache.eventrunning = false;

        isQuiz = false;

    }

    public static String getQuestion(int questionNumber){

        List<String> quests;

        quests = main.getInstance().getConfig().getStringList("questions");

        return quests.get(utils.random(0, quests.size()));



    }

    //new TextComponent("asdasd", 34);

    public static void sendQuestion(String questions){

        question = questions.split(";")[0];
        answer = questions.split(";")[1].toString();

        main.getInstance().getServer().broadcastMessage(StaticCache.prefix + "§1Frage: §7" + question);

    }

    @EventHandler
    public void onChat(PlayerChatEvent event){

        String playeranswer = event.getMessage();
        Player player = event.getPlayer();



        if (isQuiz){

            if (playeranswer.equalsIgnoreCase(answer)){

                questionnum++;

                player.sendMessage(StaticCache.prefix + "§aDas ist die richte Antwort!");

                main.getInstance().getServer().broadcastMessage(StaticCache.prefix + "§2Der Spieler §6" + player.getName() + " §2hat die richtige Antwort: §7" + answer + "§2!");

                //irgendwas mit belohnung oder so

                end();
            } else {

                player.sendMessage(StaticCache.prefix + "§cDas ist leider nicht die richtige Antwort! Versuche es nochmal!");

            }

            event.setCancelled(true);

        }

    }
}
