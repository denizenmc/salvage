package co.bitengine.salvage.logs;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;
import co.bitengine.salvage.io.IDAO;
import org.bukkit.Bukkit;

import java.util.Optional;

public class LogController {
    private String timestamp;

    public LogController() {
        timestamp = Utils.getDateTime();
    }

    public void log(SalvageLog log, boolean logToConsole) {
       Optional<IDAO<SalvageLog>> DAO = Salvage.getInstance().getDAOController().getLogDAO();
       DAO.ifPresent(salvageLogIDAO -> salvageLogIDAO.save(log));
       if (logToConsole) Bukkit.getLogger().log(log.getLevel(), log.getText());
    }

    public String getTimestamp() { return timestamp; }
}
