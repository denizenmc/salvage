package co.bitengine.salvage.guis.actions;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import co.bitengine.salvage.guis.SalvageContextKeys;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.denizenmc.menus.components.Session;
import org.denizenmc.menus.components.actions.Action;

import java.util.*;

public class RemoveRecipeFromContextAction extends Action {
    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public String getName() {
        return "salvage-remove-recipe-from-context";
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList("&fSalvage remove recipe model from context"));
    }

    @Override
    public String getIconPlayerHeadName() {
        return Utils.RECIPE_ICON_HEAD;
    }

    @Override
    public Map<String, String> getDefaultProperties() {
        return new HashMap<>();
    }

    @Override
    public Action copy() {
        return new RemoveRecipeFromContextAction();
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
        inventoryClickEvent.setCancelled(true);
        session.getContext().remove(SalvageContextKeys.MODIFIABLE, Salvage.getInstance());
    }
}
