package beesweeper.model.shape;

import beesweeper.model.field.Coordinate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * CoordinateGenerator with a uniform random distribution across the coordinates of a given shape.
 */
public class RandomCoordinateGenerator implements CoordinateGenerator {

  private final Random randomNumberGenerator;

  /** generate the new random coordinate.
   *
   */
  public RandomCoordinateGenerator() {
    randomNumberGenerator = new Random();
  }

  /** generate the random coordinate
   *
   * @param seed generate a new random coordinate
   */
  public RandomCoordinateGenerator(int seed) {
    randomNumberGenerator = new Random(seed);
  }

  /** Generate a collection of random coordinates. */
  @Override
  public Collection<Coordinate> getCoordinates(int n, FieldShape shape) {
    final List<Coordinate> allCoordinates = shape.getAllCoordinates();
    if (n > allCoordinates.size()) {
      throw new IllegalArgumentException(
          "Asked to sample " + n + " coordinates, but shape only has " + allCoordinates.size());
    }

    Collections.shuffle(allCoordinates, randomNumberGenerator);
    return allCoordinates.subList(0, n);
  }
}
