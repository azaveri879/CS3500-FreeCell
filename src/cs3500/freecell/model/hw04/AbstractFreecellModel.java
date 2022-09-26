package cs3500.freecell.model.hw04;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardVal;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.Suit;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an abstract freecell model which can be either a
 * SimpleModel or a MultiModel, but is open to extension from
 * this class.
 */
public abstract class AbstractFreecellModel implements FreecellModel<ICard> {

  protected static List<ICard> fullDeck;
  protected ArrayList<ArrayList<ICard>> cascadePiles;
  protected ArrayList<ArrayList<ICard>> foundationPiles;
  protected  ICard[] openPiles;
  protected boolean started;

  /**
   * Constructs a ABSTRACTFreeCellModel with
   * a full deck, open,foundation, and cascade piles.
   */
  public AbstractFreecellModel() {
    fullDeck = new ArrayList<ICard>();
    cascadePiles = new ArrayList<ArrayList<ICard>>();
    foundationPiles = new ArrayList<ArrayList<ICard>>();
    started = false;
  }


  @Override
  public List<ICard> getDeck() {
    if (fullDeck.size() == 52) {
      return fullDeck;
    }

    else {
      for (Suit s : Suit.values()) {
        for (CardVal v : CardVal.values()) {
          fullDeck.add(new Card(v, s));
        }
      }
    }
    return fullDeck;
  }

  @Override
  public void startGame(List deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {

    //checking for duplicates
    for (int i = 0; i < deck.size(); i++) {
      for (int j = i + 1; j < deck.size(); j++) {
        if (deck.get(i).equals(deck.get(j))) {
          throw new IllegalArgumentException("No Duplicates!");
        }
      }
    }

    //checking for invalid cards
    for (int i = 0; i < deck.size(); i++) {
      if (!(deck.get(i) instanceof Card)) {
        throw new IllegalArgumentException("All objects in decks must be Cards!");
      }
    }

    //check for 52 cards
    if (deck.size() != 52) {
      throw new IllegalArgumentException("Deck size must be 52!");
    }

    //less than 4 cascade piles
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("Cannot have less than 4 cascade piles!");
    }
    //less than 1 open pile
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("Cannot have less than 1 open pile!");
    }

    List<ICard> copydeck = new ArrayList(deck);
    if (shuffle) {
      Collections.shuffle(copydeck);
    }

    foundationPiles = new ArrayList<ArrayList<ICard>>();
    foundationPiles.add(new ArrayList<ICard>());
    foundationPiles.add(new ArrayList<ICard>());
    foundationPiles.add(new ArrayList<ICard>());
    foundationPiles.add(new ArrayList<ICard>());

