package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;

import java.io.IOException;

/**
 * Represents a textual representation of a FreeCell game.
 * @param <K> any type
 */
public class FreecellTextView<K> implements FreecellView {
  private final FreecellModel<K> model;
  private Appendable ap;

  /**
   * Constructs a textual representation of a FreeCell game.
   * @param model the FreeCellModel game
   */
  public FreecellTextView(FreecellModel<K> model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
  }

  /**
   * Constructs a textual representation of a FreeCell game and adds it to
   * the appendable output.
   * @param model the FreeCellModel game
   * @param ap Appendable output
   */
  public FreecellTextView(FreecellModel<K> model, Appendable ap) {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.ap = ap;
  }


  @Override
  public String toString() {
    //use observer methods on model to construct the string
    StringBuilder str = new StringBuilder();
    try {
      str.append(foundationPilesToString());
      str.append("\n");
      str.append(openPilesToString());
      str.append("\n");
      str.append(cascadePilesToString());
    } catch (IllegalStateException e) {
      return "";
    }
    return str.toString();
  }

  /**
   * Converts a FoundationPile to a String.
   * @return String
   */
  protected String foundationPilesToString() {
    String temp = "";
    for (int i = 0; i < 4; i++) {
      temp += "F" + Integer.toString(i + 1) + ":";
      for (int j = 0; j < model.getNumCardsInFoundationPile(i);j++) {
        if (j != model.getNumCardsInFoundationPile(i) - 1) {
          temp += " " + model.getFoundationCardAt(i, j).toString() + ",";
        }
        else {
          temp += " " + model.getFoundationCardAt(i, j).toString();
        }
      }
      if (i !=  3) {
        temp += "\n";
      }
    }
    return temp;
  }

  /**
   * Converts a CascadePile to a String.
   * @return String
   */
  protected String cascadePilesToString() {
    String temp = "";
    for (int i = 0; i < model.getNumCascadePiles(); i++) {
      temp += "C" + Integer.toString(i + 1) + ":";
      for (int j = 0; j < model.getNumCardsInCascadePile(i); j++) {
        if (j != model.getNumCardsInCascadePile(i) - 1) {
          temp += " " + model.getCascadeCardAt(i, j).toString() + ",";
        }
        else {
          temp += " " + model.getCascadeCardAt(i, j).toString();
        }
      }
      if (i != model.getNumCascadePiles() - 1) {
        temp += "\n";
      }
    }
    return temp;
  }

  /**
   * Converts a OpenPile to a String.
   * @return String
   */
  protected String openPilesToString() {
    String temp = "";
    for (int i = 0; i < model.getNumOpenPiles(); i++) {
      temp += "O" + (i + 1) + ":";
      if (model.getOpenCardAt(i) != null) {
        if (i != model.getNumOpenPiles() - 1) {
          temp += " " + model.getOpenCardAt(i) + "\n";
        }
        else {
          temp += " " + model.getOpenCardAt(i);
        }
      }
      else {
        if (i != model.getNumOpenPiles() - 1) {
          temp += "\n";
        }
        else {
          temp += "";
        }
      }

    }
    return temp;
  }


  @Override
  public void renderBoard() throws IOException {
    try {
      ap.append(this.toString() + "\n");
    }
    catch (IOException e) {
      throw new IllegalStateException("Could not render board");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    ap.append(message);
  }


}
