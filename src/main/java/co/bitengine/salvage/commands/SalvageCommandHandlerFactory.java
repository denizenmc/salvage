package co.bitengine.salvage.commands;

import org.bukkit.entity.Player;

public class SalvageCommandHandlerFactory {
    public SalvageCommandHandler getHandler(String command, Player player) {
        switch (command) {
            case "open":
                return new OpenSalvageCommandHandler(player);
        }
        return null;
    }
}
