package beesweeper.model.field;

import beesweeper.model.BeeSweeper;
import beesweeper.model.shape.FieldShape;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the game field of the {@link BeeSweeper} game. Consists of the field shape
 * with the current cell status, and the number of flowers that are available for use. It is
 * responsible for all internal cell manipulation.
 *
 * <p>Users of the {@link BeeSweeper} game logic should avoid using methods of this class, if
 * possible, and rely on methods provided by {@link BeeSweeper}.
 */
public final class GameField {

  private final FieldShape field;
  private int flowersAvailable;

  GameField(FieldShape shape, int numFlowers) {
    field = shape;
    flowersAvailable = numFlowers;
  }

  /** check if the coordination is included in the game
   *
   *
   * @param coordinate the given coordination
   * @return if the given coordinate is possible in the game
   */
  public boolean contains(Coordinate coordinate) {
    return field.contains(coordinate);
  }

  /** will give the coordination of each cell
   *
   * @param coordinate the coordination of the cell
   * @return the coordination of the cell
   */
  public Cell get(Coordinate coordinate) {
    return field.get(coordinate);
  }

  /** Returns coordinates of all bees on the game field.
   *
   * @return it gives the bees object, which contains the coordination of each bees cells
   */
  public Collection<Coordinate> getAllBeeCoordinates() {
    Collection<Coordinate> bees = new ArrayList<>();
    for (Coordinate c : field.getAllCoordinates()) {
      if (field.get(c).isBee()) {
        bees.add(c);
      }
    }
    return bees;
  }

  /** Returns coordinates of all marked cells on the game field.
   *
   * @return it gives the marked object, which contains the coordination of all marked cells.
   */
  public Collection<Coordinate> getAllMarkedCoordinates() {
    Collection<Coordinate> marked = new ArrayList<>();
    for (Coordinate c : field.getAllCoordinates()) {
      if (field.get(c).isMarked()) {
        marked.add(c);
      }
    }
    return marked;
  }

  /** Returns coordinates of all cells on the game field.
   *
   * @return alle the coordination of the game.
   */
  public Collection<Coordinate> getAllCoordinates() {
    return field.getAllCoordinates();
  }

  /** gives the number of column
   *
   * @return the number of column
   */
  public int getMaxColumn() {
    return field.getNumberOfColumns();
  }

  /** gives the number of row
   *
   * @return the number of row
   */

  public int getMaxRow() {
    return field.getNumberOfRows();
  }

  /**
   * Reveals the cell on the game field.
   *
   * @param coordinate Coordinate of the cell
   * @return the operation status of this method. {@link BeeSweeper.OperationStatus#SUCCESS} if
   *     action was successful. Otherwise, a corresponding error status.
   */
  public BeeSweeper.OperationStatus reveal(Coordinate coordinate) {
    if (!field.contains(coordinate)) {
      return BeeSweeper.OperationStatus.INDEX_OOB;
    }

    Cell newCell = get(coordinate);
    if (newCell.isBee() && !newCell.isRevealed()) {
      newCell.reveal();
      return BeeSweeper.OperationStatus.FAIL;
    } else {
      return BeeSweeper.OperationStatus.SUCCESS;
    }
  }

  /**
   * Marks the cell on the game field.
   *
   * @param coordinate Coordinate of the cell
   * @return the operation status of this method. {@link BeeSweeper.OperationStatus#SUCCESS} if
   *     action was successful. Otherwise, a corresponding error status.
   */
  public BeeSweeper.OperationStatus mark(Coordinate coordinate) {
    if (!field.contains(coordinate)) {
      return BeeSweeper.OperationStatus.INDEX_OOB;
    } else if (get(coordinate).isMarked()) {
      return BeeSweeper.OperationStatus.FAIL;
    } else if (flowersAvailable > 0) {
      flowersAvailable--;

      get(coordinate).mark();
    }
    return BeeSweeper.OperationStatus.SUCCESS;

  }

  /**
   * Unmarks the cell on the game field.
   *
   * @param coordinate coordinate of the cell
   * @return the operation status of this method. {@link BeeSweeper.OperationStatus#SUCCESS} if
   *     action was successful. Otherwise, a corresponding error status.
   */
  public BeeSweeper.OperationStatus unmark(Coordinate coordinate) {
    if (!field.contains(coordinate)) {
      return BeeSweeper.OperationStatus.INDEX_OOB;
    } else if (!get(coordinate).isMarked()) {
      return BeeSweeper.OperationStatus.FAIL;
    } else {
      flowersAvailable++;
      get(coordinate).unmark();
      return BeeSweeper.OperationStatus.SUCCESS;
    }
  }


  /** gives the available flowers
   *
   * @return the available flowers of the game.
   */
  public int getFlowersAvailable() {
    return flowersAvailable;
  }
}
