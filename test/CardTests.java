import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardVal;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.Suit;
import org.junit.Test;




/**
 * Tests for the ICard methods.
 */
public class CardTests {

  ICard aceSpades = new Card(CardVal.ACE, Suit.SPADES);
  ICard fiveDiamonds = new Card(CardVal.FIVE, Suit.DIAMONDS);
  ICard tenClubs = new Card(CardVal.TEN, Suit.CLUBS);
  ICard kingHearts = new Card(CardVal.KING, Suit.HEARTS);
  ICard kingHearts2 = new Card(CardVal.KING, Suit.HEARTS);

  Card aceHearts = new Card(CardVal.ACE, Suit.HEARTS);
  Card jackClubs = new Card(CardVal.JACK, Suit.CLUBS);

  @Test
  public void testGetters() {
    assertEquals(CardVal.ACE, aceHearts.getVal());
    assertEquals(Suit.CLUBS, jackClubs.getSuit());
    assertEquals("black", jackClubs.getSuit().getColor());
    assertEquals("red", aceHearts.getSuit().getColor());
  }

  @Test
  public void toStringTest() {
    assertEquals("K♥", kingHearts.toString());
    assertEquals("A♠", aceSpades.toString());
    assertEquals("5♦", fiveDiamonds.toString());
    assertEquals("10♣",tenClubs.toString());
  }

  @Test
  public void equalsTest() {
    assertTrue(kingHearts.equals(kingHearts2));
    assertFalse(kingHearts.equals(aceSpades));
    assertFalse(kingHearts.equals(5));
  }

  @Test
  public void hashCodeTest() {
    assertEquals(kingHearts2.hashCode(),kingHearts.hashCode());
  }

  @Test(expected = NullPointerException.class)
  public void nullTest() {
    ICard nullExample = new Card(null,null);
  }
}

