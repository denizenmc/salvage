package co.bitengine.salvage.guis.actions;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.guis.SalvageContextKeys;
import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.SalvagePlayerData;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.denizenmc.menus.components.Session;
import org.denizenmc.menus.components.actions.Action;

import java.util.*;

public class SalvageOutputAction extends Action {
    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public String getName() {
        return "salvage-output";
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList("&fSalvage output slot", "&7Possible output items will appear"));
    }

    @Override
    public String getIconPlayerHeadName() {
        return "MCadir1";
    }

    @Override
    public Map<String, String> getDefaultProperties() {
        return new HashMap<>();
    }

    @Override
    public Action copy() {
        return new SalvageOutputAction();
    }

    @Override
    public boolean isDynamicIcon() {
        return true;
    }

    @Override
    public ItemStack getDynamicIcon(Session session, int i) {
        if (!(session.getContext().getValue(SalvageContextKeys.LOADED_POSSIBLE_LOOT, Salvage.getInstance()) instanceof Map)) return null;
        Map<Loot, Integer> loot = (Map) session.getContext().getValue(SalvageContextKeys.LOADED_POSSIBLE_LOOT, Salvage.getInstance());
        if (loot == null) return null;
        List<Loot> ordered = getLootFromMap(loot);
        int paginatedCount = (session.getPage()-1)*session.getMenu().getTotal(this)+i;
        if (paginatedCount <= ordered.size()) {
            return getLootItem(ordered.get(paginatedCount-1), loot.get(ordered.get(paginatedCount-1)));
        }
        return null;
    }

    @Override
    public void onBuild(Session session, int i) {
        if (i == 1) {
            Map<Loot, Integer> possibleLoot = Salvage.getInstance().getRecipeController().getPossibleLoot(session.getInput());
            session.getContext().setValue(SalvageContextKeys.LOADED_POSSIBLE_LOOT, Salvage.getInstance(), possibleLoot);
        }
    }

    @Override
    public void onClick(Session session, int i, InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);
    }

    private List<Loot> getLootFromMap(Map map) {
        List<Loot> loot = new ArrayList<>();
        for (Object o : map.keySet()) {
            if (o instanceof Loot) loot.add((Loot) o);
        }
        Collections.sort(loot);
        return loot;
    }

    private ItemStack getLootItem(Loot loot, int count) {
        ItemStack item = new ItemStack(loot.getItem());
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.hasLore() ? new ArrayList<>(meta.getLore()) : new ArrayList<>();
            lore.add(ChatColor.YELLOW + "Count: " + ChatColor.GOLD + "(x" + count + ")");
            lore.add("");
            lore.add(ChatColor.YELLOW + "Chance: " + ChatColor.WHITE + loot.getChance() + "%");
            lore.add(ChatColor.YELLOW + "Min Amount: " + ChatColor.GRAY + loot.getRange().getMin());
            lore.add(ChatColor.YELLOW + "Max Amount: " + ChatColor.GRAY + loot.getRange().getMax());
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }
}
