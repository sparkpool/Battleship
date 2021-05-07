package service.intrfaces;

import models.BattleshipOutput;
import models.Coordinate;

import java.util.List;

public interface IBattleshipPlayService {

    void addCoordinates(String gameId, String playerId, List<Coordinate> coordinates);

    void play(String gameId, String opponentPlayerID, List<Coordinate> coordinate);

    BattleshipOutput getPlayerOutput(String gameID, String playerID);
}
