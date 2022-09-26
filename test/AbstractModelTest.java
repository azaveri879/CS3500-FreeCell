
import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardVal;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tests for the FreeCellModel methods.
 */
public abstract class AbstractModelTest {
  FreecellModel game;

  FreecellModel game2;
  List<ICard> deck;
  FreecellModel game3;
  List<ICard> d2;

  @Before
  public void setUp() {
    game = makeModel();
    game2 = makeModel();
    deck = game2.getDeck();
    game3 = makeModel();
    d2 = game3.getDeck();
  }

  protected abstract FreecellModel makeModel();

  ICard aceSpades = new Card(CardVal.ACE, Suit.SPADES);
  ICard fiveDiamonds = new Card(CardVal.FIVE, Suit.DIAMONDS);
  ICard tenClubs = new Card(CardVal.TEN, Suit.CLUBS);

  /**
   * Represents all tests for SimpleModels.
   */
  public static class SimpleModelTests extends AbstractModelTest {

    @Override
    protected FreecellModel makeModel() {
      return new SimpleFreecellModel();
    }
  }


  @Test
  public void testGetDeck() {
    assertEquals(52,game.getDeck().size());

  }

  //TESTS FOR STARTGAME----------------------------------------------
  @Test
  public void testStartGameInitialDecks1() {
    List<ICard> g = game.getDeck();
    game.startGame(g,5,2,false);
    assertEquals(g,deck);

    game.startGame(g,5,1,false);
    assertEquals(g,deck);
  }

