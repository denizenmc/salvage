package co.bitengine.salvage.tasks;

import co.bitengine.salvage.Salvage;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskController extends BukkitRunnable {
    private ConcurrentLinkedQueue<SalvageTask> tasks;
    public TaskController() { tasks = new ConcurrentLinkedQueue<>(); }

    @Override
    public void run() {
        tasks.forEach(task -> {
            try {
                if (task.isTimeout()) task.cancel();
            } catch (IllegalStateException ignored) {}
        });
        tasks.removeIf(task -> task.isTimeout() || task.isComplete());
        tasks.forEach(task -> {
            try {
                if (!task.isSync() && !task.isStarted()) task.runTaskAsynchronously(Salvage.getInstance());
                else if (!task.isStarted()) task.runTask(Salvage.getInstance());
            } catch (IllegalStateException ignored) {}
        });
    }

    public void add(SalvageTask task) {
        if (tasks.contains(task)) return;
        tasks.offer(task);
    }
}
