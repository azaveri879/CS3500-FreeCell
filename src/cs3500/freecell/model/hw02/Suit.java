package cs3500.freecell.model.hw02;


/**
 * There are Four types of Suits.
 * Clubs -- ♣
 * Diamonds -- ♦
 * Hearts -- ♥
 * Spades -- ♠
 */
public enum Suit {

  CLUBS("♣", "black"),DIAMONDS("♦","red"),
  HEARTS("♥","red"),SPADES("♠","black");

  private final String value;
  private final String c;

  Suit(String value, String c) {
    this.value = value;
    this.c = c;
  }

  @Override
  public String toString() {
    return this.value;
  }

  /**
   * Gets the color of a Suit.
   * @return String
   */
  public String getColor() {
    return this.c;
  }


}
