package co.bitengine.salvage.guis.menus;

import co.bitengine.salvage.Utils;
import co.bitengine.salvage.guis.actions.SalvageOutputAction;
import co.bitengine.salvage.guis.actions.SalvageSubmitAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.denizenmc.menus.Menus;
import org.denizenmc.menus.components.Element;
import org.denizenmc.menus.components.Menu;
import org.denizenmc.menus.components.actions.ItemInputAction;
import org.denizenmc.menus.components.actions.NextPageAction;
import org.denizenmc.menus.components.actions.PreviousPageAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalvageMenu {
    public void create() {
        Menu menu = Menus.getAPI().createEmptyMenu(Utils.SALVAGE_MENU,  5)
                .setTitle("Salvage")
                .setCollection("Salvage")
                .setRefreshRateTicks(1);
        menu.setCanOpenDirectly(false);
        menu.setHidden(false);
        setContent(menu);
    }

    private void setContent(Menu menu) {
        List<Integer> inputSlots = new ArrayList<>(Arrays.asList(10, 11, 12, 19, 20, 21, 28, 29, 30));
        List<Integer> outputSlots = new ArrayList<>(Arrays.asList(14, 15, 16, 23, 24, 25, 32, 33, 34));
        List<Integer> backgroundSlots = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 18, 26, 27, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44));
        for (int i : inputSlots) {
            menu.getContent().put(i, new Element(Menus.getAPI().getBackgroundItemFromMaterial(Material.GRAY_STAINED_GLASS_PANE))
                    .addAction(new ItemInputAction()));
        }
        for (int i : backgroundSlots) {
            menu.getContent().put(i, new Element(Menus.getAPI().getBackgroundItemFromMaterial(Material.GRAY_STAINED_GLASS_PANE),
                    "&7 "));
        }
        for (int i : outputSlots) {
            menu.getContent().put(i, new Element(Menus.getAPI().getBackgroundItemFromMaterial(Material.GRAY_STAINED_GLASS_PANE))
                    .addAction(new SalvageOutputAction()));
        }
        menu.getContent().put(17,
                new Element(new ItemStack(Material.ARROW),
                        "&bPrevious Page", Arrays.asList("&fCurrent Page: &7(%menus_page%)", "", "&eClick Here"))
                        .addAction(new PreviousPageAction()));
        menu.getContent().put(35,
                new Element(new ItemStack(Material.ARROW),
                        "&bNext Page", Arrays.asList("&fCurrent Page: &7(%menus_page%)", "", "&eClick Here"))
                        .addAction(new NextPageAction()));
        menu.getContent().put(22,
                new Element(new ItemStack(Material.ANVIL),
                        "&e&lSalvage", Arrays.asList("&7Click Here"))
                        .addAction(new SalvageSubmitAction()));
        Menus.getAPI().updateMenu(menu);
    }
}
