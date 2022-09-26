
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;

import cs3500.freecell.model.hw04.MultiFreecellModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Represents the tests for MultiMoves.
 */
public class MultiTests extends AbstractModelTest {
  @Before
  public void setUp() {
    game = makeModel();
    game2 = makeModel();
    deck = game2.getDeck();
    game3 = makeModel();
    d2 = game3.getDeck();
  }
  //FreecellView fcv = new FreecellTextView<ICard>(game);

  @Override
  protected FreecellModel makeModel() {
    return new MultiFreecellModel();
  }

  @Test
  public void testMultipleMoves()  {

    game.startGame(game.getDeck(), 8,4,false);


    game.move(PileType.CASCADE, 0,6,PileType.OPEN,0);
    game.move(PileType.CASCADE, 0,5,PileType.OPEN,1);
    game.move(PileType.CASCADE, 4,5,PileType.CASCADE,0);
    game.move(PileType.OPEN, 0,0,PileType.CASCADE,4);

    game.move(PileType.CASCADE, 4,4,PileType.CASCADE,2);

    assertEquals(game.getCascadeCardAt(2,7).toString(),"J♥");
    assertEquals(game.getCascadeCardAt(2,8).toString(),"10♠");

  }

  @Test
  public void testMultipleMoves1() {

    game.startGame(game.getDeck(), 15, 4, false);

    game.move(PileType.CASCADE, 9,2,PileType.FOUNDATION,0);
    game.move(PileType.CASCADE, 6,3,PileType.OPEN,0);
    game.move(PileType.CASCADE, 6,2,PileType.CASCADE,5);
    game.move(PileType.CASCADE, 3,3,PileType.CASCADE,5);

    game.move(PileType.CASCADE, 6,1,PileType.OPEN,1);
    game.move(PileType.CASCADE, 6,0,PileType.OPEN,2);
    game.move(PileType.CASCADE, 5,3,PileType.CASCADE,8);


    assertEquals(game.getCascadeCardAt(8,3).toString(),"Q♠");
    assertEquals(game.getCascadeCardAt(8,4).toString(),"J♥");
    assertEquals(game.getCascadeCardAt(8,5).toString(),"10♠");


  }


}
