package INSU.creperozelot;

import INSU.creperozelot.commands.*;
import INSU.creperozelot.dc.bot.botlogic;
import INSU.creperozelot.events.FindTheItem;
import INSU.creperozelot.events.InsuQuiz;
import INSU.creperozelot.listener.*;
import INSU.creperozelot.tasks.EventManager;
import INSU.creperozelot.tasks.LiveDisplayTask;
import INSU.creperozelot.utils.MYSQL;
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
        if  (main.getInstance().getConfig().getBoolean("main.started")) EventManager.runEventManager();
        if (main.getInstance().getConfig().getBoolean("main.started")) LiveDisplayTask.run();
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
        this.getCommand("setairplanestart").setExecutor(new CommandSetStoryAirplaneStart());
        this.getCommand("reloadconfig").setExecutor(new CommandConfigReload());
        this.getCommand("startevent").setExecutor(new CommandStartEvent());
        this.getCommand("dev_seteventvar").setExecutor(new CommandStopEventDef());
    }

    private void registerListener() {
       // getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new InsuQuiz(), this);
        getServer().getPluginManager().registerEvents(new FindTheItem(), this);
    }
}
