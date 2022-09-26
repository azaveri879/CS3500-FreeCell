package cs3500.freecell.model.hw04;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;


/**
 * Represents a FreecellModel in which multiple card moves
 * are allowed at a time depending on the availability of
 * empty open and cascade piles.
 */
public class MultiFreecellModel extends AbstractFreecellModel {

  /**
   * Formula for maximum cards that can be moved at a time
   * depending on number of open cascade and open piles.
   * @return Integer
   */
  private int maxCardsToMove() {
    int numOpenOpenPiles = 0;
    int numEmptyCascades = 0;

    for (int j = 0; j < openPiles.length; j++) {
      if (openPiles[j] == null) {
        numOpenOpenPiles++;
      }
    }

    for (int i = 0; i < cascadePiles.size();i++) {
      if (cascadePiles.get(i).isEmpty()) {
        numEmptyCascades++;
      }
    }
    return (int)((numOpenOpenPiles + 1) * Math.pow(2,numEmptyCascades));

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {


    if (!started) {
      throw new IllegalStateException("Game has not started!");
    }

    ICard src;
    switch (source) {
      case FOUNDATION:
        throw new IllegalArgumentException("Cannot remove from Foundation Pile!");
      case CASCADE:
        if (pileNumber < 0 || this.cascadePiles.size() - 1 < pileNumber ||
            cardIndex < 0 || this.cascadePiles.get(pileNumber).size() - 1 < cardIndex) {
          throw new IllegalArgumentException("Index Out of Bounds!");
        }
        else if (!cascadePiles.get(pileNumber).isEmpty() &&
            cascadePiles.get(pileNumber).get(cardIndex).equals(
                cascadePiles.get(pileNumber).get(this.getNumCardsInCascadePile(pileNumber) - 1))) {

          src = cascadePiles.get(pileNumber).get(cardIndex);
          movePileDest(src, destination,destPileNumber);
          cascadePiles.get(pileNumber).remove(cardIndex);
        }
        else if (!cascadePiles.get(pileNumber).isEmpty() &&
            !cascadePiles.get(pileNumber).get(cardIndex).equals(
                cascadePiles.get(pileNumber).get(this.getNumCardsInCascadePile(pileNumber) - 1))) {


          int n = cascadePiles.get(pileNumber).size() - cardIndex;
          if (n <= this.maxCardsToMove() && destination == PileType.CASCADE) {
            for (int j = cardIndex; j < cascadePiles.get(pileNumber).size(); j++) {
              movePileDest(cascadePiles.get(pileNumber).get(j), destination, destPileNumber);
            }
            while ( cardIndex < cascadePiles.get(pileNumber).size()) {
              cascadePiles.get(pileNumber).remove(cardIndex);
            }
          }
          else {
            throw new IllegalArgumentException("MultiMove not possible!");
          }
        }
        else {
          throw new IllegalArgumentException("Cannot remove from empty cascade pile and" +
              " Can only move from top of Cascade Pile");
        }
        break;
      case OPEN:
        if (cardIndex < 0 || this.openPiles.length - 1 < cardIndex) {
          throw new IllegalArgumentException("Index Out of Bounds!");
        }

        if (openPiles[pileNumber] != null) {

          src = openPiles[pileNumber];
          movePileDest(src, destination, destPileNumber);
          openPiles[pileNumber] = null;
        }
        else {
          throw new IllegalArgumentException("Cannot remove from empty open pile!");
        }
        break;
      default:
        throw new IllegalArgumentException("Should not get here!");
    }
  }

}
