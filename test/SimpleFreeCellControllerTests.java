import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import cs3500.freecell.controller.FreecellController;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for SimpleFreecellController.
 */
public class SimpleFreeCellControllerTests {
  FreecellModel game = new SimpleFreecellModel();
  FreecellView view = new FreecellTextView<ICard>(game);

  @org.junit.Test
  public void testValidInputs() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);
    assertEquals("F1: A♣\n" + "F2:\n" + "F3:\n" + "F4:\n" + "O1:\n" + "O2:\n"
        + "O3:\n" + "O4:\n"
        + "C1:\n" + "C2: 2♣\n" + "C3: 3♣\n" + "C4: 4♣\n"
        + "C5: 5♣\n" + "C6: 6♣\n" + "C7: 7♣\n" + "C8: 8♣\n" + "C9: 9♣\n"
        + "C10: 10♣\n" + "C11: J♣\n" + "C12: Q♣\n" + "C13: K♣\n" + "C14: A♦\n" + "C15: 2♦\n"
        + "C16: 3♦\n" + "C17: 4♦\n" + "C18: 5♦\n" + "C19: 6♦\n" + "C20: 7♦\n" + "C21: 8♦\n"
        + "C22: 9♦\n" + "C23: 10♦\n" + "C24: J♦\n" + "C25: Q♦\n" + "C26: K♦\n" + "C27: A♥\n"
        + "C28: 2♥\n" + "C29: 3♥\n" + "C30: 4♥\n" + "C31: 5♥\n" + "C32: 6♥\n" + "C33: 7♥\n"
        + "C34: 8♥\n" + "C35: 9♥\n"
        + "C36: 10♥\n" + "C37: J♥\n" + "C38: Q♥\n" + "C39: K♥\n" + "C40: A♠\n" + "C41: 2♠\n"
        + "C42: 3♠\n" + "C43: 4♠\n" + "C44: 5♠\n" + "C45: 6♠\n" + "C46: 7♠\n" + "C47: 8♠\n"
        + "C48: 9♠\n" + "C49: 10♠\n" + "C50: J♠\n" + "C51: Q♠\n" + "C52: K♠\n",string.toString());
  }

  @org.junit.Test
  public void testValidInputs1() {

    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1 C2 1 F1");
    FreecellController s = new SimpleFreecellController(game,stringReader,string);

    s.playGame(game.getDeck(),52,4,false);

    assertEquals("F1: A♣\n" + "F2:\n" + "F3:\n" + "F4:\n"
        + "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n" + "C1:\n"
        + "C2: 2♣\n" + "C3: 3♣\n" + "C4: 4♣\n" + "C5: 5♣\n"
        + "C6: 6♣\n" + "C7: 7♣\n" + "C8: 8♣\n" + "C9: 9♣\n"
        + "C10: 10♣\n" + "C11: J♣\n" + "C12: Q♣\n" + "C13: K♣\n"
        + "C14: A♦\n" + "C15: 2♦\n" + "C16: 3♦\n" + "C17: 4♦\n"
        + "C18: 5♦\n" + "C19: 6♦\n" + "C20: 7♦\n" + "C21: 8♦\n"
        + "C22: 9♦\n" + "C23: 10♦\n" + "C24: J♦\n" + "C25: Q♦\n"
        + "C26: K♦\n" + "C27: A♥\n" + "C28: 2♥\n" + "C29: 3♥\n"
        + "C30: 4♥\n" + "C31: 5♥\n" + "C32: 6♥\n" + "C33: 7♥\n"
        + "C34: 8♥\n" + "C35: 9♥\n" + "C36: 10♥\n" + "C37: J♥\n"
        + "C38: Q♥\n" + "C39: K♥\n" + "C40: A♠\n" + "C41: 2♠\n"
        + "C42: 3♠\n" + "C43: 4♠\n" + "C44: 5♠\n" + "C45: 6♠\n"
        + "C46: 7♠\n" + "C47: 8♠\n" + "C48: 9♠\n" + "C49: 10♠\n"
        + "C50: J♠\n" + "C51: Q♠\n" + "C52: K♠\n"
        + "F1: A♣, 2♣\n" + "F2:\n" + "F3:\n" + "F4:\n"
        + "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n"
        + "C1:\n" + "C2:\n" + "C3: 3♣\n" + "C4: 4♣\n"
        + "C5: 5♣\n" + "C6: 6♣\n" + "C7: 7♣\n" + "C8: 8♣\n"
        + "C9: 9♣\n" + "C10: 10♣\n" + "C11: J♣\n" + "C12: Q♣\n"
        + "C13: K♣\n" + "C14: A♦\n" + "C15: 2♦\n" + "C16: 3♦\n"
        + "C17: 4♦\n" + "C18: 5♦\n" + "C19: 6♦\n" + "C20: 7♦\n"
        + "C21: 8♦\n" + "C22: 9♦\n" + "C23: 10♦\n" + "C24: J♦\n"
        + "C25: Q♦\n" + "C26: K♦\n" + "C27: A♥\n" + "C28: 2♥\n"
        + "C29: 3♥\n" + "C30: 4♥\n" + "C31: 5♥\n" + "C32: 6♥\n"
        + "C33: 7♥\n" + "C34: 8♥\n" + "C35: 9♥\n" + "C36: 10♥\n"
        + "C37: J♥\n" + "C38: Q♥\n" + "C39: K♥\n" + "C40: A♠\n"
        + "C41: 2♠\n" + "C42: 3♠\n" + "C43: 4♠\n" + "C44: 5♠\n"
        + "C45: 6♠\n" + "C46: 7♠\n" + "C47: 8♠\n" + "C48: 9♠\n"
        + "C49: 10♠\n" + "C50: J♠\n" + "C51: Q♠\n" + "C52: K♠\n",string.toString());

  }

  @org.junit.Test
  public void testQuitValidInputs1() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader(" q C1 1 F1 ");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);



    assertEquals("Game quit prematurely.\n", string.toString());

  }

  @org.junit.Test
  public void testQuitValidInputs2() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader(" C1 1 F1 q");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);
    System.out.println(string);
    s.playGame(game.getDeck(), 52, 4, false);


    assertEquals("F1: A♣\n" + "F2:\n" + "F3:\n" + "F4:\n"
        + "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n" + "C1:\n" + "C2: 2♣\n"
        + "C3: 3♣\n" + "C4: 4♣\n" + "C5: 5♣\n" + "C6: 6♣\n" + "C7: 7♣\n"
        + "C8: 8♣\n" + "C9: 9♣\n" + "C10: 10♣\n" + "C11: J♣\n" + "C12: Q♣\n"
        + "C13: K♣\n" + "C14: A♦\n" + "C15: 2♦\n" + "C16: 3♦\n" + "C17: 4♦\n"
        + "C18: 5♦\n" + "C19: 6♦\n" + "C20: 7♦\n" + "C21: 8♦\n" + "C22: 9♦\n"
        + "C23: 10♦\n" + "C24: J♦\n" + "C25: Q♦\n" + "C26: K♦\n" + "C27: A♥\n"
        + "C28: 2♥\n" + "C29: 3♥\n" + "C30: 4♥\n" + "C31: 5♥\n" + "C32: 6♥\n"
        + "C33: 7♥\n" + "C34: 8♥\n" + "C35: 9♥\n" + "C36: 10♥\n" + "C37: J♥\n"
        + "C38: Q♥\n" + "C39: K♥\n" + "C40: A♠\n" + "C41: 2♠\n" + "C42: 3♠\n"
        + "C43: 4♠\n" + "C44: 5♠\n" + "C45: 6♠\n" + "C46: 7♠\n" + "C47: 8♠\n"
        + "C48: 9♠\n" + "C49: 10♠\n" + "C50: J♠\n" + "C51: Q♠\n" + "C52: K♠\n"
        + "Game quit prematurely.\n", string.toString());
  }

  @org.junit.Test
  public void testInValidInputs1() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("nsjndkjn C1 sdkjasdh");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);


    assertEquals("Need card index!\n", string.toString());

  }

  @org.junit.Test
  public void testInValidInputs2() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("nsjndkjn sdkjasdh 1");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);

    assertEquals("Need moving card!\n", string.toString());
  }

  @org.junit.Test
  public void testInValidInputs3() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 2 asjjklkfjkl");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);

    assertEquals("Need destination!\n", string.toString());
  }

  @org.junit.Test
  public void testGameOverInputs() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1 C2 1 F1 C3 1 F1 C4 1 F1 C5 1 F1" +
        " C6 1 F1 C7 1 F1 C8 1 F1 C9 1 F1 C10 1 F1 C11 1 F1 C12 1 F1 C13 1 F1" +
        " C14 1 F2 C15 1 F2 C16 1 F2 C17 1 F2 C18 1 F2" +
        " C19 1 F2 C20 1 F2 C21 1 F2 C22 1 F2 C23 1 F2 C24 1 F2 C25 1 F2 C26 1 F2" +
         " C27 1 F3 C28 1 F3 C29 1 F3 C30 1 F3 C31 1 F3" +
        " C32 1 F3 C33 1 F3 C34 1 F3 C35 1 F3 C36 1 F3 C37 1 F3 C38 1 F3 C39 1 F3" +
        " C40 1 F4 C41 1 F4 C42 1 F4 C43 1 F4 C44 1 F4" +
        " C45 1 F4 C46 1 F4 C47 1 F4 C48 1 F4 C49 1 F4 C50 1 F4 C51 1 F4 C52 1 F4");

    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);

    assertEquals( "Game over.",
        string.toString().substring(string.toString().length() - 10));

  }

  @org.junit.Test
  public void testInValidInputs4() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C72 1 F1");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);
    System.out.println(string);
    assertEquals("Game quit prematurely.", string.toString());
  }

  @org.junit.Test
  public void testInValidInputs5() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 \n 1 \n F1");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);
    System.out.println(string);

    assertEquals("F1: A♣\n" + "F2:\n" + "F3:\n" + "F4:\n" +
        "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n" + "C1:\n" + "C2: 2♣\n" +
        "C3: 3♣\n" + "C4: 4♣\n" + "C5: 5♣\n" + "C6: 6♣\n" + "C7: 7♣\n" +
        "C8: 8♣\n" + "C9: 9♣\n" + "C10: 10♣\n" + "C11: J♣\n" + "C12: Q♣\n" +
        "C13: K♣\n" + "C14: A♦\n" + "C15: 2♦\n" + "C16: 3♦\n" + "C17: 4♦\n" +
        "C18: 5♦\n" + "C19: 6♦\n" + "C20: 7♦\n" + "C21: 8♦\n" + "C22: 9♦\n" +
        "C23: 10♦\n" + "C24: J♦\n" + "C25: Q♦\n" + "C26: K♦\n" + "C27: A♥\n" +
        "C28: 2♥\n" + "C29: 3♥\n" + "C30: 4♥\n" + "C31: 5♥\n" + "C32: 6♥\n" +
        "C33: 7♥\n" + "C34: 8♥\n" + "C35: 9♥\n" + "C36: 10♥\n" + "C37: J♥\n" +
        "C38: Q♥\n" + "C39: K♥\n" + "C40: A♠\n" + "C41: 2♠\n" + "C42: 3♠\n" +
        "C43: 4♠\n" + "C44: 5♠\n" + "C45: 6♠\n" + "C46: 7♠\n" + "C47: 8♠\n" +
        "C48: 9♠\n" + "C49: 10♠\n" + "C50: J♠\n" + "C51: Q♠\n" + "C52: K♠\n", string.toString());

  }

  @org.junit.Test
  public void testInValidInputs6() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, true);
    System.out.println(string);

    assertEquals("Invalid move to foundation! Try Again!",
        string.toString());
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testNullControllers() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1");
    SimpleFreecellController a = new SimpleFreecellController(null, stringReader,string);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testNullControllers2() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1");
    SimpleFreecellController a = new SimpleFreecellController(game,null,string);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testNullControllers3() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1");
    SimpleFreecellController a = new SimpleFreecellController(game, stringReader,null);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testArgExceptions() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1");

    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(null, 52, 4, false);

  }

  @org.junit.Test(expected = IllegalStateException.class)
  public void testStateExceptions() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("");

    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);
  }

  @org.junit.Test
  public void testValidInputs3() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader(" C14 13q F1");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, true);
    System.out.println(string);
    assertEquals("Game quit prematurely.\n", string.toString());
  }

  @org.junit.Test
  public void testInValidInputs7() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 14 F1");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, true);
    System.out.println(string);

    assertEquals("Game quit prematurely.",
        string.toString());
  }

  @org.junit.Test
  public void testCardOutOfIndex1() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1 C1 13 F1");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);
    System.out.println(string);
    assertEquals("F1: A♣\n" + "F2:\n" + "F3:\n" + "F4:\n"
        + "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n" + "C1:\n" + "C2: 2♣\n"
        + "C3: 3♣\n" + "C4: 4♣\n" + "C5: 5♣\n" + "C6: 6♣\n" + "C7: 7♣\n"
        + "C8: 8♣\n" + "C9: 9♣\n" + "C10: 10♣\n" + "C11: J♣\n" + "C12: Q♣\n"
        + "C13: K♣\n" + "C14: A♦\n" + "C15: 2♦\n" + "C16: 3♦\n" + "C17: 4♦\n"
        + "C18: 5♦\n" + "C19: 6♦\n" + "C20: 7♦\n" + "C21: 8♦\n" + "C22: 9♦\n"
        + "C23: 10♦\n" + "C24: J♦\n" + "C25: Q♦\n" + "C26: K♦\n" + "C27: A♥\n"
        + "C28: 2♥\n" + "C29: 3♥\n" + "C30: 4♥\n" + "C31: 5♥\n" + "C32: 6♥\n"
        + "C33: 7♥\n" + "C34: 8♥\n" + "C35: 9♥\n" + "C36: 10♥\n" + "C37: J♥\n"
        + "C38: Q♥\n" + "C39: K♥\n" + "C40: A♠\n" + "C41: 2♠\n" + "C42: 3♠\n"
        + "C43: 4♠\n" + "C44: 5♠\n" + "C45: 6♠\n" + "C46: 7♠\n" + "C47: 8♠\n"
        + "C48: 9♠\n" + "C49: 10♠\n" + "C50: J♠\n" + "C51: Q♠\n" + "C52: K♠\n"
        + "Game quit prematurely.",string.toString());
  }

  @org.junit.Test
  public void testCardOutOfIndex2() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1 1 F1 C1 13 F1q");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 6, 4, false);
    //SHould I expect index out of bounds or invalid move?

    assertEquals("F1:\n" + "F2:\n" + "F3:\n" + "F4:\n" +
        "O1:\n" + "O2:\n" + "O3:\n" + "O4:\n" +
        "C1: A♣, 7♣, K♣, 6♦, Q♦, 5♥, J♥, 4♠, 10♠\n" +
        "C2: 2♣, 8♣, A♦, 7♦, K♦, 6♥, Q♥, 5♠, J♠\n" +
        "C3: 3♣, 9♣, 2♦, 8♦, A♥, 7♥, K♥, 6♠, Q♠\n" +
        "C4: 4♣, 10♣, 3♦, 9♦, 2♥, 8♥, A♠, 7♠, K♠\n" +
        "C5: 5♣, J♣, 4♦, 10♦, 3♥, 9♥, 2♠, 8♠\n" +
        "C6: 6♣, Q♣, 5♦, J♦, 4♥, 10♥, 3♠, 9♠\n" + "\n" +
        "Cannot remove from empty cascade pile and Can only " +
        "move from top of Cascade Pile Try Again!\n" + "Game quit prematurely.\n",
        string.toString());
  }

  @Test
  public void testShuffleFromController() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("q q");
    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, true);
    System.out.println(string);
    String acc = string.toString();


    string = new StringBuilder();
    stringReader = new StringReader("q q");
    FreecellController s1 = new SimpleFreecellController(game, stringReader, string);

    s1.playGame(game.getDeck(),52,4,false);
    String n = string.toString();

    System.out.println(string);
    //assertNotEquals();
    assertNotEquals(acc,n);
  }

  @org.junit.Test
  public void testGameOverInputs2() {
    Appendable string = new StringBuilder();
    Readable stringReader = new StringReader("C1\n 1\n F1\n C2\n 1\n F1\n " +
        "C3\n 1\n F1\n C4\n 1\n F1\n C5 1\n F1\n" +
        " C6\n 1\n F1\n C7\n 1\n F1\n C8\n 1\n F1\n C9\n 1\n F1\n C10\n 1\n F1\n " +
        "C11\n 1\n F1\n C12\n 1\n F1\n C13\n 1\n F1\n" +
        " C14\n 1\n F2\n C15\n 1\n F2\n C16\n 1\n F2\n C17\n 1\n F2\n C18\n 1\n F2\n" +
        " C19\n 1\n F2\n C20\n 1\n F2\n C21\n 1\n F2\n" +
        " C22\n 1\n F2\n C23\n 1\n F2\n C24\n 1\n F2\n C25\n 1\n F2\n C26\n 1\n F2\n" +
        " C27\n 1\n F3\n C28\n 1\n F3\n C29\n 1\n F3\n C30\n 1\n F3\n C31\n 1\n F3\n" +
        " C32\n 1\n F3\n C33\n 1\n F3\n C34\n 1\n F3\n" +
        " C35\n 1\n F3\n C36\n 1\n F3\n C37\n 1\n F3\n C38\n 1\n F3\n C39\n 1\n F3\n" +
        " C40\n 1\n F4\n C41\n 1\n F4\n C42\n 1\n F4\n C43\n 1\n F4\n C44\n 1\n F4\n" +
        " C45\n 1\n F4\n C46\n 1\n F4\n C47\n 1\n F4\n" +
        " C48\n 1\n F4\n C49\n 1\n F4\n C50\n 1\n F4\n C51\n 1\n F4\n C52\n 1\n F4\n");

    FreecellController s = new SimpleFreecellController(game, stringReader, string);

    s.playGame(game.getDeck(), 52, 4, false);

    assertEquals( "Game over.",
        string.toString().substring(string.toString().length() - 10));

  }


}
