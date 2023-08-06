package co.bitengine.salvage;

import co.bitengine.salvage.commands.SalvageCommandController;
import co.bitengine.salvage.controllers.PlayerController;
import co.bitengine.salvage.controllers.RecipeController;
import co.bitengine.salvage.controllers.TestController;
import co.bitengine.salvage.guis.actions.*;
import co.bitengine.salvage.guis.menus.RecipeConfirmDeleteMenu;
import co.bitengine.salvage.guis.menus.RecipeEditMenu;
import co.bitengine.salvage.guis.menus.RecipeListMenu;
import co.bitengine.salvage.guis.menus.SalvageMenu;
import co.bitengine.salvage.io.DAOController;
import co.bitengine.salvage.io.IOSource;
import co.bitengine.salvage.listeners.InventoryClickListener;
import co.bitengine.salvage.logs.LogController;
import co.bitengine.salvage.logs.SevereSalvageLog;
import co.bitengine.salvage.tasks.TaskController;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
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
        initListeners();
        initControllers();
        test();
        initMenus();
        initCommands();
    }

    @Override
    public void onDisable() {}

    private void initListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryClickListener(), this);
    }

    private void initControllers() {
        daoController = new DAOController(IOSource.FILE);
        logController = new LogController();
        recipeController = new RecipeController();
        playerController = new PlayerController();

        taskController = new TaskController();
        taskController.runTaskTimerAsynchronously(this, 0, Utils.TASK_CONTROLLER_FREQUENCY_TICKS);
    }

    private void initCommands() {
       PluginCommand command = getCommand("salvage");
       if (command != null) command.setExecutor(new SalvageCommandController());
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

        Menus.getAPI().registerAction(new AddRecipeToContextAction(), this);
        Menus.getAPI().registerAction(new CreateRecipeAction(), this);
        Menus.getAPI().registerAction(new DeleteRecipeAction(), this);
        Menus.getAPI().registerAction(new EditRecipeAction(), this);
        Menus.getAPI().registerAction(new ModifyAction(), this);
        Menus.getAPI().registerAction(new EditRecipeInputAction(), this);
        Menus.getAPI().registerAction(new EditRecipeOutputAction(), this);
        Menus.getAPI().registerAction(new RemoveRecipeFromContextAction(), this);

        if (Menus.getAPI().getMenu(Utils.RECIPE_LIST_MENU) == null) new RecipeListMenu().create();
        if (Menus.getAPI().getMenu(Utils.RECIPE_EDIT_MENU) == null) new RecipeEditMenu().create();
        if (Menus.getAPI().getMenu(Utils.RECIPE_CONFIRM_DELETE_MENU) == null) new RecipeConfirmDeleteMenu().create();
        if (Menus.getAPI().getMenu(Utils.SALVAGE_MENU) == null) new SalvageMenu().create();
    }

    public static Salvage getInstance() { return instance; }
    public DAOController getDAOController() { return daoController; }
    public LogController getLogController() { return logController; }
    public RecipeController getRecipeController() { return recipeController; }
    public PlayerController getPlayerController() { return playerController; }
    public TaskController getTaskController() { return taskController; }
}
