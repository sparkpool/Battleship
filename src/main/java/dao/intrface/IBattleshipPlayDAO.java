package dao.intrface;

import models.Coordinate;

import java.util.Map;
import java.util.Set;

public interface IBattleshipPlayDAO {

    void addCoordinate(String gameId, String playerId, Coordinate coordinate);

    void play(String gameId, String playerId, Coordinate coordinate);

    Set<Coordinate> getPlayerCoordinates(String gameId, String playerId);

    Set<Coordinate> getPlayerHitCoordinates(String gameId, String playerId);

    Set<Coordinate> getPlayerMissCoordinates(String gameId, String playerId);
}
