package co.bitengine.salvage;

import co.bitengine.salvage.controllers.PlayerController;
import co.bitengine.salvage.controllers.RecipeController;
import co.bitengine.salvage.controllers.TestController;
import co.bitengine.salvage.guis.actions.SalvageInputAction;
import co.bitengine.salvage.guis.actions.SalvageOutputAction;
import co.bitengine.salvage.guis.actions.SalvageSubmitAction;
import co.bitengine.salvage.io.DAOController;
import co.bitengine.salvage.io.IOSource;
import co.bitengine.salvage.logs.LogController;
import co.bitengine.salvage.logs.SevereSalvageLog;
import co.bitengine.salvage.tasks.TaskController;
import org.bukkit.plugin.java.JavaPlugin;
import org.denizenmc.menus.Menus;

public final class Salvage extends JavaPlugin {
    private static Salvage instance;
    private DAOController daoController;
    private LogController logController;
    private RecipeController recipeController;
    private PlayerController playerController;
    private TaskController taskController;

    @Override
    public void onEnable() {
        instance = this;
        initControllers();
        test();
        initMenus();
    }

    @Override
    public void onDisable() {}

    private void initControllers() {
        daoController = new DAOController(IOSource.FILE);
        logController = new LogController();
        recipeController = new RecipeController();
        playerController = new PlayerController();

        taskController = new TaskController();
        taskController.runTaskTimerAsynchronously(this, 0, Utils.TASK_CONTROLLER_FREQUENCY_TICKS);
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

    private void initMenus() {
        Menus.getAPI().registerAction(new SalvageInputAction(), this);
        Menus.getAPI().registerAction(new SalvageOutputAction(), this);
        Menus.getAPI().registerAction(new SalvageSubmitAction(), this);
    }

    public static Salvage getInstance() { return instance; }
    public DAOController getDAOController() { return daoController; }
    public LogController getLogController() { return logController; }
    public RecipeController getRecipeController() { return recipeController; }
    public PlayerController getPlayerController() { return playerController; }
    public TaskController getTaskController() { return taskController; }
}
