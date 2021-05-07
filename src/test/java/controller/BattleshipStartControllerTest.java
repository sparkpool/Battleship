package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

public class BattleshipStartControllerTest {

    private BattleshipStartController classToTest = new BattleshipStartController();

    @Test
    public void testStartGame_Success() {
        String gameID = classToTest.startGame(5, "P1", "P2");
        assertNotNull(gameID);
    }


    @Test
    public void testStartGame_InvalidPlayerID() {
        boolean isException = false;
        try {
            classToTest.startGame(5, "", "P2");
        } catch (InvalidParameterException e) {
            isException = true;
        }
        assertTrue(isException);
    }

    @Test
    public void testStartGame_InvalidGridSize() {
        boolean isException = false;
        try {
            classToTest.startGame(0, "P1", "P2");
        } catch (InvalidParameterException e) {
            isException = true;
        }
        assertTrue(isException);
    }

    @Test
    public void testAddShip_Success() {
        String gameID = classToTest.startGame(5, "P1", "P2");
        assertNotNull(gameID);
        classToTest.addShips(gameID, "P1", "2,3:4,3:2,0", 3);
        classToTest.addShips(gameID, "P2", "4,1:3,4:3,0", 3);
    }

    @Test
    public void testAddShip_InvalidCoordinatesSize() {
        String gameID = classToTest.startGame(5, "P1", "P2");
        boolean isException = false;
        assertNotNull(gameID);
        try {
            classToTest.addShips(gameID, "P1", "2,3:4,3:2,0", 4);
        } catch (InvalidParameterException e) {
            isException = true;
        }
        assertTrue(isException);
    }


    @Test
    public void testAddShip_InvalidCoordinate() {
        String gameID = classToTest.startGame(5, "P1", "P2");
        boolean isException = false;
        assertNotNull(gameID);
        try {
            classToTest.addShips(gameID, "P1", "2,3:4,3:5,0", 3);
        } catch (InvalidParameterException e) {
            isException = true;
        }
        assertTrue(isException);
    }

    @Test
    public void testAddShip_InvalidPlayer() {
        String gameID = classToTest.startGame(5, "P1", "P2");
        boolean isException = false;
        assertNotNull(gameID);
        try {
            classToTest.addShips(gameID, "P3", "2,3:4,3:5,0", 3);
        } catch (InvalidParameterException e) {
            isException = true;
        }
        assertTrue(isException);
    }
}
