package INSU.creperozelot;

import INSU.creperozelot.commands.CommandSpec;
import INSU.creperozelot.commands.CommandTeamRegister;
import INSU.creperozelot.dc.bot.botlogic;
import INSU.creperozelot.events.BetterWatchOut;
import INSU.creperozelot.events.Glueckstreffer;
import INSU.creperozelot.listener.*;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public final class main extends JavaPlugin {
    private static main instance;

    static Connection connection;

    public static main getInstance() {
        return instance;
    }



    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        this.getLogger().info("--------------------");
        this.getLogger().info("§aAktiviert...");
        this.getLogger().info("§cby §acreperozelot §cund §ePixelMandel");
        this.getLogger().info("--------------------");
        registerCommand();
        registerListener();
        InfoMessage.deathInfo();
        //Bot startup logic
        botlogic.createBot();
        //MYSQL
        MYSQL.connect();
        if (MYSQL.isConnected()) {
            MYSQL.update("CREATE TABLE IF NOT EXISTS INSU(PLAYER varchar(64), DEATH int, ID int, TEAM varchar(64), ISGAMEMASTER varchar(32), STARTED varchar(32));");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("--------------------");
        this.getLogger().info("§cDeaktiviert...");
        this.getLogger().info("");
        this.getLogger().info("--------------------");
        MYSQL.disconnect();
    }

    private void registerCommand() {
        //this.getCommand("heal").setExecutor(new CommandHeal());
        this.getCommand("spec").setExecutor(new CommandSpec());
        this.getCommand("glueckstreffer").setExecutor(new Glueckstreffer());
        this.getCommand("BetterWatchOut").setExecutor(new BetterWatchOut());
    }

    private void registerListener() {
       // getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerTeamLiveListener(), this);
    }
}
