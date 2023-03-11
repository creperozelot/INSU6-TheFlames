package INSU.creperozelot.listener;

import INSU.creperozelot.StaticCache;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class onCraftItemListener implements Listener {
    @EventHandler
    public void onCraftItemListener(CraftItemEvent event) {
        ItemStack item = event.getCurrentItem();
        HumanEntity humanEntity = event.getWhoClicked();
        if (humanEntity instanceof Player) {
            Player player = (Player) humanEntity;
            switch (item.getType()) {
                case FISHING_ROD: // Fishing_rod
                case BEETROOT_SOUP: //soup
                case MUSHROOM_SOUP:
                    event.setCancelled(true);
                    player.sendMessage(StaticCache.prefix + "§cDu kannst dieses Item nicht Craften!");
                    break;
            }
        }
    }
}
