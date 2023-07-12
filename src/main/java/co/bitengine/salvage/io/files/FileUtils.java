package co.bitengine.salvage.io.files;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.logs.SevereSalvageLog;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

class FileUtils {
    static String RECIPE_DIRECTORY = "recipes";
    static String LOG_DIRECTORY = "logs";
    static String PLAYER_DIRECTORY = "players";

    static File getFile(String path) {
        File f = Salvage.getInstance().getDataFolder();
        if (!f.exists()) f.mkdirs();
        for (String file : path.split("/")) {
            f = new File(f, file);
        }
        return f;
    }

    static void createFile(File file) {
        if (file == null) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("[IO] Failed to create null file"), true);
            return;
        }
        if (file.exists()) {
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("[IO] Failed to create file " + file.getName()), true);
        }
    }

    static void saveYML(FileConfiguration cfg, File file) {
        try {
            cfg.save(file);
        } catch (IOException e) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("[IO] Failed to save YML to " + file.getName()), true);
        }
    }

}
