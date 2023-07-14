package co.bitengine.salvage.guis.actions;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import co.bitengine.salvage.guis.SalvageContextKeys;
import co.bitengine.salvage.models.Recipe;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.denizenmc.menus.Menus;
import org.denizenmc.menus.components.Session;
import org.denizenmc.menus.components.actions.Action;

import java.util.*;

public class AddRecipeToContextAction extends Action {
    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public String getName() {
        return "salvage-add-recipe-to-context";
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList("&fSalvage add recipe model to context"));
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
        return new AddRecipeToContextAction();
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
        String search = session.getContext().getValue("menus-text-input", Menus.getInstance()) instanceof String ?
                (String) session.getContext().getValue("menus-text-input", Menus.getInstance()) : null;
        int paginatedCount = (session.getPage()-1)*session.getMenu().getTotal(this)+i;
        if (paginatedCount <= Salvage.getInstance().getRecipeController().getCache(search).size()) {
            session.getContext().setValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance(), Salvage.getInstance().getRecipeController().getCache(search).get(paginatedCount-1));
        }
    }
}
