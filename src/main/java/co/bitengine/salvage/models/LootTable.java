package co.bitengine.salvage.models;

import java.util.ArrayList;
import java.util.List;

public class LootTable {
    private final List<Loot> loot;
    public LootTable() {
        loot = new ArrayList<>();
    }

    public List<Loot> getLoot() {
        return loot;
    }
}
