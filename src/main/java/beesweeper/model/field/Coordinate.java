package beesweeper.model.field;

import beesweeper.model.shape.FieldShape;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Coordinate ofa cell on a {@link FieldShape}.
 *
 * <p>Coordinates are comparable with each other, according to the following rule:
 *
 * <ol>
 *   <li>Coordinates in a lower row always come first.
 *   <li>Coordinates with the same row but lower column number come first.
 * </ol>
 */
public final class Coordinate implements Comparable<Coordinate> {
  private final int row;
  private final int column;

  private Coordinate(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /** Creates a new Coordinate.
   *
   * @param column the numbers of column in coordinate
   * @param row  the number of row in coordinate
   *
   * @return it gives a new coordinate contains the row and column
   */
  public static Coordinate of(int row, int column) {
    return new Coordinate(row, column);
  }

  /** get the number of rows.
   *
   *
   * @return the number of rows
   */
  public int getRow() {
    return row;
  }

  /** get the number of column.
   *
   *
   * @return the number of column
   */

  public int getColumn() {
    return column;
  }

  @Override
  public int compareTo(Coordinate coordinate) {
    int rowDiff = getRow() - coordinate.getRow();
    if (rowDiff != 0) {
      return rowDiff;
    }
    return getColumn() - coordinate.getColumn();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coordinate that = (Coordinate) o;
    return row == that.row && column == that.column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }

  @Override
  public String toString() {
    return "Coordinate{" + "row=" + row + ", column=" + column + '}';
  }

  /** Returns a list containing all the surrounding coordinates of the given coordinate.
   *
   * @param coordinate the coordinate to get the surrounding coordinates for
   *
   * @return a list of surrounding coordinates
   * */

  public List<Coordinate> getSurroundingCoordinates(Coordinate coordinate) {
    List<Coordinate> list = new ArrayList<>();
    int a = coordinate.getRow();
    int b = coordinate.getColumn();
    for (int row = -1; row <= 1; row++) {
      for (int col = -1; col <= 1; col++) {
        if (col == 0 && row == 0) {
          continue;
        }
        list.add(new Coordinate(a + row, b + col));
      }
    }
    return list;

  }
}


