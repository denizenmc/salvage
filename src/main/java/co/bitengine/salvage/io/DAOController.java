package co.bitengine.salvage.io;

import co.bitengine.salvage.io.files.FileRecipeDAO;
import co.bitengine.salvage.models.Recipe;

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
}
