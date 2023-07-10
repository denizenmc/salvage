package co.bitengine.salvage.io.files;

import co.bitengine.salvage.io.IDAO;
import co.bitengine.salvage.models.Recipe;
import co.bitengine.salvage.models.RecipeQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileRecipeDAO implements IDAO<Recipe> {

    @Override
    public Optional<Recipe> get(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Recipe> get(String name) {
        return Optional.empty();
    }

    @Override
    public List<Recipe> getAll() {
        return null;
    }

    @Override
    public List<Recipe> getAll(RecipeQuery recipeQuery) {
        return null;
    }

    @Override
    public void save(Recipe recipe) {

    }

    @Override
    public void delete(Recipe recipe) {

    }
}
