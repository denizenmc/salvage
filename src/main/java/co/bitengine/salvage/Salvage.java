package co.bitengine.salvage;

import co.bitengine.salvage.controllers.PlayerController;
import co.bitengine.salvage.controllers.RecipeController;
import co.bitengine.salvage.controllers.TestController;
import co.bitengine.salvage.io.DAOController;
import co.bitengine.salvage.io.IOSource;
import co.bitengine.salvage.logs.LogController;
import co.bitengine.salvage.logs.SevereSalvageLog;
import org.bukkit.plugin.java.JavaPlugin;

public final class Salvage extends JavaPlugin {
    private static Salvage instance;
    private DAOController daoController;
    private LogController logController;
    private RecipeController recipeController;
    private PlayerController playerController;

    @Override
    public void onEnable() {
        instance = this;
        initControllers();
        test();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initControllers() {
        daoController = new DAOController(IOSource.FILE);
        logController = new LogController();
        recipeController = new RecipeController();
        playerController = new PlayerController();
    }

    private void test() {
        TestController testController = new TestController();
        testController.runAllTests();
        if (!testController.getTestResults().isEmpty()) {
            testController.logTestResults();
            logController.log(new SevereSalvageLog("[TESTS FAILED] This plugin version is defective. Please report to the developer"), true);
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    public static Salvage getInstance() { return instance; }
    public DAOController getDAOController() { return daoController; }
    public LogController getLogController() { return logController; }

    public RecipeController getRecipeController() { return recipeController; }

    public PlayerController getPlayerController() { return playerController; }
}
