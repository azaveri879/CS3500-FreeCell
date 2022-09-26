package cs3500.freecell.model.hw02;

/**
 * Represents Cards from Ace to King.
 * A,1,2,3,4,5,6,7,8,9,10,J,K,Q.
 */
public enum CardVal {
  ACE(1),TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
  EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

  private final int val;
  CardVal(int val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return Integer.toString(this.val);
  }

  public int getVal() {
    return this.val;
  }
}
