package dao.impl;

import dao.intrface.IBattleshipPlayDAO;
import models.Coordinate;

import java.util.*;

public class BattleshipPlayDAOImpl implements IBattleshipPlayDAO {

    private final static BattleshipPlayDAOImpl INSTANCE = new BattleshipPlayDAOImpl();

    private BattleshipPlayDAOImpl() {

    }

    public static BattleshipPlayDAOImpl getInstance() {
        return INSTANCE;
    }

    private final Map<String, Set<Coordinate>> PLAYER_COORDINATES = new HashMap<>();
    private final Map<String, Set<Coordinate>> PLAYER_HIT_COORDINATES = new HashMap<>();
    private final Map<String, Set<Coordinate>> PLAYER_MISS_COORDINATES = new HashMap<>();

    @Override
    public void addCoordinate(String gameId, String playerId, Coordinate coordinate) {
        PLAYER_COORDINATES.computeIfAbsent(generateKey(gameId, playerId), (k) -> new HashSet<>()).add(coordinate);
    }

    private String generateKey(String gameId, String playerId) {
        return gameId + "+" + playerId;
    }

    @Override
    public void play(String gameId, String playerId, Coordinate coordinate) {
        String key = generateKey(gameId, playerId);
        Set<Coordinate> coordinates = PLAYER_COORDINATES.get(key);
        if (coordinates == null) {
            throw new UnsupportedOperationException("This operation is not supported until addShip operation is completed");
        }
        if (coordinates.contains(coordinate)) {
            coordinates.remove(coordinate);
            PLAYER_HIT_COORDINATES.computeIfAbsent(key, (k) -> new HashSet<>()).add(coordinate);
        } else {
            PLAYER_MISS_COORDINATES.computeIfAbsent(key, (k) -> new HashSet<>()).add(coordinate);
        }
    }

    @Override
    public Set<Coordinate> getPlayerCoordinates(String gameId, String playerId) {
        return PLAYER_COORDINATES.get(generateKey(gameId, playerId));
    }

    @Override
    public Set<Coordinate> getPlayerHitCoordinates(String gameId, String playerId) {
        return PLAYER_HIT_COORDINATES.get(generateKey(gameId, playerId));
    }

    @Override
    public Set<Coordinate> getPlayerMissCoordinates(String gameId, String playerId) {
        return PLAYER_MISS_COORDINATES.get(generateKey(gameId, playerId));
    }
}
