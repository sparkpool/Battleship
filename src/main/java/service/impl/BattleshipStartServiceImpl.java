package service.impl;

import dao.impl.BattleshipStartDAOImpl;
import dao.intrface.IBattleshipStartDAO;
import service.intrfaces.IBattleshipStartService;

import java.security.InvalidParameterException;

public class BattleshipStartServiceImpl implements IBattleshipStartService {

    private IBattleshipStartDAO battleshipStartDAO = BattleshipStartDAOImpl.getInstance();

    @Override
    public String start(String firstPlayer, String secondPlayer, Integer gridSize) {
        this.validatePlayer(firstPlayer);
        this.validatePlayer(secondPlayer);
        this.validateGridSize(gridSize);
        return battleshipStartDAO.start(firstPlayer, secondPlayer, gridSize);
    }

    private void validatePlayer(String playerID) {
        if (playerID == null || playerID.length() == 0) {
            throw new InvalidParameterException("Input playerID is either null or empty");
        }
    }

    private void validateGridSize(Integer gridSize) {
        if (gridSize == null || gridSize < 1) {
            throw new InvalidParameterException("Invalid gridSize as it should be greater than 0");
        }
    }
}
