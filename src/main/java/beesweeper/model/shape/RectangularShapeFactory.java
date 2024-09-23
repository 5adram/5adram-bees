package beesweeper.model.shape;

import beesweeper.model.field.Cell;
import java.awt.geom.RectangularShape;
import java.util.List;

/** {@link ShapeFactory} for a rectangular game field. */
public class RectangularShapeFactory implements ShapeFactory {

  private int columnAmount;
  private int rowAmount;

  /**
   * Creates a new RectangularShapeFactory. The created factory will create {@link FieldShape}s of
   * rectangles with the given columns and rows.
   *
   * @param columns intended columns of the rectangle (width).
   * @param rows intended rows of the rectangle (height).
   * @throws IllegalArgumentException if an invalid width or height is given
   */
  public RectangularShapeFactory(final int columns, final int rows) {
    if (columns <= 0 || rows <= 0) {
      throw new IllegalArgumentException("Invalid rectangle dimensions: " + columns + "x" + rows);
    }
    columnAmount = columns;
    rowAmount = rows;
  }

  /**
   * Creates a new rectangular field shape based on the configured column and row dimensions.
   *
   * @return a new rectangular field shape with the specified number of columns and rows
   */
  @Override
  public FieldShape create() {

    return new FieldShape(columnAmount, rowAmount);
  }
}
