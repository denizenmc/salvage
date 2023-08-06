package co.bitengine.salvage.logs;

import org.bukkit.ChatColor;

import java.util.logging.Level;

public class InfoSalvageLog extends SalvageLog {

    public InfoSalvageLog(String text) {
        super(text);
    }

    @Override
    public String getText() {
        return "[INFO] " + text;
    }

    @Override
    public String getConsoleText() {
        return "[Salvage] " + text;
    }

    @Override
    Level getLevel() {
        return Level.INFO;
    }
}
