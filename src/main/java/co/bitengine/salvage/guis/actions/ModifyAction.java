package co.bitengine.salvage.guis.actions;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import co.bitengine.salvage.guis.SalvageContextKeys;
import co.bitengine.salvage.models.IModifiable;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.denizenmc.menus.Menus;
import org.denizenmc.menus.anvilgui.AnvilGUI;
import org.denizenmc.menus.components.Session;
import org.denizenmc.menus.components.actions.Action;
import org.denizenmc.menus.services.DelayedMenuTask;

import java.util.*;

public class ModifyAction extends Action {

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public String getName() {
        return "salvage-modify";
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList("&fSalvage modify model"));
    }

    @Override
    public String getIconPlayerHeadName() {
        return Utils.RECIPE_ICON_HEAD;
    }

    @Override
    public Map<String, String> getDefaultProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("key", "none");
        return properties;
    }

    @Override
    public Action copy() {
        return new ModifyAction();
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
        session.pause();
        String placeholder = session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance()) instanceof IModifiable
                && ((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())).getValue(getProperties().get("key")) instanceof String ?
                (String)((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())).getValue(getProperties().get("key")) : "...";
        new AnvilGUI.Builder()
                .onClose(player -> {
                    Menus.getInstance().getTaskService().add(new DelayedMenuTask(1, null, player.getPlayer()));
                })
                .onClick((slot, stateSnapshot) -> {
                    if (slot != AnvilGUI.Slot.OUTPUT) {
                        return Collections.emptyList();
                    }
                    if (session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance()) instanceof IModifiable) {
                        ((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())).setValue(getProperties().get("key"), stateSnapshot.getText());
                        Salvage.getInstance().getRecipeController().save(((IModifiable) session.getContext().getValue(SalvageContextKeys.MODIFIABLE, Salvage.getInstance())));
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.close());
                })
                .text(placeholder)
                .itemLeft(getItem())
                .title("Modify " + getProperties().get("key"))
                .plugin(Salvage.getInstance())
                .open(session.getPlayer());
    }

    private ItemStack getItem() {
        return new ItemStack(Material.PAPER);
    }
}
