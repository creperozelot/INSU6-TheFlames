package INSU.creperozelot.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickupListener implements Listener {
    @EventHandler
    public void ItemPickupListener(PlayerPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();

        switch (item.getTypeId()) {
            case 332: //Schneebälle
                event.setCancelled(true);
                break;
        }


    }
}
