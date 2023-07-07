package co.bitengine.salvage.logs;

import java.util.logging.Level;

public class SevereSalvageLog extends SalvageLog {

    public SevereSalvageLog(String text) {
        super(text);
    }

    @Override
    public String getText() {
        return "[SEVERE] " + text;
    }

    @Override
    Level getLevel() {
        return Level.SEVERE;
    }
}
