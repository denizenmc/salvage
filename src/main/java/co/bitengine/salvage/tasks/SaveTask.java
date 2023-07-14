package co.bitengine.salvage.tasks;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.models.SalvagePlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.denizenmc.menus.Menus;

public class SaveTask extends SalvageTask {
    private SalvagePlayerData data;
    private boolean started, complete;

    public SaveTask(SalvagePlayerData data){
        super(System.currentTimeMillis());
        this.data = data; started = false; complete = false;  }

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
    public void run() {
        started = true;
        Salvage.getInstance().getPlayerController().save(data);
        Player player = Bukkit.getPlayer(data.getUUID());
        if (player != null) Menus.getAPI().getSession(player).refresh();
        complete = true;
    }
}
