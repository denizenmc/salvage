package co.bitengine.salvage.tasks;

import co.bitengine.salvage.Utils;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class SalvageTask extends BukkitRunnable {
    private long startTime;
    public SalvageTask(long startTime) { this.startTime = startTime; }

    abstract boolean isSync();
    abstract boolean isStarted();
    abstract boolean isComplete();
    public boolean isTimeout() {
        return (System.currentTimeMillis()-startTime)*1.0/1000 > Utils.TASK_DURATION_THRESHOLD_SECONDS;
    }
}
