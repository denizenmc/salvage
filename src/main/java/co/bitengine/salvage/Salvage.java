package co.bitengine.salvage;

import co.bitengine.salvage.io.DAOController;
import co.bitengine.salvage.io.IOSource;
import co.bitengine.salvage.logs.LogController;
import org.bukkit.plugin.java.JavaPlugin;

public final class Salvage extends JavaPlugin {

    private DAOController daoController;
    private LogController logController;

    @Override
    public void onEnable() {
        initDAOController();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initDAOController() {
        daoController = new DAOController(IOSource.FILE);
    }

    public DAOController getDAOController() { return daoController; }
}
