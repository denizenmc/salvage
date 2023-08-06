package co.bitengine.salvage.commands;

import co.bitengine.salvage.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.denizenmc.menus.Menus;

public class SalvageCommandController implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission(Utils.SALVAGE_ADMIN_PERMISSION) && sender instanceof Player) {
                Menus.getAPI().getSession((Player) sender, Utils.RECIPE_LIST_MENU).open();
            }
        } else {
            Player player = sender instanceof Player ? (Player) sender : null;
            SalvageCommandHandler handler = new SalvageCommandHandlerFactory().getHandler(args[0], player);
            if (handler != null) handler.execute(args);
        }
        return true;
    }
}
