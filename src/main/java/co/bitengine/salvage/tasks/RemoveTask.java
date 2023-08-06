package co.bitengine.salvage.tasks;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.models.SalvagePlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RemoveTask extends SalvageTask {
    private Player player;
    private int index;
    private boolean started, complete;

    public RemoveTask(Player player, int index) {
        super(System.currentTimeMillis());
        this.player = player;
        this.index = index;
        started = false;
        complete = false;
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
        return o instanceof RemoveTask &&
                ((RemoveTask) o).player != null && player != null && ((RemoveTask) o).player.getUniqueId().equals(player.getUniqueId());
    }

    @Override
    public void run() {
        started = true;
        if (player == null || !Bukkit.getOnlinePlayers().contains(player)) {
            complete = true;
            return;
        }
        SalvagePlayerData data = Salvage.getInstance().getPlayerController().get(player);
        if (index < data.getInput().size()) {
            data.getInput().remove(index);
        }
        Salvage.getInstance().getTaskController().add(new SaveTask(data));
        complete = true;
    }
}
