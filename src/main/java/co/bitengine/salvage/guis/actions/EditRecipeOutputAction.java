package co.bitengine.salvage.guis.actions;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import co.bitengine.salvage.guis.SalvageContextKeys;
import co.bitengine.salvage.models.IModifiable;
import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.Recipe;
import co.bitengine.salvage.models.SalvageItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.denizenmc.menus.Menus;
import org.denizenmc.menus.anvilgui.AnvilGUI;
import org.denizenmc.menus.components.Session;
import org.denizenmc.menus.components.actions.Action;
import org.denizenmc.menus.services.DelayedMenuTask;

import java.util.*;

public class EditRecipeOutputAction extends Action {
    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public String getName() {
        return "salvage-edit-recipe-output";
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList("&fSalvage edit recipe output"));
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
        return new EditRecipeOutputAction();
    }

    @Override
    public boolean isDynamicIcon() {
        return true;
    }

    @Override
    public ItemStack getDynamicIcon(Session session, int i) {
        if (!(session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance()) instanceof Recipe)) return null;
        Recipe recipe = (Recipe) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance());
        int paginatedCount = (session.getPage()-1)*session.getMenu().getTotal(this)+i;
        if (paginatedCount <= recipe.getOutput().size()) {
            return getOutputItem(recipe.getOutput().get(paginatedCount-1));
        } else {
            return getEmptyItem();
        }
    }

    @Override
    public void onBuild(Session session, int i) {

    }

    @Override
    public void onClick(Session session, int i, InventoryClickEvent inventoryClickEvent) {
        if (!(session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance()) instanceof Recipe)) return;
        Recipe recipe = (Recipe) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance());
        if (inventoryClickEvent.getCursor() != null && inventoryClickEvent.getCursor().getType() != Material.AIR) {
            ItemStack toAdd = new ItemStack(inventoryClickEvent.getCursor());
            recipe.getOutput().add(new Loot(toAdd));
            Salvage.getInstance().getRecipeController().save(recipe);
            inventoryClickEvent.setCancelled(true);
            session.refresh();
        } else {
            inventoryClickEvent.setCancelled(true);
            int paginatedCount = (session.getPage()-1)*session.getMenu().getTotal(this)+i;
            if (paginatedCount <= recipe.getOutput().size()) {
                switch (inventoryClickEvent.getClick()) {
                    case LEFT:
                        session.pause();
                        new AnvilGUI.Builder()
                                .onClose(player -> {
                                    Menus.getInstance().getTaskService().add(new DelayedMenuTask(1, null, player.getPlayer()));
                                })
                                .onClick((slot, stateSnapshot) -> {
                                    if (slot != AnvilGUI.Slot.OUTPUT) {
                                        return Collections.emptyList();
                                    }
                                    if (session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance()) instanceof IModifiable) {
                                        ((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())).setValue("output-"+(paginatedCount-1)+"-chance", stateSnapshot.getText());
                                        Salvage.getInstance().getRecipeController().save(((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())));
                                    }
                                    return Arrays.asList(AnvilGUI.ResponseAction.close());
                                })
                                .text("...")
                                .itemLeft(new ItemStack(Material.PAPER))
                                .title("Edit Loot Chance")
                                .plugin(Salvage.getInstance())
                                .open(session.getPlayer());
                        break;
                    case SHIFT_LEFT:
                        session.pause();
                        new AnvilGUI.Builder()
                                .onClose(player -> {
                                    Menus.getInstance().getTaskService().add(new DelayedMenuTask(1, null, player.getPlayer()));
                                })
                                .onClick((slot, stateSnapshot) -> {
                                    if (slot != AnvilGUI.Slot.OUTPUT) {
                                        return Collections.emptyList();
                                    }
                                    if (session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance()) instanceof IModifiable) {
                                        ((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())).setValue("output-"+(paginatedCount-1)+"-min", stateSnapshot.getText());
                                        Salvage.getInstance().getRecipeController().save(((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())));
                                    }
                                    return Arrays.asList(AnvilGUI.ResponseAction.close());
                                })
                                .text("...")
                                .itemLeft(new ItemStack(Material.PAPER))
                                .title("Edit Minimum Items")
                                .plugin(Salvage.getInstance())
                                .open(session.getPlayer());
                        break;
                    case RIGHT:
                        session.pause();
                        new AnvilGUI.Builder()
                                .onClose(player -> {
                                    Menus.getInstance().getTaskService().add(new DelayedMenuTask(1, null, player.getPlayer()));
                                })
                                .onClick((slot, stateSnapshot) -> {
                                    if (slot != AnvilGUI.Slot.OUTPUT) {
                                        return Collections.emptyList();
                                    }
                                    if (session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance()) instanceof IModifiable) {
                                        ((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())).setValue("output-"+(paginatedCount-1)+"-max", stateSnapshot.getText());
                                        Salvage.getInstance().getRecipeController().save(((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())));
                                    }
                                    return Arrays.asList(AnvilGUI.ResponseAction.close());
                                })
                                .text("...")
                                .itemLeft(new ItemStack(Material.PAPER))
                                .title("Edit Maximum Items")
                                .plugin(Salvage.getInstance())
                                .open(session.getPlayer());
                        break;
                    case SHIFT_RIGHT:
                        recipe.getOutput().remove(paginatedCount-1);
                        Salvage.getInstance().getRecipeController().save(recipe);
                        session.refresh();
                        break;
                }
            }
        }
    }

    private ItemStack getEmptyItem() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Empty Output Slot");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Click With Item On Cursor To Add");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack getOutputItem(Loot item) {
        ItemStack i = new ItemStack(item.getItem());
        ItemMeta meta = i.getItemMeta();
        if (meta == null) return i;
        List<String> lore = meta.hasLore() ? new ArrayList<>(meta.getLore()) : new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "Chance: " + ChatColor.GRAY + item.getChance()+"%");
        lore.add(ChatColor.WHITE + "Min: " + ChatColor.GRAY + item.getRange().getMin());
        lore.add(ChatColor.WHITE + "Max: " + ChatColor.GRAY + item.getRange().getMax());
        lore.add("");
        lore.add(ChatColor.GOLD + "Left-Click: " + ChatColor.YELLOW + "Edit Chance");
        lore.add(ChatColor.GOLD + "Shift-Left-Click: " + ChatColor.YELLOW + "Edit Minimum Amount");
        lore.add(ChatColor.GOLD + "Right-Click: " + ChatColor.YELLOW + "Edit Maximum Amount");
        lore.add(ChatColor.GOLD + "Shift-Right-Click: " + ChatColor.RED + "Remove");
        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}
