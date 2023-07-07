package co.bitengine.salvage;

import co.bitengine.salvage.io.DAOController;
import co.bitengine.salvage.io.IOSource;
import co.bitengine.salvage.logs.LogController;
import org.bukkit.plugin.java.JavaPlugin;

public final class Salvage extends JavaPlugin {
    private static Salvage instance;
    private DAOController daoController;
    private LogController logController;

    @Override
    public void onEnable() {
        instance = this;
        initControllers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initControllers() {
        daoController = new DAOController(IOSource.FILE);
        logController = new LogController();
    }

    public static Salvage getInstance() { return instance; }
    public DAOController getDAOController() { return daoController; }
    public LogController getLogController() { return logController; }
}
