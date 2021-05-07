package controller;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BattleshipPlayControllerTest {

    private BattleshipPlayController classToTest = new BattleshipPlayController();
    private BattleshipStartController startController = new BattleshipStartController();

    @Test
    public void testPlay_Success() {
        String gameID = startController.startGame(5, "P1", "P2");
        assertNotNull(gameID);
        startController.addShips(gameID, "P1", "2,3:4,3:2,0", 3);
        startController.addShips(gameID, "P2", "4,1:3,4:3,0", 3);
        classToTest.play(gameID, "P2", "4,3:2,3", 2);
        classToTest.play(gameID, "P1", "0,0:2,3", 2);
    }

    @Test
    public void testPlay_WithoutAddShipCall() {
        String gameID = startController.startGame(5, "P1", "P2");
        assertNotNull(gameID);
        boolean isException = false;
        try {
            classToTest.play(gameID, "P2", "4,3:2,3", 2);
        } catch (UnsupportedOperationException e) {
            isException = true;
        }
        assertTrue(isException);
    }

    @Test
    public void testPlay_InvalidGameID() {
        boolean isException = false;
        try {
            classToTest.play("gameID", "P2", "2,0", 1);
        } catch (InvalidParameterException e) {
            isException = true;
        }
        assertTrue(isException);
    }

    @Test
    public void testOutput_Success() {
        String gameID = startController.startGame(5, "P1", "P2");
        assertNotNull(gameID);
        startController.addShips(gameID, "P1", "1,1:2,0:2,3:3,4:4,3", 5);
        startController.addShips(gameID, "P2", "0,1:2,3:3,0:3,4:4,1", 5);
        classToTest.play(gameID, "P2", "0,1:4,3:2,3:3,1:4,1", 5);
        classToTest.play(gameID, "P1", "0,1:0,0:1,1:2,3:4,3", 5);

        List<String> result = classToTest.getOutput(gameID, "P1", "P2");
        assertNotNull(result);
        assertEquals(result.size(), 17);
        assertEquals(result.get(0), "P1");
        assertEquals(result.get(1), "OO___");
        assertEquals(result.get(2), "_X___");
        assertEquals(result.get(3), "B__X_");
        assertEquals(result.get(4), "____B");
        assertEquals(result.get(5), "___X_");
        assertEquals(result.get(7), "P2");
        assertEquals(result.get(8), "_X___");
        assertEquals(result.get(9), "_____");
        assertEquals(result.get(10), "___X_");
        assertEquals(result.get(11), "BO__B");
        assertEquals(result.get(12), "_X_O_");
        assertEquals(result.get(14), "P1:3");
        assertEquals(result.get(15), "P2:3");
        assertEquals(result.get(16), "It's a draw");
    }
}
