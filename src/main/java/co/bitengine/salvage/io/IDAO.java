package co.bitengine.salvage.io;

import co.bitengine.salvage.models.RecipeQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDAO<T> {
    Optional<T> get(UUID id);
    Optional<T> get(String name);
    List<T> getAll();
    List<T> getAll(RecipeQuery recipeQuery);
    void save(T t);
    void delete(T t);
}
