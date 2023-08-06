package co.bitengine.salvage.commands;

import org.bukkit.entity.Player;

public abstract class SalvageCommandHandler {
    protected Player player;
    public SalvageCommandHandler(Player player) { this.player = player; }
    public abstract void execute(String [] args);
}
