package co.bitengine.salvage.io.files;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.io.IDAO;
import co.bitengine.salvage.logs.SalvageLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        File log = FileUtils.getFile(FileUtils.LOG_DIRECTORY+"/"+Salvage.getInstance().getLogController().getTimestamp() +".log");
        if (!log.exists()) FileUtils.createFile(log);
        if (!log.exists()) return;
        try {
            FileWriter writer = new FileWriter(log);
            writer.write(salvageLog.getText());
            writer.close();
        } catch (IOException ignored) {}
    }

    @Override
    public void delete(SalvageLog salvageLog) {

    }
}
