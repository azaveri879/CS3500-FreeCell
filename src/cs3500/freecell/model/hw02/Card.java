package cs3500.freecell.model.hw02;

import java.util.Objects;

/**
 * Represents a Card from a standard 52 card deck.
 */
public class Card implements ICard {
  private final CardVal val;
  private final Suit suit;

  /**
   * Constructs a valid Card according to the standard
   * 52 card deck.
   *
   * @param val the value of the card.
   * @param suit the suit of the card.
   *
   * @throws NullPointerException If any arguments are null.
   */
  public Card(CardVal val, Suit suit) {
    this.val = Objects.requireNonNull(val);
    this.suit = Objects.requireNonNull(suit);
  }

  /**
   * Gets the value of a Card.
   * @return CardVal
   */
  @Override
  public CardVal getVal() {
    return this.val;
  }

  /**
   * Gets the suit of a Card.
   * @return Suit
   */
  @Override
  public Suit getSuit() {
    return this.suit;
  }



  /**
   * Checks if an Object is equal to this Card.
   * @param that any object
   * @return true or false
   */
  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Card)) {
      return false;
    }

    return ((Card) that).val.getVal() == this.val.getVal() &&
        ((Card)that).suit.toString().equals(this.suit.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.val) + Objects.hashCode(this.suit);
  }

  /**
   * Converts a Card to its String representation in the
   * form of its value and then suit.
   *
   * @return value + symbol of its suit
   */
  @Override
  public String toString() {
    String temp = "";
    switch (val) {
      case ACE:
        temp = "A" + this.getSuit().toString();
        break;
      case JACK:
        temp = "J" + this.getSuit().toString();
        break;
      case QUEEN:
        temp = "Q" + this.getSuit().toString();
        break;
      case KING:
        temp = "K" + this.getSuit().toString();
        break;
      default:
        temp = this.val.toString() + this.getSuit().toString();

    }

    return temp;
  }

}
