package cs3500.freecell.model.hw02;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw04.AbstractFreecellModel;

/**
 * Models a FreeCell Game and its Methods.
 */
public class SimpleFreecellModel extends AbstractFreecellModel {

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

        if (!cascadePiles.get(pileNumber).isEmpty() &&
            cascadePiles.get(pileNumber).get(cardIndex).equals(
                cascadePiles.get(pileNumber).get(this.getNumCardsInCascadePile(pileNumber) - 1))) {

          src = cascadePiles.get(pileNumber).get(cardIndex);
          movePileDest(src, destination,destPileNumber);
          cascadePiles.get(pileNumber).remove(cardIndex);
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
