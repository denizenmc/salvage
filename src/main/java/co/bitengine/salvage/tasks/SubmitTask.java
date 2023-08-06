package co.bitengine.salvage.tasks;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import org.bukkit.Bukkit;
import org.denizenmc.menus.components.Session;

public class SubmitTask extends SalvageTask {
    private Session session;
    private boolean started, complete;

    public SubmitTask(Session session) {
        super(System.currentTimeMillis());
        this.session = session; started = false; complete = false; }

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
                ((SubmitTask) o).session.getPlayer() != null && session.getPlayer() != null && ((SubmitTask) o).session.getPlayer().getUniqueId().equals(session.getPlayer().getUniqueId());
    }

    @Override
    public void run() {
        started = true;
        if (session.getPlayer() == null || !Bukkit.getOnlinePlayers().contains(session.getPlayer())) {
            complete = true;
            return;
        }
        Utils.giveItems(session.getPlayer(), Salvage.getInstance().getRecipeController().getLoot(session.getInput()));
        Utils.giveItems(session.getPlayer(), session.getInput());
        session.getInput().clear();
        session.clearInput();
        complete = true;
    }
}
