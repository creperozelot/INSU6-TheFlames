package INSU.creperozelot.tasks;

import INSU.creperozelot.StaticCache;
import INSU.creperozelot.events.*;
import INSU.creperozelot.main;
import INSU.creperozelot.utils.utils;
import org.bukkit.Bukkit;

public class EventManager {
    public static void runEventManager() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (!StaticCache.eventrunning) {
                    int randomint = utils.random(0, 55);

                    switch (randomint) {
                        case 1:
                            AcidRain.run();
                            break;
                        case 2:
                            FindTheItem.run();
                            break;
                        case 3:
                            FindThePlayer.run();
                            break;
                        case 4:
                            Glueckstreffer.run();
                            break;
                        case 5:
                            HungersNot.run();
                            break;
                        case 6:
                            utils.eventanimation("§2§lINSU-Quiz", "§2§lU-Q", "§2§lSU-Qu", "§2§lNSU-Qui", "§2§lINSU-Quiz", "§a§lINSU-Quiz", "§e§lINSU-Quiz");
                            InsuQuiz.run();
                            break;
                        case 7:
                            RandomEffekt.run();
                            break;
                        case 8:
                            TPUP.run();
                            break;
                        case 9:
                            WeitEntfernt.run();
                            break;
                        case 10:
                            break;
                        case 11:
                            break;
                    }

                }
            }
        }, 20 * 60, 0);
    }
}
