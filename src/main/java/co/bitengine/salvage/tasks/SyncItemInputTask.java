package co.bitengine.salvage.tasks;

import org.denizenmc.menus.components.Session;

public class SyncItemInputTask extends SalvageTask {

    private boolean started, complete;
    private Session session;

    public SyncItemInputTask(Session session) {
        super(System.currentTimeMillis());
        started = false;
        complete = false;
        this.session = session;
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
    public void run() {
        started = true;
        session.syncInputSlots();
        complete = true;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SyncItemInputTask &&
                ((SyncItemInputTask) o).session != null && session != null && ((SyncItemInputTask) o).session.getPlayer().getUniqueId().equals(session.getPlayer().getUniqueId());
    }
}
