package co.bitengine.salvage.tasks;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.models.SalvagePlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InputTask extends SalvageTask {
    Player player;
    int index;
    ItemStack cursor;
    boolean started, complete;
    public InputTask(Player player, int index, ItemStack cursor) {
        super(System.currentTimeMillis());
        this.player = player;
        this.index = index;
        this.cursor = cursor;
        complete = false;
        started = false;
    }

    @Override
    boolean isSync() {
        return false;
    }

    @Override
    boolean isStarted() {
        return started;
    }

    @Override
    boolean isComplete() {
        return complete;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof InputTask &&
                ((InputTask) o).player != null && player != null && ((InputTask) o).player.getUniqueId().equals(player.getUniqueId());
    }

    @Override
    public void run() {
        started = true;
        if (player == null || !Bukkit.getOnlinePlayers().contains(player)) {
            complete = true;
            return;
        }
        SalvagePlayerData data = Salvage.getInstance().getPlayerController().get(player);
        if (!data.getInput().isEmpty() && index < data.getInput().size()) {
            data.getInput().remove(index);
        }
        if (cursor != null && cursor.getType() != Material.AIR) {
            data.getInput().add(cursor);
        }
        Salvage.getInstance().getTaskController().add(new SaveTask(data));
        complete = true;
    }
}
