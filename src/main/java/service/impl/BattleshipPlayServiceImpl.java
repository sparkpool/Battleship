package service.impl;

import constants.BattleshipConstants;
import dao.impl.BattleshipPlayDAOImpl;
import dao.impl.BattleshipStartDAOImpl;
import dao.intrface.IBattleshipPlayDAO;
import dao.intrface.IBattleshipStartDAO;
import models.Battleship;
import models.BattleshipOutput;
import models.Coordinate;
import service.intrfaces.IBattleshipPlayService;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BattleshipPlayServiceImpl implements IBattleshipPlayService {

    private IBattleshipPlayDAO battleshipPlayDAO = BattleshipPlayDAOImpl.getInstance();
    private IBattleshipStartDAO battleshipStartDAO = BattleshipStartDAOImpl.getInstance();

    @Override
    public void addCoordinates(String gameId, String playerId, List<Coordinate> coordinates) {
        this.validateGameAndPlayer(gameId, playerId);
        Battleship game = this.battleshipStartDAO.getGameByGameID(gameId);
        for (final Coordinate coordinate : coordinates) {
            this.validateCoordinate(game.getGridSize(), coordinate);
            battleshipPlayDAO.addCoordinate(gameId, playerId, coordinate);
        }
    }

    private void validateCoordinate(Integer gridSize, Coordinate coordinate) {
        if (coordinate.getX() < 0 || coordinate.getY() < 0 || coordinate.getX() >= gridSize || coordinate.getY() >= gridSize) {
            throw new InvalidParameterException("One of the given coordinate is out of range of the grid " + coordinate);
        }
    }

    private void validateGameAndPlayer(String gameID, String playerID) {
        Battleship game = this.battleshipStartDAO.getGameByGameID(gameID);
        if (game == null) {
            throw new InvalidParameterException("Invalid GameID as game does not exist with gameID " + gameID);
        }
        Set<String> players = game.getPlayers();
        if (!players.contains(playerID)) {
            throw new InvalidParameterException("Invalid Player as no player with playerId " + playerID + " does not exist in the input game with gameID " + gameID);
        }
    }

    @Override
    public void play(String gameId, String opponentPlayerID, List<Coordinate> coordinates) {
        this.validateGameAndPlayer(gameId, opponentPlayerID);
        for (final Coordinate coordinate : coordinates) {
            battleshipPlayDAO.play(gameId, opponentPlayerID, coordinate);
        }
    }

    @Override
    public BattleshipOutput getPlayerOutput(String gameID, String playerID) {
        this.validateGameAndPlayer(gameID, playerID);
        Set<Coordinate> remainingCoordinates = battleshipPlayDAO.getPlayerCoordinates(gameID, playerID);
        Set<Coordinate> hitCoordinates = battleshipPlayDAO.getPlayerHitCoordinates(gameID, playerID);
        Set<Coordinate> missCoordinates = battleshipPlayDAO.getPlayerMissCoordinates(gameID, playerID);

        Integer gridSize = battleshipStartDAO.getGridSizeByGameID(gameID);
        StringBuilder line = new StringBuilder();
        List<String> lines = new ArrayList<>();
        int hitCount = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                if (hitCoordinates != null && hitCoordinates.contains(coordinate)) {
                    hitCount++;
                    line.append(BattleshipConstants.HIT_SLOT);
                } else if (missCoordinates != null && missCoordinates.contains(coordinate)) {
                    line.append(BattleshipConstants.MISS_SLOT);
                } else if (remainingCoordinates.contains(coordinate)) {
                    line.append(BattleshipConstants.ALIVE_SLOT);
                } else {
                    line.append(BattleshipConstants.EMPTY_SLOT);
                }
            }
            lines.add(line.toString());
            line.setLength(0);
        }
        BattleshipOutput output = new BattleshipOutput();
        output.setPlayerGrid(lines);
        output.setHitCount(hitCount);
        return output;
    }
}
