package co.bitengine.salvage.logs;

public abstract class SalvageLog {
    protected String text;
    public SalvageLog(String text) {
        this.text = text;
    }
    abstract String getText();
}
