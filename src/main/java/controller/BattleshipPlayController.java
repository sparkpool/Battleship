package controller;

import models.BattleshipOutput;
import models.Coordinate;
import service.impl.BattleshipPlayServiceImpl;
import service.intrfaces.IBattleshipPlayService;
import util.BattleshipUtil;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class BattleshipPlayController {

    private IBattleshipPlayService battleshipPlayService = new BattleshipPlayServiceImpl();

    public void play(String gameID, String opponentPlayerID, String missiles, Integer noOfMissiles) {
        final List<Coordinate> coordinates = BattleshipUtil.parseCoordinates(missiles);
        if (coordinates.size() != noOfMissiles) {
            throw new InvalidParameterException("No Of missiles is not equal to the input missiles coordinates");
        }
        battleshipPlayService.play(gameID, opponentPlayerID, coordinates);
    }

    public List<String> getOutput(String gameID, String firstPlayer, String secondPlayer) {
        BattleshipOutput firstPlayerOutput = battleshipPlayService.getPlayerOutput(gameID, firstPlayer);
        BattleshipOutput secondPlayerOutput = battleshipPlayService.getPlayerOutput(gameID, secondPlayer);

        List<String> output = new ArrayList<>();
        output.add(firstPlayer);
        output.addAll(firstPlayerOutput.getPlayerGrid());
        output.add("\n");
        output.add(secondPlayer);
        output.addAll(secondPlayerOutput.getPlayerGrid());
        output.add("\n");
        output.add(firstPlayer + ":" + firstPlayerOutput.getHitCount());
        output.add(secondPlayer + ":" + secondPlayerOutput.getHitCount());
        if (firstPlayerOutput.getHitCount().equals(secondPlayerOutput.getHitCount())) {
            output.add("It's a draw");
        } else if (firstPlayerOutput.getHitCount() > secondPlayerOutput.getHitCount()) {
            output.add(firstPlayer + " wins");
        } else {
            output.add(secondPlayer + " wins");
        }
        return output;
    }
}
