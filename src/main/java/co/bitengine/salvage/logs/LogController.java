package co.bitengine.salvage.logs;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import co.bitengine.salvage.io.IDAO;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogController {
    private String timestamp;
    private List<String> cache;

    public LogController() {
        timestamp = Utils.getDateTime();
        cache = new ArrayList<>();
    }

    public void log(SalvageLog log, boolean logToConsole) {
       Optional<IDAO<SalvageLog>> DAO = Salvage.getInstance().getDAOController().getLogDAO();
       DAO.ifPresent(salvageLogIDAO -> salvageLogIDAO.save(log));
       if (logToConsole) Bukkit.getLogger().log(log.getLevel(), log.getText());
       cache.add(log.getText());
    }

    public List<String> getLogsFromCurrentSession(int page) {
        List<String> logs = new ArrayList<>();
        if ((page-1)*10 >= cache.size()) return logs;
        for (int i = (page-1)*10; i < (page-1)*10+10 && i < cache.size(); i++) {
            logs.add(cache.get(i));
        }
        return logs;
    }

    public String getTimestamp() { return timestamp; }
}
