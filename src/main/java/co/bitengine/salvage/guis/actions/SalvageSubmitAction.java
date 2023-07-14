package co.bitengine.salvage.guis.actions;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.tasks.SubmitTask;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.denizenmc.menus.components.Session;
import org.denizenmc.menus.components.actions.Action;

import java.util.*;

public class SalvageSubmitAction extends Action {
    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public String getName() {
        return "salvage-submit";
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList("&fSalvage submit button", "&7Salvage all input items"));
    }

    @Override
    public String getIconPlayerHeadName() {
        return "MrSnowDK";
    }

    @Override
    public Map<String, String> getDefaultProperties() {
        return new HashMap<>();
    }

    @Override
    public Action copy() {
        return new SalvageSubmitAction();
    }

    @Override
    public boolean isDynamicIcon() {
        return false;
    }

    @Override
    public ItemStack getDynamicIcon(Session session, int i) {
        return null;
    }

    @Override
    public void onBuild(Session session, int i) {

    }

    @Override
    public void onClick(Session session, int i, InventoryClickEvent inventoryClickEvent) {
        Salvage.getInstance().getTaskController().add(new SubmitTask(session.getPlayer()));
    }
}
