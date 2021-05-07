import constants.BattleshipConstants;
import controller.BattleshipPlayController;
import controller.BattleshipStartController;
import processor.impl.FileInputProcessor;
import processor.impl.FileOutputProcessor;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * This class is the entry point to start the battleship game.
 * <p>
 * This class is processing based on input file and and assuming that there will be two players only in this game.
 */
public class BattleshipStarter {

    private static BattleshipStartController startController = new BattleshipStartController();
    private static BattleshipPlayController playController = new BattleshipPlayController();

    private static FileInputProcessor fileInputProcessor = new FileInputProcessor();
    private static FileOutputProcessor fileOutputProcessor = new FileOutputProcessor();

    public static void main(String[] args) {
        System.out.println("Battleship game started");

        String firstPlayer = BattleshipConstants.FIRST_PLAYER_ID;
        String secondPlayer = BattleshipConstants.SECOND_PLAYER_ID;
        System.out.print("....");
        List<String> input = fileInputProcessor.process(BattleshipConstants.FILE_PATH + "Input.txt");
        if (input.size() < 7) {
            throw new InvalidParameterException("Insufficient number of rows in the Input file");
        }
        System.out.print("...............");
        int gridSize = Integer.parseInt(input.get(0).trim());
        int noOfCoordinates = Integer.parseInt(input.get(1).trim());
        String firstPlayerCoordinates = input.get(2).trim();
        String secondPlayerCoordinates = input.get(3).trim();
        Integer noOfMissiles = Integer.parseInt(input.get(4).trim());
        String firstPlayerMissiles = input.get(5).trim();
        String secondPlayerMissiles = input.get(6).trim();
        System.out.print(".......");
        String gameID = startController.startGame(gridSize, firstPlayer, secondPlayer);
        System.out.print("..");
        startController.addShips(gameID, firstPlayer, firstPlayerCoordinates, noOfCoordinates);
        startController.addShips(gameID, secondPlayer, secondPlayerCoordinates, noOfCoordinates);
        System.out.print(".........");

        System.out.print("\n Battleship game InProgress ");

        playController.play(gameID, secondPlayer, firstPlayerMissiles, noOfMissiles);
        System.out.print("\n.......");
        playController.play(gameID, firstPlayer, secondPlayerMissiles, noOfMissiles);
        System.out.print("\n Game Play Completed, creating the final output");
        System.out.print("\n.......");
        List<String> output = playController.getOutput(gameID, firstPlayer, secondPlayer);
        System.out.print(".............");
        fileOutputProcessor.process(BattleshipConstants.FILE_PATH + "Output.txt", output);
        System.out.print("\n Battleship game completed and output is written to the file ");
    }
}