  @Test
  public void testFoundationPiles() {
    List<ICard> g = game.getDeck();
    game.startGame(g,8,4,true);
    assertEquals(0,game.getNumCardsInFoundationPile(3));
    assertEquals(0,game.getNumCardsInFoundationPile(2));
    assertEquals(0,game.getNumCardsInFoundationPile(1));
    assertEquals(0,game.getNumCardsInFoundationPile(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testGameNotStarted() {
    assertEquals(false,game.getFoundationCardAt(1,0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameStartedNotDealt() {
    List<ICard> g = game.getDeck();
    game.startGame(g,8,4,true);
    game.getFoundationCardAt(1,0);
  }

  @Test
  public void testCascadePiles() {
    List<ICard> g = game.getDeck();
    game.startGame(g,8,4,false);

    assertEquals(g.get(8), game.getCascadeCardAt(0,1));
    assertEquals(g.get(16), game.getCascadeCardAt(0,2));
    assertEquals(g.get(0), game.getCascadeCardAt(0,0));
    assertEquals(g.get(1), game.getCascadeCardAt(1,0));
    assertEquals(g.get(7), game.getCascadeCardAt(7,0));
  }

  @Test
  public void testOpenPile() {
    List<ICard> g = game.getDeck();
    game.startGame(g,8,4,true);

    assertEquals(null, game.getOpenCardAt(1));
    assertEquals(null, game.getOpenCardAt(2));
  }


  //tests length of not 52
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptions1() {

    game.startGame(new ArrayList<ICard>(Arrays.asList(aceSpades, fiveDiamonds)),
        1, 1, true);
  }

  //tests duplicates
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptions2() {

    List<ICard> g = game.getDeck();
    g.remove(51);
    g.add(tenClubs);

    game.startGame(g, 1, 1, true);
  }

  //tests non-card decks
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptions3() {
    game.startGame(new ArrayList<Integer>(Arrays.asList(1,2,3)),
        1, 2,false );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartPiles() {
    List<ICard> g = game.getDeck();
    game.startGame(g,8,4,true);

    game.getOpenCardAt(5);
    game.getOpenCardAt(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartPiles1() {
    List<ICard> g = game.getDeck();
    game.startGame(g,1,2,true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartPiles2() {
    List<ICard> g = game.getDeck();
    game.startGame(g,4,0,true);
  }

  //TESTS FOR MOVE----------------------------------------------

  @Test //tests Move and MoveDest Methods
  public void testMoveAndMoveDest() {
    game3.startGame(d2,8,4,false);


    //Move From Cascade to Open
    assertEquals(null,game3.getOpenCardAt(2));
    ICard c = (ICard) game3.getCascadeCardAt(0,6);
    game3.move(PileType.CASCADE,0,6,PileType.OPEN,2);
    assertEquals(c,game3.getOpenCardAt(2));
    assertEquals(6,game3.getNumCardsInCascadePile(0));


    //Move From Open to Cascade
    System.out.println(game3.getCascadeCardAt(4,5));
    game3.move(PileType.CASCADE,4,5,PileType.OPEN,0);
    System.out.println(game3.getOpenCardAt(2));
    game3.move(PileType.OPEN, 2,0,PileType.CASCADE,4);
    assertEquals(null,game3.getOpenCardAt(2));


    //Move From Cascade to Foundation

    game3.move(PileType.CASCADE, 7,5,PileType.OPEN,3);
    game3.move(PileType.CASCADE,7,4,PileType.FOUNDATION,1);
    assertEquals("A♠",game3.getFoundationCardAt(1,0).toString());
    assertEquals(4,game3.getNumCardsInCascadePile(7));


    game3.move(PileType.CASCADE,0,5,PileType.FOUNDATION,1);
    assertEquals("2♠", game3.getFoundationCardAt(1,1).toString());

    //Move From Open to Foundation
    game3.move(PileType.CASCADE, 1,6,PileType.OPEN,1);
    game3.move(PileType.CASCADE,1,5,PileType.OPEN,2);
    game3.move(PileType.OPEN,2,0,PileType.FOUNDATION,1);
    assertEquals("3♠", game3.getFoundationCardAt(1,2).toString());

    //Move Cascade to Cascade
    game.startGame(d2,8,4,false);
    game.move(PileType.CASCADE, 4,5,PileType.OPEN,1);
    game.move(PileType.CASCADE,0,6,PileType.CASCADE,4);

    //Move to empty Cascade
    game3.move(PileType.CASCADE,7,3,PileType.CASCADE,5);
    game3.move(PileType.CASCADE,7,2,PileType.CASCADE,2);
    game3.move(PileType.CASCADE,7,1,PileType.OPEN,2);
    game3.move(PileType.OPEN,0,0,PileType.CASCADE,0);
    game3.move(PileType.CASCADE,7,0,PileType.OPEN,0);
    assertEquals(0,game3.getNumCardsInCascadePile(7));
    game3.move(PileType.CASCADE,2,7,PileType.CASCADE,7);
    game3.move(PileType.CASCADE,4,5,PileType.CASCADE,7);
    assertEquals(2,game3.getNumCardsInCascadePile(7));



  }

  @Test(expected = IllegalStateException.class)
  public void testMoveExceptions() {
    game.move(PileType.CASCADE,1,
        2,PileType.FOUNDATION,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveArgumentExceptions1() {
    game.startGame(game.getDeck(),7,4,true);
    game.move(PileType.FOUNDATION,1,
        2,PileType.CASCADE,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveArgumentExceptions2() {
    //Moving two cards to the same Open Pile
    game3.startGame(d2,8,4,false);
    game3.move(PileType.CASCADE,0,6,PileType.OPEN,2);
    game3.move(PileType.CASCADE,1,6,PileType.OPEN,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveArgumentExceptions3() {
    game.startGame(d2,52,4,false);
    //removing from an empty cascade pile
    game.move(PileType.CASCADE, 0,0,PileType.OPEN,2);
    game.move(PileType.CASCADE, 0,0,PileType.OPEN,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveArgumentExceptions4() {
    //Removing from an empty open pile
    game.startGame(d2,52,4,false);
    game.move(PileType.OPEN, 0,0,PileType.FOUNDATION,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDestArgumentExceptions1() {
    game.startGame(d2,8,4,false);
    //Invalid move to empty foundation
    game.move(PileType.CASCADE,2,6,PileType.FOUNDATION,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDestArgumentExceptions2() {
    game.startGame(d2,8,4,false);
    //invalid move to foundation
    game.move(PileType.CASCADE,0,0,PileType.FOUNDATION,0);
    game.move(PileType.CASCADE, 0,1, PileType.FOUNDATION,0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDestArgumentExceptions3() {
    game.startGame(d2,8,4,false);
    //invalid move to foundation
    game.move(PileType.CASCADE,0,0,PileType.FOUNDATION,0);
    game.move(PileType.CASCADE, 0,5, PileType.FOUNDATION,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDestArgumentExceptions4() {
    game.startGame(d2,8,4,false);
    //invalid move cascade-- same color
    game.move(PileType.CASCADE,0,6,PileType.CASCADE,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDestArgumentExceptions5() {
    game.startGame(d2,8,4,false);
    //invalid move cascade-- different color
    game.move(PileType.CASCADE,7,5,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,4,PileType.OPEN,1);
    game.move(PileType.CASCADE,7,3,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,2,PileType.OPEN,3);


    game.move(PileType.CASCADE,0,6,PileType.CASCADE,7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDestArgumentExceptions6() {
    game.startGame(d2,8,4,false);
    //invalid move cascade-- different color
    game.move(PileType.CASCADE,7,5,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,4,PileType.OPEN,1);
    game.move(PileType.CASCADE,7,3,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,2,PileType.OPEN,3);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);

  }

  @Test
  public void testIsGameOver() {
    game.startGame(d2, 52, 4, false);

    assertEquals(false, game.isGameOver());

    for (int i = 0; i < game.getNumCascadePiles() / 4; i++) {
      game.move(PileType.CASCADE, i, 0, PileType.FOUNDATION,0);
    }

    for (int i = game.getNumCascadePiles() / 4 ; i < game.getNumCascadePiles() / 2; i++ ) {
      game.move(PileType.CASCADE, i, 0, PileType.FOUNDATION,1);
    }


    for (int i = game.getNumCascadePiles() / 2 ; i < game.getNumCascadePiles() - 13; i++ ) {
      game.move(PileType.CASCADE, i, 0, PileType.FOUNDATION,2);
    }

    for (int i = 39 ; i < game.getNumCascadePiles() ; i++ ) {
      game.move(PileType.CASCADE, i, 0, PileType.FOUNDATION,3);
    }


    assertEquals(true, game.isGameOver());
  }

  @Test
  public void testNumCardsCascadePiles() {
    game.startGame(d2, 8, 4, false);

    assertEquals(7,game.getNumCardsInCascadePile(0));
    assertEquals(6,game.getNumCardsInCascadePile(7));

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);

    assertEquals(6,game.getNumCardsInCascadePile(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testNumCascadeCardException() {
    game.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumCascadeCardException1() {
    game.startGame(d2, 8, 4, false);

    game.getNumCardsInCascadePile(8);
  }

  @Test
  public void testNumCascadePiles() {
    game.startGame(d2, 8, 4, false);
    assertEquals(8,game.getNumCascadePiles());

    game.startGame(d2, 8, 4, true);
    game.startGame(d2, 8, 4, true);
    assertEquals(8,game.getNumCascadePiles());
  }


  @Test
  public void testNumCascadePilesException() {
    assertEquals(-1,game.getNumCascadePiles());
  }

  @Test
  public void testNumCardsInFoundation() {
    game.startGame(d2, 52, 4, false);


    for (int i = 0; i < game.getNumCascadePiles() / 4; i++) {
      game.move(PileType.CASCADE, i, 0, PileType.FOUNDATION,0);
    }

    assertEquals(13,game.getNumCardsInFoundationPile(0));
    assertEquals(0,game.getNumCardsInFoundationPile(1));
  }

  @Test(expected = IllegalStateException.class)
  public void testNumCardsInFoundationExceptions() {
    game.getNumCardsInFoundationPile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumCardsInFoundationExceptions1() {
    game.startGame(d2, 52, 4, false);
    game.getNumCardsInFoundationPile(5);
  }

  @Test
  public void testNumCardsInOpenPile() {
    game.startGame(d2, 8, 4, false);

    assertEquals(0,game.getNumCardsInOpenPile(3));

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);

    assertEquals(1,game.getNumCardsInOpenPile(0));

  }

  @Test(expected = IllegalStateException.class)
  public void testNumCardsInOpenExceptions() {
    game.getNumCardsInOpenPile(3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumCardsInOpenExceptions1() {
    game.startGame(d2, 8, 4, false);

    game.getNumCardsInOpenPile(4);

  }

  @Test
  public void testNumOpenPiles() {
    game.startGame(d2, 8, 4, false);
    assertEquals(4,game.getNumOpenPiles());
  }

  @Test
  public void testNumOpenPilesException() {
    assertEquals(-1,game.getNumCascadePiles());
  }

  @Test
  public void testGetCardAt() {
    game.startGame(d2,8,4,false);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,5,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,4,PileType.FOUNDATION,0);

    assertEquals(null,game.getOpenCardAt(1));
    assertEquals("10♠", game.getOpenCardAt(0).toString());
    assertEquals("2♠",game.getCascadeCardAt(0,5).toString());
    assertEquals("A♠",game.getFoundationCardAt(0,0).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtOpenExceptions1() {
    game.startGame(d2,8,4,false);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,5,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,4,PileType.FOUNDATION,0);

    game.getOpenCardAt(4);

  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtOpenExceptions2() {
    game.getOpenCardAt(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtFoundationExceptions1() {
    game.startGame(d2,8,4,false);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,5,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,4,PileType.FOUNDATION,0);

    game.getFoundationCardAt(0,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtFoundationExceptions2() {
    game.startGame(d2,8,4,false);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,5,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,4,PileType.FOUNDATION,0);

    game.getFoundationCardAt(5,1);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtFoundationExceptions3() {
    game.getFoundationCardAt(0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCascadeExceptions1() {
    game.startGame(d2,8,4,false);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,5,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,4,PileType.FOUNDATION,0);

    game.getCascadeCardAt(5,9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCascadeExceptions2() {
    game.startGame(d2,8,4,false);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,5,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,4,PileType.FOUNDATION,0);

    game.getCascadeCardAt(9,0);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtCascadeExceptions3() {
    game.getCascadeCardAt(5,9);
  }

  @Test
  public void testMoveOpenPiles() {
    game.startGame(game.getDeck(),6,4,false);
    System.out.println(game.getCascadeCardAt(3,6));
    game.move(PileType.CASCADE,3,8,PileType.OPEN,0);
    game.move(PileType.CASCADE,3,7,PileType.OPEN,1);
    game.move(PileType.CASCADE, 3,6, PileType.OPEN, 2);
    System.out.println(game.getOpenCardAt(2));
    game.move(PileType.OPEN, 2,0,PileType.FOUNDATION,1);

    assertEquals("A♠", game.getFoundationCardAt(1,0).toString());
  }
}
