package co.bitengine.salvage.commands;

import co.bitengine.salvage.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.denizenmc.menus.Menus;

public class OpenSalvageCommandHandler extends SalvageCommandHandler {
    public OpenSalvageCommandHandler(Player player) {
        super(player);
    }

    @Override
    public void execute(String[] args) {
        if (player != null && !player.hasPermission(Utils.SALVAGE_ADMIN_PERMISSION)) return;
        if (args.length == 2 && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
            Menus.getAPI().getSession(Bukkit.getPlayer(args[1]), Utils.SALVAGE_MENU).open();
        }
    }
}
