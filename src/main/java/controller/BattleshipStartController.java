package controller;

import dao.intrface.IBattleshipPlayDAO;
import models.Coordinate;
import service.impl.BattleshipPlayServiceImpl;
import service.impl.BattleshipStartServiceImpl;
import service.intrfaces.IBattleshipPlayService;
import service.intrfaces.IBattleshipStartService;
import util.BattleshipUtil;

import java.security.InvalidParameterException;
import java.util.List;

public class BattleshipStartController {

    private IBattleshipStartService battleshipStartService = new BattleshipStartServiceImpl();
    private IBattleshipPlayService battleshipPlayService = new BattleshipPlayServiceImpl();

    public String startGame(Integer gridSize, String firstPlayer, String secondPlayer) {
        this.validatePlayer(firstPlayer);
        this.validatePlayer(secondPlayer);
        return battleshipStartService.start(firstPlayer, secondPlayer, gridSize);
    }

    private void validatePlayer(String player) {
        if (player == null || player.length() == 0) {
            throw new InvalidParameterException("Invalid player name to start the Game");
        }
    }

    public void addShips(String gameId, String playerID, String coordinates, Integer noOfCoordinates) {
        List<Coordinate> coordinatesList = BattleshipUtil.parseCoordinates(coordinates);
        if (!noOfCoordinates.equals(coordinatesList.size())) {
            throw new InvalidParameterException("NoOfCoordinates are not equal to the coordinates given in the next row");
        }
        battleshipPlayService.addCoordinates(gameId, playerID, coordinatesList);
    }
}
