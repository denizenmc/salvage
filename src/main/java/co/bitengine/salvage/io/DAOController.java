package co.bitengine.salvage.io;

import co.bitengine.salvage.io.files.FileLogDAO;
import co.bitengine.salvage.io.files.FilePlayerDAO;
import co.bitengine.salvage.io.files.FileRecipeDAO;
import co.bitengine.salvage.logs.SalvageLog;
import co.bitengine.salvage.models.Recipe;
import co.bitengine.salvage.models.SalvagePlayerData;

import java.util.Optional;

public class DAOController {
    IOSource source;
    public DAOController(IOSource source) { this.source = source; }

    public Optional<IDAO<Recipe>> getRecipeDAO() {
        if (source == IOSource.FILE) {
            return Optional.of(new FileRecipeDAO());
        }
        return Optional.empty();
    }

    public Optional<IDAO<SalvageLog>> getLogDAO() {
        if (source == IOSource.FILE) {
            return Optional.of(new FileLogDAO());
        }
        return Optional.empty();
    }

    public Optional<IDAO<SalvagePlayerData>> getPlayerDAO() {
        if (source == IOSource.FILE) {
            return Optional.of(new FilePlayerDAO());
        }
        return Optional.empty();
    }
}
