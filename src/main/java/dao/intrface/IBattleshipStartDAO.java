package dao.intrface;

import enums.EnumGameStatus;
import models.Battleship;

import java.util.Set;

public interface IBattleshipStartDAO {

    String start(String firstPlayer, String secondPlayer, Integer gridSize);

    Battleship getGameByGameID(String gameID);

    Integer getGridSizeByGameID(String gameID);

    void updateGameStatus(String gameID, EnumGameStatus status);
}