    createCascadePile(copydeck, numCascadePiles);
    openPiles = new Card[numOpenPiles];
    started = true;
  }

  /**
   * Makes the Cascade Pile of a FreeCellGame.
   * @param deck    the deck of cards
   * @param numCascadePiles number of cascade piles needed
   */
  protected void createCascadePile(List<ICard> deck, int numCascadePiles) {
    ArrayList<ArrayList<ICard>> temp = new ArrayList<ArrayList<ICard>>();
    for (int i = 0; i < numCascadePiles; i++) {
      temp.add(new ArrayList<ICard>());
    }
    for (int i = 0; i < deck.size(); i++) {
      temp.get(i % numCascadePiles).add(deck.get(i));
    }
    cascadePiles = temp;
  }


  /**
   * Moves a given Card to its destination based on the piletype of the destination.
   * @param src the source card we want to move.
   * @param destination the pile we want to move src to.
   * @param destPileNumber the index of the pile we want to move src to.
   * @throws IllegalArgumentException if move is invalid.
   */
  protected void movePileDest(ICard src, PileType destination, int destPileNumber) throws
      IllegalArgumentException {
    switch (destination) {
      case FOUNDATION:
        if (destPileNumber < 0 || this.foundationPiles.size() - 1 < destPileNumber) {
          throw new IllegalArgumentException("Index Out of Bounds!");
        }

        if (foundationPiles.get(destPileNumber).isEmpty() &&
            src.getVal() == CardVal.ACE) {
          foundationPiles.get(destPileNumber).add(src);
        }
        else if (!foundationPiles.get(destPileNumber).isEmpty() &&
            foundationPiles.get(destPileNumber).get(0).getVal() == CardVal.ACE &&
            foundationPiles.get(destPileNumber).get(0).getSuit().toString()
                == src.getSuit().toString()
            && cardGreater(foundationPiles,destPileNumber,src)) {
          foundationPiles.get(destPileNumber).add(src);
        }
        else {

          throw new IllegalArgumentException("Invalid move to foundation!");

        }
        break;
      case CASCADE:
        if (destPileNumber < 0 || this.cascadePiles.size() - 1 < destPileNumber) {
          throw new IllegalArgumentException("Index Out of Bounds!");
        }

        if (cascadePiles.get(destPileNumber).isEmpty()) {
          cascadePiles.get(destPileNumber).add(src);
        }
        else if (!cascadePiles.get(destPileNumber).isEmpty() &&
            !cascadePiles.get(destPileNumber).get(
                cascadePiles.get(destPileNumber).size() - 1).getSuit().getColor().equals(
                src.getSuit().getColor())
            &&  cardGreater(cascadePiles,destPileNumber,src)) {
          cascadePiles.get(destPileNumber).add(src);
        }
        else {
          throw new IllegalArgumentException("Invalid move to cascade!");
        }
        break;
      case OPEN:
        if (destPileNumber < 0 || this.openPiles.length - 1 < destPileNumber) {
          throw new IllegalArgumentException("Index Out of Bounds!");
        }

        if (openPiles[destPileNumber] == null) {
          openPiles[destPileNumber] = src;
        }
        else {
          throw new IllegalArgumentException("This open pile is full!");
        }
        break;
      default: throw new IllegalArgumentException("Should not get here!");
    }
  }


  /**
   * Checks if the current src Card is equal to 1 greater than the last Card
   * in the given Pile.
   * @param piles Pile we are checking.
   * @param destPileNumber Index of Pile we are checking
   * @param src Card to compare
   * @return boolean
   */
  protected boolean cardGreater(ArrayList<ArrayList<ICard>> piles,
      int destPileNumber,ICard src) {
    if (piles.equals(cascadePiles)) {
      return piles.get(destPileNumber).get(piles.get(destPileNumber).size() - 1).getVal()
          .getVal() - 1 == src.getVal().getVal();
    }
    else {
      return piles.get(destPileNumber).get(piles.get(destPileNumber).size() - 1).getVal()
          .getVal() + 1 == src.getVal().getVal();
    }
  }

  @Override
  public boolean isGameOver() {
    return foundationPiles.get(0).size() == 13 &&
        foundationPiles.get(1).size() == 13 &&
        foundationPiles.get(2).size() == 13 &&
        foundationPiles.get(3).size() == 13;
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started!");
    }
    if (index < 0 || index > foundationPiles.size() - 1) {
      throw new IllegalArgumentException("Index out of bounds!");
    }

    return foundationPiles.get(index).size();
  }

  @Override
  public int getNumCascadePiles() {
    if (!started) {
      return -1;
    }
    else {
      return cascadePiles.size();
    }
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started!");
    }
    if (index < 0 || index > cascadePiles.size() - 1) {
      throw new IllegalArgumentException("Index out of bounds!");
    }

    return cascadePiles.get(index).size();
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {

    if (!started) {
      throw new IllegalStateException("Game has not started!");
    }
    if (index < 0 || index > openPiles.length - 1) {
      throw new IllegalArgumentException("Index out of bounds!");
    }

    if (openPiles[index] != null) {
      return 1;
    }
    return 0;
  }

  @Override
  public int getNumOpenPiles() {
    if (!started) {
      return -1;
    }
    else {
      return openPiles.length;
    }
  }

  @Override
  public ICard getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started!");
    }
    if (pileIndex < 0 || pileIndex > foundationPiles.size() - 1
        || cardIndex < 0 || cardIndex > foundationPiles.get(pileIndex).size() - 1) {
      throw new IllegalArgumentException("Index out of bounds!");
    }

    return foundationPiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public ICard getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started!");
    }
    if (pileIndex < 0 || pileIndex > cascadePiles.size() - 1
        || cardIndex < 0 || cardIndex > cascadePiles.get(pileIndex).size() - 1) {
      throw new IllegalArgumentException("Index out of bounds!");
    }

    return cascadePiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public ICard getOpenCardAt(int pileIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started!");
    }
    if (pileIndex < 0 || pileIndex > openPiles.length - 1) {
      throw new IllegalArgumentException("Index out of bounds!");
    }
    return openPiles[pileIndex];
  }

}
