package co.bitengine.salvage.tasks;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import co.bitengine.salvage.models.SalvagePlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.denizenmc.menus.Menus;

public class SubmitTask extends SalvageTask {
    private Player player;
    private boolean started, complete;

    public SubmitTask(Player player) {
        super(System.currentTimeMillis());
        this.player = player; started = false; complete = false; }

    @Override
    boolean isSync() {
        return true;
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
        return o instanceof SubmitTask &&
                ((SubmitTask) o).player != null && player != null && ((SubmitTask) o).player.getUniqueId().equals(player.getUniqueId());
    }

    @Override
    public void run() {
        started = true;
        if (player == null || !Bukkit.getOnlinePlayers().contains(player)) {
            complete = true;
            return;
        }
        SalvagePlayerData data = Salvage.getInstance().getPlayerController().get(player);
        Utils.giveItems(player, Salvage.getInstance().getRecipeController().getLoot(data.getInput()));
        Utils.giveItems(player, data.getInput());
        data.getInput().clear();
        Salvage.getInstance().getTaskController().add(new SaveTask(data));
        complete = true;
    }
}
