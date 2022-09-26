import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.hw02.CardVal;
import cs3500.freecell.model.hw02.Suit;
import org.junit.Test;



/**
 * Tests for the CardVal and Suit methods.
 */
public class CardValSuitMethodTest {

  @Test
  public void toStringTests() {
    assertEquals("1",CardVal.ACE.toString());
    assertEquals("5", CardVal.FIVE.toString());
    assertEquals("11", CardVal.JACK.toString());

    assertEquals("♣", Suit.CLUBS.toString());
  }

  @Test
  public void getValTest() {
    assertEquals(9, CardVal.NINE.getVal());
    assertEquals(12, CardVal.QUEEN.getVal());
  }
}
