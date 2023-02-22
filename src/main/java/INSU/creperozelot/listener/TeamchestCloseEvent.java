package INSU.creperozelot.listener;

import INSU.creperozelot.main;
import INSU.creperozelot.utils.MYSQL;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class TeamchestCloseEvent implements Listener {
    @EventHandler
    public void TeamchestCloseEvent(InventoryCloseEvent event) throws SQLException, IOException {
        if (event.getInventory().getName().equals("Teamchest " + MYSQL.getIDbyName(event.getPlayer().getName()))) {
            File file = new File(main.getInstance().getDataFolder().getAbsolutePath() + "/data/teamchest", "teamchest_" + MYSQL.getTeamIDbyName(event.getPlayer().getName()) + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(file);
            c.set("inventory.content", event.getInventory().getContents());
            c.save(file);
        }
    }
}
