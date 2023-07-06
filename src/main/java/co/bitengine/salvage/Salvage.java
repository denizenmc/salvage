package co.bitengine.salvage;

import co.bitengine.salvage.io.DAOController;
import co.bitengine.salvage.io.IOSource;
import org.bukkit.plugin.java.JavaPlugin;

public final class Salvage extends JavaPlugin {

    private DAOController daoController;

    @Override
    public void onEnable() {
        // Plugin startup logic

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
