package co.bitengine.salvage.controllers;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.io.IDAO;
import co.bitengine.salvage.logs.SevereSalvageLog;
import co.bitengine.salvage.models.SalvagePlayerData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerController {
    private IDAO<SalvagePlayerData> dao;
    private List<SalvagePlayerData> cache;

    public PlayerController() {
        dao = getDAO();
        cache = getAll();
    }

    public SalvagePlayerData get(Player player) {
        for (SalvagePlayerData data : getCache()) {
            if (data.getUUID().equals(player.getUniqueId())) return data;
        }
        SalvagePlayerData newData = new SalvagePlayerData(player.getUniqueId(), new ArrayList<>());
        save(newData);
        return newData;
    }

    public void save(SalvagePlayerData playerData) {
        if (!cache.contains(playerData)) {
            cache.add(playerData);
        }
        if (dao == null) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("Failed to save player data to IO source"), true);
        } else {
            dao.save(playerData);
        }
    }

    private IDAO<SalvagePlayerData> getDAO() {
        Optional<IDAO<SalvagePlayerData>> playerDAO = Salvage.getInstance().getDAOController().getPlayerDAO();
        return playerDAO.orElse(null);
    }

    private List<SalvagePlayerData> getCache() {
        return new ArrayList<>(cache);
    }

    private List<SalvagePlayerData> getAll() {
        if (dao == null) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("Failed to load player data from IO source"), true);
            return new ArrayList<>();
        }
        return dao.getAll();
    }

}
