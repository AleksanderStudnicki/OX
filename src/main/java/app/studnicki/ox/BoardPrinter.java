package app.studnicki.ox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BoardPrinter implements PropertyChangeListener {
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    System.out.println(evt.getPropertyName());
    if (evt.getPropertyName().equals("filledField")) {
      System.out.println("aaa");
    }
  }
}
