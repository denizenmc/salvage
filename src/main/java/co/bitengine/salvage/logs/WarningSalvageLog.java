package co.bitengine.salvage.logs;

import java.util.logging.Level;

public class WarningSalvageLog extends SalvageLog {

    public WarningSalvageLog(String text) {
        super(text);
    }

    @Override
    public String getText() {
        return "[WARNING] " + text;
    }

    @Override
    String getConsoleText() {
        return "[Salvage] " + text;
    }

    @Override
    Level getLevel() {
        return Level.WARNING;
    }
}
