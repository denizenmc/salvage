package co.bitengine.salvage.io.files;

import co.bitengine.salvage.io.IDAO;
import co.bitengine.salvage.logs.SalvageLog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileLogDAO implements IDAO<SalvageLog> {

    @Override
    public Optional<SalvageLog> get(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<SalvageLog> get(String name) {
        return Optional.empty();
    }

    @Override
    public List<SalvageLog> getAll() {
        return null;
    }

    @Override
    public void save(SalvageLog salvageLog) {

    }

    @Override
    public void delete(SalvageLog salvageLog) {

    }
}
