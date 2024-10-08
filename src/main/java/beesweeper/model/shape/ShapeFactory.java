package beesweeper.model.shape;

/** Factory to build a certain {@link FieldShape}. */
public interface ShapeFactory {

  /** Creates a new {@link FieldShape}.
   *
   * @return the new shape
   */
  FieldShape create();
}
