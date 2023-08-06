package co.bitengine.salvage.guis.menus;

import co.bitengine.salvage.Utils;
import co.bitengine.salvage.guis.actions.DeleteRecipeAction;
import org.bukkit.Material;
import org.denizenmc.menus.Menus;
import org.denizenmc.menus.MenusConfiguration;
import org.denizenmc.menus.MenusUtils;
import org.denizenmc.menus.components.Element;
import org.denizenmc.menus.components.Menu;
import org.denizenmc.menus.components.actions.BackAction;

import java.util.Arrays;

public class RecipeConfirmDeleteMenu {
    public void create() {
        Menu menu = Menus.getAPI().createMenuWithBackground(Utils.RECIPE_CONFIRM_DELETE_MENU, Menus.getAPI().getBackgroundItemFromMaterial(Material.GRAY_STAINED_GLASS_PANE), 3)
                .setTitle("Delete Recipe")
                .setCollection("Salvage")
                .setRefreshRateTicks(4000);
        menu.setCanOpenDirectly(false);
        menu.setHidden(false);
        setContent(menu);
    }

    private void setContent(Menu menu) {
        menu.getContent().put(12,
                new Element(MenusUtils.getHead(MenusConfiguration.CLICK_TOGGLED_OFF_HEAD),
                        "&c&lCancel", Arrays.asList("&7Cancel Delete", "", "&eClick Here"))
                        .addAction(new BackAction()));
        menu.getContent().put(14,
                new Element(MenusUtils.getHead(MenusConfiguration.CLICK_TOGGLED_ON_HEAD),
                        "&a&lDelete", Arrays.asList("&7Delete Menu","", "&eClick Here"))
                        .addAction(new DeleteRecipeAction())
                        .addAction(new BackAction()));
        Menus.getAPI().updateMenu(menu);
    }
}
