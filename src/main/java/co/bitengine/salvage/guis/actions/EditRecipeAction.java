package co.bitengine.salvage.guis.actions;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import co.bitengine.salvage.guis.SalvageContextKeys;
import co.bitengine.salvage.models.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.denizenmc.menus.Menus;
import org.denizenmc.menus.MenusUtils;
import org.denizenmc.menus.components.Session;
import org.denizenmc.menus.components.actions.Action;

import java.util.*;

public class EditRecipeAction extends Action {
    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public String getName() {
        return "salvage-edit-recipe";
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList("&fSalvage edit model"));
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
        return new EditRecipeAction();
    }

    @Override
    public boolean isDynamicIcon() {
        return true;
    }

    @Override
    public ItemStack getDynamicIcon(Session session, int i) {
        String search = session.getContext().getValue("menus-text-input", Menus.getInstance()) instanceof String ?
                (String) session.getContext().getValue("menus-text-input", Menus.getInstance()) : null;
        int paginatedCount = (session.getPage()-1)*session.getMenu().getTotal(this)+i;
        if (paginatedCount <= Salvage.getInstance().getRecipeController().getCache(search).size()) {
            return getRecipeItem(Salvage.getInstance().getRecipeController().getCache(search).get(paginatedCount-1));
        }
        return null;
    }

    @Override
    public void onBuild(Session session, int i) {
        if (i == 1) {
            session.getContext().remove(SalvageContextKeys.MODIFIABLE, Salvage.getInstance());
        }
    }

    @Override
    public void onClick(Session session, int i, InventoryClickEvent inventoryClickEvent) {

    }

    private ItemStack getRecipeItem(Recipe recipe) {
        ItemStack item = MenusUtils.getHead(Utils.RECIPE_ICON_HEAD);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.YELLOW + recipe.getName());

        }
        return item;
    }
}
