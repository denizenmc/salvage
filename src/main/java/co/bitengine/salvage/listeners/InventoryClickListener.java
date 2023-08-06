package co.bitengine.salvage.listeners;

import co.bitengine.salvage.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.denizenmc.menus.Menus;
import org.denizenmc.menus.components.Menu;

public class InventoryClickListener implements Listener {
    @EventHandler (priority = EventPriority.MONITOR)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (player.getOpenInventory().getTopInventory() != null
                && player.getOpenInventory().getTopInventory().getHolder() instanceof Menu
                && Menus.getAPI().getSession(player) != null &&
                Menus.getAPI().getSession(player).getMenu().getName().equals(Utils.SALVAGE_MENU)
            && event.getClickedInventory() != null && event.getClickedInventory().equals(player.getOpenInventory().getBottomInventory())) {
            if (event.getClick().equals(ClickType.SHIFT_LEFT) && event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                boolean added = Menus.getAPI().getSession(player).addInput(new ItemStack(event.getCurrentItem()));
                if (added) event.getCurrentItem().setAmount(0);
                event.setCancelled(true);
            }
        }
    }
}
