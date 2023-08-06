package co.bitengine.salvage.guis.menus;

import co.bitengine.salvage.Utils;
import co.bitengine.salvage.guis.actions.EditRecipeInputAction;
import co.bitengine.salvage.guis.actions.EditRecipeOutputAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.denizenmc.menus.Menus;
import org.denizenmc.menus.MenusUtils;
import org.denizenmc.menus.components.Element;
import org.denizenmc.menus.components.Menu;
import org.denizenmc.menus.components.actions.BackAction;
import org.denizenmc.menus.components.actions.NextPageAction;
import org.denizenmc.menus.components.actions.PreviousPageAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeEditMenu {
    public void create() {
        Menu menu = Menus.getAPI().createEmptyMenu(Utils.RECIPE_EDIT_MENU,  3)
                .setTitle("Edit Recipe Items")
                .setCollection("Salvage")
                .setRefreshRateTicks(2000);
        menu.setCanOpenDirectly(false);
        menu.setHidden(false);
        setContent(menu);
    }

    private void setContent(Menu menu) {
        List<Integer> inputSlots = new ArrayList<>(Arrays.asList(1, 2, 3, 10, 11, 12, 19, 20, 21));
        List<Integer> outputSlots = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 14, 15, 16, 17, 23, 24, 25, 26));
        menu.getContent().put(0,
                new Element(new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        "&7&l "));
        menu.getContent().put(4,
                new Element(new ItemStack(Material.ARROW),
                        "&bPrevious Page", Arrays.asList("&fCurrent Page: &7(%menus_page%)", "", "&eClick Here"))
                        .addAction(new PreviousPageAction()));
        menu.getContent().put(9,
                new Element(MenusUtils.getHead(Utils.RECIPE_ICON_HEAD),
                        "&e&lExit", Arrays.asList("&7Click Here"))
                        .addAction(new BackAction()));
        menu.getContent().put(13,
                new Element(new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        "&7&l "));
        menu.getContent().put(18,
                new Element(new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                        "&7&l "));
        menu.getContent().put(22,
                new Element(new ItemStack(Material.ARROW),
                        "&bNext Page", Arrays.asList("&fCurrent Page: &7(%menus_page%)", "", "&eClick Here"))
                        .addAction(new NextPageAction()));
        for (int i : inputSlots) {
            menu.getContent().put(i,
                    new Element(new ItemStack(Material.GRAY_STAINED_GLASS_PANE))
                            .addAction(new EditRecipeInputAction()));
        }
        for (int i : outputSlots) {
            menu.getContent().put(i,
                    new Element(new ItemStack(Material.GRAY_STAINED_GLASS_PANE))
                            .addAction(new EditRecipeOutputAction()));
        }
        Menus.getAPI().updateMenu(menu);
    }
}
