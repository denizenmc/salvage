package co.bitengine.salvage.io.files;

import co.bitengine.salvage.io.IDAO;
import co.bitengine.salvage.models.SalvagePlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FilePlayerDAO implements IDAO<SalvagePlayerData> {
    @Override
    public Optional<SalvagePlayerData> get(UUID id) {
        File data = FileUtils.getFile(FileUtils.PLAYER_DIRECTORY+"/"+id.toString()+".yml");
        if (!data.exists()) return Optional.empty();
        return getFromFile(data);
    }

    @Override
    public Optional<SalvagePlayerData> get(String name) {
        return Optional.empty();
    }

    @Override
    public List<SalvagePlayerData> getAll() {
        List<SalvagePlayerData> all = new ArrayList<>();
        File players = FileUtils.getFile(FileUtils.PLAYER_DIRECTORY);
        if (!players.exists()) players.mkdirs();
        File [] list = players.listFiles();
        if (list != null && list.length > 0) {
            for (File player : list) {
                Optional<SalvagePlayerData> r = getFromFile(player);
                r.ifPresent(all::add);
            }
        }
        return all;
    }

    @Override
    public void save(SalvagePlayerData salvagePlayerData) {
        File data = FileUtils.getFile(FileUtils.PLAYER_DIRECTORY+"/"+salvagePlayerData.getUUID().toString()+".yml");
        if (!data.exists()) FileUtils.createFile(data);
        if (!data.exists()) return;
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(data);
        configuration.set("uuid", salvagePlayerData.getUUID().toString());
        int count = 1;
        for (ItemStack item : salvagePlayerData.getInput()) {
            configuration.set("input."+(count++), item);
        }
        FileUtils.saveYML(configuration, data);
    }

    @Override
    public void delete(SalvagePlayerData salvagePlayerData) {
        File data = FileUtils.getFile(FileUtils.PLAYER_DIRECTORY+"/"+salvagePlayerData.getUUID().toString()+".yml");
        if (data.exists()) data.delete();
    }

    private Optional<SalvagePlayerData> getFromFile(File file) {
        UUID uuid;
        List<ItemStack> input = new ArrayList<>();
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if (configuration.getString("uuid") == null) return Optional.empty();
        uuid = UUID.fromString(configuration.getString("uuid"));
        for (String count : configuration.getConfigurationSection("input").getValues(false).keySet()) {
            ItemStack i = configuration.getItemStack("input."+count);
            if (i != null) input.add(i);
        }
        return Optional.of(new SalvagePlayerData(uuid, input));
    }
}
