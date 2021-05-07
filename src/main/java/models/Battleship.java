package models;

import enums.EnumGameStatus;

import java.util.Set;

public class Battleship {

    private Set<String> players;
    private EnumGameStatus status;
    private Integer gridSize;
    private String gameID;

    public Battleship(Set<String> players, EnumGameStatus status, Integer gridSize, String gameID) {
        this.players = players;
        this.status = status;
        this.gridSize = gridSize;
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }

    public Integer getGridSize() {
        return gridSize;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public EnumGameStatus getStatus() {
        return status;
    }

    public void setStatus(EnumGameStatus status) {
        this.status = status;
    }
}
