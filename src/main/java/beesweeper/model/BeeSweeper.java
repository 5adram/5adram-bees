package beesweeper.model;

import beesweeper.model.field.Cell;
import beesweeper.model.field.Coordinate;
import beesweeper.model.field.GameField;
import beesweeper.model.field.GameFieldFactory;
import beesweeper.model.shape.FieldShape;
import beesweeper.model.shape.RandomCoordinateGenerator;
import beesweeper.model.shape.RectangularShapeFactory;

/** BeeSweeper game. Same rules as Minesweeper, but different setting. */
public class BeeSweeper {
  private final GameField playingField;
  private GameState gameState;


  /** Status of a game operation. */
  public enum OperationStatus {
    /** Status that represents the operation was executed successfully. */
    SUCCESS,
    /** Status that represents the operation reached a cell that is out of bounds. */
    INDEX_OOB,
    /**
     * Status that represents the operation could not be done for other reasons, e.g. trying to mark
     * a cell that has already been revealed.
     */
    FAIL
  }

  /**
   * For testing only. Use {@link #newCombGame(int, int)} and {@link #newRectangularGame(int, int,
   * int)} instead.
   */
  BeeSweeper(GameField playingField) {

    this.playingField = playingField;
    this.gameState = getGameState().create(playingField);
  }

  /**
   * Creates a new rectangular game of BeeSweeper with specified number of column,
   * rows and bees.
   *
   * @param columns number of columns in the game field
   * @param rows number of rows in the game field
   * @param numBees number of bees in the game field
   * @return a new BeeSweeper object representing the rectangular game
   */
  public static BeeSweeper newRectangularGame(int columns, int rows, int numBees) {
    RectangularShapeFactory shape = new RectangularShapeFactory(columns, rows);
    FieldShape a = shape.create();
    RandomCoordinateGenerator zufall = new RandomCoordinateGenerator();
    GameFieldFactory gameFieldFactory = new GameFieldFactory(shape, zufall);
    GameField gameField = gameFieldFactory.create(numBees, numBees);

    return new BeeSweeper(gameField);
  }

  /**
   * creates a new honeycombed game of BeeSweeper with the specified number of columns,
   * rows and bees.
   *
   * @param rows number of rows in honeycombed game
   * @param numBees number of bees in honeycombed game
   * @return a new honeycombed object representing the honeycombed game
   */
  public static BeeSweeper newCombGame(int rows, int numBees) {
    throw new UnsupportedOperationException();
  }

  /**
   * Reveals the cell at the given location.
   *
   * @param coordinate the coordinate of the cell to act on
   * @return the operation status of this method. {@link OperationStatus#SUCCESS} if action was
   *     successful. Otherwise, a corresponding error status.
   */
  public OperationStatus reveal(Coordinate coordinate) {
    if (!playingField.contains(coordinate)) {
      return OperationStatus.INDEX_OOB;

    }
    Cell cell = playingField.get(coordinate);

    if (cell.isBee()) {
      cell.reveal();
      gameState = gameState.with(GameState.GameStatus.LOSE);
      return OperationStatus.SUCCESS;
    } else if (cell.isRevealed()) {
      System.out.println("Cell is already revealed");
      return OperationStatus.FAIL;
    } else {
      cell.reveal();

      // Check if all non-bee cells are revealed
      boolean allNonBeeCellsRevealed = true;

      for (Coordinate coord : playingField.getAllCoordinates()) {
        Cell currentCell = playingField.get(coord);
        if (!currentCell.isBee() && !currentCell.isRevealed()) {
          allNonBeeCellsRevealed = false;
          break;
        }
      }

      if (allNonBeeCellsRevealed) {
        gameState = gameState.with(GameState.GameStatus.WIN);
      }

      return OperationStatus.SUCCESS;
    }
  }

  /**
   * Marks the cell at the given location.
   *
   * @param coordinate the coordinate of the cell to act on
   * @return the operation status of this method. {@link OperationStatus#SUCCESS} if action was
   *     successful. Otherwise, a corresponding error status.
   */
  public OperationStatus mark(Coordinate coordinate) {
    if (!playingField.contains(coordinate)) {
      return OperationStatus.INDEX_OOB;
    }

    Cell cell = playingField.get(coordinate);

    if (cell.isRevealed()) {
      return OperationStatus.FAIL;
    } else if (cell.isMarked()) {
      return OperationStatus.FAIL;
    } else {
      playingField.mark(coordinate);

      // Check if all marked cells are bees
      boolean allMarkedCellsAreBees = true;
      boolean allOtherCellsRevealed = true;
      for (Coordinate beeCoordinate : playingField.getAllBeeCoordinates()) {
        Cell beeCell = playingField.get(beeCoordinate);

        if (!beeCell.isMarked() && !beeCell.isRevealed()) {
          allOtherCellsRevealed = false;
          break;
        }

        if (!beeCell.isMarked()) {
          allMarkedCellsAreBees = false;
          break;
        }
      }

      if (allMarkedCellsAreBees && allOtherCellsRevealed) {
        gameState = gameState.with(GameState.GameStatus.WIN);
      }

      return OperationStatus.SUCCESS;
    }
  }

  /**
   * Unmarks the cell at the given location.
   *
   * @param coordinate the coordinate of the cell to act on
   * @return the operation status of this method. {@link OperationStatus#SUCCESS} if action was
   *     successful. Otherwise, a corresponding error status.
   */
  public OperationStatus unmark(Coordinate coordinate) {
    if (!playingField.contains(coordinate)) {
      return OperationStatus.INDEX_OOB;
    }
    Cell cell = playingField.get(coordinate);

    if (!cell.isMarked()) {
      return OperationStatus.FAIL;
    } else {
      playingField.unmark(coordinate);
      return OperationStatus.SUCCESS;
    }
  }

  /** Gets the current GameState.
   *
   *
   * @return   return the latest state of the game.
   */
  public final GameState getGameState() {
    return gameState;
  }
}
