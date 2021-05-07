package util;

import constants.BattleshipConstants;
import models.Coordinate;

import java.util.ArrayList;
import java.util.List;

public final class BattleshipUtil {

    public static List<Coordinate> parseCoordinates(final String coordinates) {
        List<Coordinate> result = new ArrayList<>();
        String[] parsedCoordinates = coordinates.split(BattleshipConstants.COORDINATES_SEPARATOR);
        for (final String parsedCoordinate : parsedCoordinates) {
            String[] finalCoordinate = parsedCoordinate.split(BattleshipConstants.COORDINATE_SEPARATOR);
            result.add(new Coordinate(Integer.parseInt(finalCoordinate[0].trim()), Integer.parseInt(finalCoordinate[1].trim())));
        }
        return result;
    }
}
