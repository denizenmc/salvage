package co.bitengine.salvage.guis.actions;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.models.SalvagePlayerData;
import co.bitengine.salvage.tasks.InputTask;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.denizenmc.menus.components.Session;
import org.denizenmc.menus.components.actions.Action;

import java.util.*;

public class SalvageInputAction extends Action {
    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public String getName() {
        return "salvage-input";
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList("&fSalvage input slot"));
    }

    @Override
    public String getIconPlayerHeadName() {
        return "Davenator911";
    }

    @Override
    public Map<String, String> getDefaultProperties() {
        return new HashMap<>();
    }

    @Override
    public Action copy() {
        return new SalvageInputAction();
    }

    @Override
    public boolean isDynamicIcon() {
        return true;
    }

    @Override
    public ItemStack getDynamicIcon(Session session, int i) {
        SalvagePlayerData data = Salvage.getInstance().getPlayerController().get(session.getPlayer());
        if (!data.getInput().isEmpty() && i <= data.getInput().size()) {
            return data.getInput().get(i-1);
        }
        return null;
    }

    @Override
    public void onBuild(Session session, int i) {

    }

    @Override
    public void onClick(Session session, int i, InventoryClickEvent inventoryClickEvent) {
        Salvage.getInstance().getTaskController().add(new InputTask(session.getPlayer(), i-1, inventoryClickEvent.getCursor() == null ? null : new ItemStack(inventoryClickEvent.getCursor())));
    }
}
