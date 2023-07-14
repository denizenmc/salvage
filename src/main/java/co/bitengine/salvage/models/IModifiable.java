package co.bitengine.salvage.models;

public interface IModifiable {
    Object getValue(String key);
    void setValue(String key, Object o);
}
