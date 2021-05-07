package dao.impl;

import dao.intrface.IBattleshipStartDAO;
import enums.EnumGameStatus;
import models.Battleship;

import java.util.*;

public class BattleshipStartDAOImpl implements IBattleshipStartDAO {

    private final static BattleshipStartDAOImpl INSTANCE = new BattleshipStartDAOImpl();

    private BattleshipStartDAOImpl() {

    }

    public static BattleshipStartDAOImpl getInstance() {
        return INSTANCE;
    }

    private final Map<String, Battleship> GAMES = new HashMap<>();

    @Override
    public String start(String firstPlayer, String secondPlayer, Integer gridSize) {
        final String gameID = UUID.randomUUID().toString();
        Set<String> players = new HashSet<>();
        players.add(firstPlayer);
        players.add(secondPlayer);
        GAMES.put(gameID, new Battleship(players, EnumGameStatus.CREATED, gridSize, gameID));
        return gameID;
    }

    @Override
    public Battleship getGameByGameID(String gameID) {
        return GAMES.get(gameID);
    }

    @Override
    public Integer getGridSizeByGameID(String gameID) {
        return this.GAMES.get(gameID).getGridSize();
    }

    @Override
    public void updateGameStatus(String gameID, EnumGameStatus status) {
        Battleship battleship = this.GAMES.get(gameID);
        battleship.setStatus(status);
    }
}
