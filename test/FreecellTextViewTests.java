import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;

import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test Class for FreeCellView.
 */
public class FreecellTextViewTests {
  FreecellModel<ICard> game = new SimpleFreecellModel();
  List<ICard> d2 = game.getDeck();
  Appendable str = new StringBuilder();

  FreecellView fct = new FreecellTextView(game,str);

  @Test
  public void testTextView() {
    game.startGame(d2,8,4,false);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,5,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,4,PileType.FOUNDATION,0);

    assertEquals("F1: A♠\n" + "F2:\n" + "F3:\n" + "F4:\n" +
        "O1: 10♠\n" + "O2:\n" + "O3: 9♠\n" +
        "O4:\n" + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠\n" +
        "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
        "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
        "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
        "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
        "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n" +
        "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
        "C8: 8♣, 3♦, J♦, 6♥", fct.toString());

  }

  @Test
  public void testRenderBoard() throws IOException {
    game.startGame(d2,8,4,false);

    game.move(PileType.CASCADE,0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE,7,5,PileType.OPEN,2);
    game.move(PileType.CASCADE,7,4,PileType.FOUNDATION,0);

    fct.renderBoard();
    System.out.println(str);
    assertEquals("F1: A♠\n" + "F2:\n" + "F3:\n" + "F4:\n"
        + "O1: 10♠\n" + "O2:\n" + "O3: 9♠\n" + "O4:\n"
        + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠\n" + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
        + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
        + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
        + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" + "C8: 8♣, 3♦, J♦, 6♥\n", str.toString());

  }

  @Test
  public void testRenderMessage() throws IOException {
    game.startGame(d2, 8, 4, false);

    game.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    game.move(PileType.CASCADE, 7, 5, PileType.OPEN, 2);
    game.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);

    fct.renderMessage("Invalid!");
    System.out.println(str);
    assertEquals("Invalid!",str.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    FreecellModel<ICard> game = new SimpleFreecellModel();
    FreecellTextView f = new FreecellTextView(game,null);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    FreecellModel<ICard> game = new SimpleFreecellModel();
    FreecellTextView f = new FreecellTextView(null,str);
  }

  @Test(expected = IllegalStateException.class)
  public void testStateExceptions() {
    Appendable a = new StringBuilder();
    FreecellModel<ICard> game = new SimpleFreecellModel();
    FreecellTextView f = new FreecellTextView(game);

    //trying to catch IO exception throws a IllegalState
    //Not sure how to write an IO exception
  }







}
