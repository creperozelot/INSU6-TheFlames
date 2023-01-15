package INSU.creperozelot;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


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

    public static boolean pickupevent = false;
    public static Material pickupitem = Material.BEDROCK;
}
