package co.bitengine.salvage.controllers;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.logs.SalvageLog;
import co.bitengine.salvage.tests.LootTableServiceTest;
import co.bitengine.salvage.tests.QueryServiceTest;
import co.bitengine.salvage.tests.RecipeServiceTest;
import co.bitengine.salvage.tests.SalvageItemServiceTest;

import java.util.ArrayList;
import java.util.List;

public class TestController {
    List<SalvageLog> logs;
    public TestController() {
        logs = new ArrayList<>();
    }

    public void runAllTests() {
        logs.addAll(new LootTableServiceTest().run());
        logs.addAll(new QueryServiceTest().run());
        logs.addAll(new RecipeServiceTest().run());
        logs.addAll(new SalvageItemServiceTest().run());
    }

    public List<SalvageLog> getTestResults() {
        return logs;
    }

    public void logTestResults() {
        for (SalvageLog log : logs) Salvage.getInstance().getLogController().log(log, true);
    }
}
