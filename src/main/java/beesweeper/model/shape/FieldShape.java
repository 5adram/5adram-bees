package beesweeper.model.shape;

import beesweeper.model.field.Cell;
import beesweeper.model.field.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A field of a given shape. Consists of rows and columns. Each row can have an arbitrary amount of
 * columns.
 */
public class FieldShape {
  private int numberOfRows;
  private int numberOfColumns;
  private Map<Coordinate, Cell> fieldShape;
  private List<Coordinate> allCoordinates;


  /**
   * Constructs a new FieldShape with the specified number of rows and columns.
   *
   * @param numberOfRows    the number of rows in the field
   * @param numberOfColumns the number of columns in the field
   */

  public FieldShape(int numberOfRows, int numberOfColumns) {
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    this.fieldShape = new HashMap<>();
    this.allCoordinates = new ArrayList<>();

    for (int col = 0; col < numberOfColumns; col++) {
      for (int row = 0; row < numberOfRows; row++) {
        Coordinate coordinate = Coordinate.of(col, row);
        fieldShape.put(coordinate, new Cell(false, 0));
        allCoordinates.add(coordinate);
      }
    }
  }

  /** Returns the maximum row amount of any column in the shape.
   *
   * @return  the number of rows in the shape
   */
  public int getNumberOfRows() {
    return this.numberOfRows;
  }

  /** Returns the maximum column amount of any row in the shape.
   *
   * @return  the number of columns of the shape
   */
  public int getNumberOfColumns() {
    return this.numberOfColumns;
  }

  /**
   * Returns the coordinates of all cells in the shape. Coordinates may not be sorted.
   *
   * @return the coordinates of all cells in this shape
   */
  public List<Coordinate> getAllCoordinates() {
    return allCoordinates;
  }

  /**
   * Returns whether the given coordinate exists in this shape.
   *
   * @param coordinate coordinate to check
   * @return <code>true</code> if the coordinate exists, <code>false</code> otherwise
   */
  public boolean contains(Coordinate coordinate) {
    return fieldShape.containsKey(coordinate);
  }

  /**
   * Replaces the cell at the given coordinate in the shape with the given cell.
   *
   * @param coordinate coordinate to replace cell at
   * @param newCell new cell to replace old cell with
   * @throws IndexOutOfBoundsException if the given coordinate is not contained in this shape
   * @see #contains(Coordinate)
   */
  public void replace(Coordinate coordinate, Cell newCell) {
    //throw new UnsupportedOperationException();
    if (!contains(coordinate)) {
      throw new IndexOutOfBoundsException();
    } else {
      fieldShape.put(coordinate, newCell);
    }
  }

  /**
   * Returns the cell at the given coordinate in this shape.
   *
   * @param cellCoordinate coordinate to return cell for
   * @return the cell at the given coordinate in this shape
   * @throws IndexOutOfBoundsException if the coordinate is not in this shape
   */
  public Cell get(Coordinate cellCoordinate) {
    if (!getAllCoordinates().contains(cellCoordinate)) {
      throw new IndexOutOfBoundsException();
    } else {
      return fieldShape.get(cellCoordinate);
    }
  }
}
