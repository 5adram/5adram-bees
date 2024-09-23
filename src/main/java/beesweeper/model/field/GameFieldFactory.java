package beesweeper.model.field;

import beesweeper.model.shape.CoordinateGenerator;
import beesweeper.model.shape.FieldShape;
import beesweeper.model.shape.ShapeFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Factory for {@link GameField}. */
public class GameFieldFactory {

  private final ShapeFactory shapeFactory;
  private final CoordinateGenerator beeCoordinateGenerator;

  /** generate the new shape of game
   *
   * @param shapeFactory take the shape of the game
   * @param beeCoordinateGenerator generate randomly the given number of bees
   */
  public GameFieldFactory(ShapeFactory shapeFactory, CoordinateGenerator beeCoordinateGenerator) {
    this.shapeFactory = shapeFactory;
    this.beeCoordinateGenerator = beeCoordinateGenerator;
  }

  /**
   * Creates a new {@link GameField} object from this factory's configuration. The game field will
   * have the given number of bees hidden, and the given number of flowers available for marking
   * cells.
   *
   * <p>The location of hidden bees is determined by the {@link CoordinateGenerator} given in the
   * constructor of this class.
   *
   * @param numBees number of bees to hide on the game field.
   * @param numFlowers number of flowers that should be initially available to the player.
   * @return a new game field instance with the configured parameters.
   */
  public GameField create(int numBees, int numFlowers) {
    if (numBees < 1) {
      throw new IllegalArgumentException("Can't generate valid game field with less than 1 bee");
    }

    // This method is a recommendation, may be changed if desired.
    FieldShape initialShape = shapeFactory.create();
    initializeEmptyCells(initialShape);
    addBeesToShape(initialShape, numBees, beeCoordinateGenerator);
    addNumbersOfSurroundingBeesToShape(initialShape);
    return new GameField(initialShape, numFlowers);
  }

  // Recommendation, may be changed if desired.
  // Add the number of bees to the given shape, using the beeCoordinateGenerator.

  /**
   * Add bees to a given field shape.
   *
   * @param emptyShape          the field shape to which bees will be added
   * @param numBees             the number of bees to add to the field shape
   * @param beeCoordinateGenerator    the generator for the bee coordinate
   * @throws IllegalArgumentException  if the number of bees exceeds the number of available cells in the field shape
   */
  private void addBeesToShape(
      FieldShape emptyShape, int numBees, CoordinateGenerator beeCoordinateGenerator) {

    if (emptyShape.getAllCoordinates().size() <= numBees) {
      throw new IllegalArgumentException(
          "Can't generate valid game field with number of bees >= number of field cells: "
              + emptyShape.getAllCoordinates().size()
              + " vs. "
              + numBees
              + " bees");
    }

    Collection<Coordinate> beeC = beeCoordinateGenerator.getCoordinates(numBees, emptyShape);
    for (Coordinate temporaryCoordinate : beeC) {
      Cell newCell = new Cell(true, 0);
      emptyShape.replace(temporaryCoordinate, newCell);
    }
  }


  /**
   *  initialize empty cells in the game
   * @param field  field of the game
   */
  private void initializeEmptyCells(FieldShape field) {
    for (Coordinate c : field.getAllCoordinates()) {
      Cell emptyCell = new Cell(false, 0);
      field.replace(c, emptyCell);
    }
  }

  /**
   * Convert the initial board into a concrete game board. The number of bees around an empty cell
   * is now counted.
   */
  private void addNumbersOfSurroundingBeesToShape(FieldShape field) {
    Set<Coordinate> beeCoordinates = new HashSet<>();
    for (Coordinate c : field.getAllCoordinates()) {
      if (field.get(c).isBee()) {
        beeCoordinates.add(c);
      }
    }

    for (Coordinate c : field.getAllCoordinates()) {
      Cell cell = field.get(c);
      if (!cell.isBee()) {
        int numbersOfSurroundingBees = 0;
        List<Coordinate> allSurrounding = c.getSurroundingCoordinates(c);
        for (Coordinate surrounding : allSurrounding) {
          if (beeCoordinates.contains(surrounding)) {
            numbersOfSurroundingBees++;
          }
        }
        cell.setNumberOfBeesSurrounding(numbersOfSurroundingBees);
      }
    }


  }
}
