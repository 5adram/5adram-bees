package beesweeper.model.field;

/**
 * Cell on a game field. Each cell can contain a bee or be empty. If a cell is empty, it has a
 * number of bees surrounding it on the game field. In addition, each cell can be revealed or
 * unrevealed (cloaked), and marked or unmarked.
 */
public final class Cell {
  private boolean isBee;
  private int numberOfBeesSurrounding;
  private boolean isRevealed;
  private boolean isMarked;

  /**
   * Creates a new cell.
   *
   * @param isBee                   true if the cell is a bee, false otherwise
   * @param numberOfBeesSurrounding the number of bees surrounding the cell
   */

  public Cell(boolean isBee, int numberOfBeesSurrounding) {
    this.isBee = isBee;
    this.numberOfBeesSurrounding = numberOfBeesSurrounding;
    this.isRevealed = false;
    this.isMarked = false;

  }

  /** Get the number of bees surrounding this cell.
   *
   *
   * @return gives a number between 0 and 8 representing the bees in the neighbors cells
   */
  public int getNumberOfBeesSurrounding() {

    return numberOfBeesSurrounding;
  }

  /**
   * Check if this cell contains a bee.
   *
   * @return true if this cell contains a bee, false otherwise
   */

  public boolean isBee() {
    return isBee;
  }

  /**
   * Check if this cell is revealed.
   *
   * @return true if this cell is revealed, false otherwise
   */

  public boolean isRevealed() {
    return isRevealed;
  }


  /**
   * Check if this cell is marked.
   *
   * @return true if this cell is marked, false otherwise
   */

  public boolean isMarked() {
    return isMarked;
  }

  /**
   * Set the number of bees surrounding this cell.
   *
   * @param numberOfBeesSurrounding the number of bees surrounding this cell
   */

  public void setNumberOfBeesSurrounding(int numberOfBeesSurrounding) {
    this.numberOfBeesSurrounding = numberOfBeesSurrounding;
  }

  /**
   * Mark this cell.
   *
   * @return true if the cell is marked successfully, false otherwise
   */

  public boolean mark() {
    return isMarked = true;
  }

  /**
   * Unmark this cell.
   *
   * @return true if the cell is unmarked successfully, false otherwise
   */

  public boolean unmark() {
    return isMarked = false;
  }

  /**
   * Reveal the cell if it is not a bee.
   */

  public void reveal() {
    isRevealed = true;
  }

  /**
   * Unreveal the cell.
   *
   * @return true if the cell is unrevealed successfully, false otherwise
   */

  public boolean unReveal() {
    return isRevealed = false;
  }
}
