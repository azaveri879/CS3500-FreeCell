package cs3500.freecell.model.hw02;

/**
 * Represents a Card.
 */
public interface ICard {
  /**
   * Gets the value of a Card.
   * @return CardVal
   */
  public CardVal getVal();

  /**
   * Gets the suit of a Card.
   * @return Suit
   */
  public Suit getSuit();

  /**
   * Checks if an Object is equal to this Card.
   * @param that any object
   * @return true or false
   */
  public boolean equals(Object that);

  /**
   * Converts a Card to its String representation in the
   * form of its value and then suit.
   *
   * @return value + symbol of its suit
   */
  public String toString();

}
