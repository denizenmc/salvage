package co.bitengine.salvage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {
    public static int getIntFromString(String string) {
        int n = 0;
        try {
            n = Integer.parseInt(string);
        } catch (NumberFormatException ignored) {}
        return n;
    }
    public static double getDoubleFromString(String string) {
        double d = 0;
        try {
            d = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return d;
        }
        return d;
    }
    public static String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static double getRandomValue(double min, double max) {
        if (min == max) return min;
        double x = (Math.random() * ((max - min) + 1)) + min;
        return Math.round(x * 100.0) / 100.0;
    }

    public static void giveItems(Player player, List<ItemStack> items) {
        if (Bukkit.getPlayer(player.getUniqueId()) == null) return;
        for (ItemStack item : items) {
            if (item != null && item.getAmount() > 0) {
                if (player.getInventory().firstEmpty() == -1) player.getWorld().dropItemNaturally(player.getLocation(), item);
                else { player.getInventory().addItem(item); }
            }
        }
    }


    public static String RECIPE_ICON_HEAD = "Abolish_exe";
    public static double TASK_DURATION_THRESHOLD_SECONDS = 1.5;
    public static long TASK_CONTROLLER_FREQUENCY_TICKS = 1;
    public static String RECIPE_LIST_MENU = "salvage-recipe-list-menu";
    public static String RECIPE_EDIT_MENU = "salvage-recipe-edit-menu";
    public static String SALVAGE_MENU = "salvage-menu";
    public static String RECIPE_CONFIRM_DELETE_MENU = "salvage-recipe-confirm-delete-menu";
    public static String SALVAGE_ADMIN_PERMISSION = "salvage.admin";
}
