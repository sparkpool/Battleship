package models;

import java.util.List;

public class BattleshipOutput {
    private List<String> playerGrid;
    private Integer hitCount;

    public List<String> getPlayerGrid() {
        return playerGrid;
    }

    public void setPlayerGrid(List<String> playerGrid) {
        this.playerGrid = playerGrid;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }
}
