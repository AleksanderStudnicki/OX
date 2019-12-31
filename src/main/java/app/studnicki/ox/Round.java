package app.studnicki.ox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

class Round {
  private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  Board board;

  /**
   * Constructor.
   *
   * @param dimension Integer value that defines game board size
   *                  (square: dimension x dimension)and range of id fields (0..(dimension^2 - 1)).
   */
  Round(int dimension) {
    if (dimension < Config.MINIMUM_DIMENSION) {
      throw new IllegalArgumentException("Board dimension cannot be lesser than "
          + Config.MINIMUM_DIMENSION + "!");
    }
    board = new Board(dimension);
  }

  void addObserver(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener("filledField", listener);
  }

  /**
   * Fill the field of game board with O or X value (Sign enum).
   *
   * @param id   Id of field within within range: 0..(dimension^2 - 1).
   * @param sign Naught or cross from Sign enum.
   */
  void setField(int id, Sign sign) throws ExistingFieldException, NotInBoardRangeException {
    board.setField(id, sign);
    propertyChangeSupport.firePropertyChange(
        new PropertyChangeEvent(this, "filledField", null, id));
  }
}
