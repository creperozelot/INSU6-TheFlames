package INSU.creperozelot;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;


public class StaticCache {
    private StaticCache() {} //Prevent Instantiation
    public static String prefix = main.getInstance().getConfig().getString("main.prefix");
    public static JDA jda;
    public static String host = main.getInstance().getConfig().getString("mysql.host");

    public static String port = main.getInstance().getConfig().getString("mysql.port");

    public static String database = main.getInstance().getConfig().getString("mysql.db");

    public static String username = main.getInstance().getConfig().getString("mysql.username");

    public static String password = main.getInstance().getConfig().getString("mysql.password");

    public static boolean eventrunning = false;
    public static boolean freeze = false;

    public static boolean storyrunning = false;

    public static boolean pickupevent = false;

    public static boolean startconfirmed = false;
    public static int pickupitem = -1;

    public static List<String> muted_players = new ArrayList<String>();

    public static List<String> onlineplayerlist = new ArrayList<String>();

    public static String consoleerr = main.getInstance().getConfig().getString("messages.errconsolerun");

    //Task IDS
    public static int Task_WaitingforHost_id = 0;

    //BOSSBAR



    public static String bossbarmsg = StaticCache.prefix + "§6Infos werden hier angezeigt!";
    public static BossBar bossBar = getServer().createBossBar(bossbarmsg, BarColor.YELLOW, BarStyle.SOLID);

}
