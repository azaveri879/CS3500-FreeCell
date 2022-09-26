package cs3500.freecell.model;

import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiFreecellModel;

/**
 * Represents a creator of a Freecell game which
 * can be either a multi-move or simple game.
 */
public class FreecellModelCreator {

  /**
   * Represents the two types of games. A SINGLEMOVE game only allows a player to move
   * one cascade card at a time whereas a MULTIMOVE game allows for multiple cascade
   * cards to be moved in one turn.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Forms a FreecellModel based on the type of game inputted.
   * @param g any GameType in the GameType enum
   * @return FreecellModel
   */
  public static FreecellModel create(GameType g) {

    if (g.equals(GameType.MULTIMOVE)) {
      return new MultiFreecellModel();
    }
    else if (g.equals(GameType.SINGLEMOVE)) {
      return new SimpleFreecellModel();
    }
    else {
      throw new IllegalArgumentException("Not a Valid GameType!");
    }

  }
}
