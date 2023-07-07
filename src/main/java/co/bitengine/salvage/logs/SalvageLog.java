package co.bitengine.salvage.logs;

import java.util.logging.Level;

public abstract class SalvageLog {
    protected String text;
    public SalvageLog(String text) {
        this.text = text;
    }
    abstract String getText();
    abstract Level getLevel();
}
