package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the Controller of a Freecell game.
 */
public class SimpleFreecellController implements FreecellController {
  private FreecellModel<ICard> model;
  private Readable rd;
  private Appendable ap;
  private FreecellView fcv;

  /**
   * Constructs a SimpleFreecellController with the given parameters.
   * @param model a non-null model freecell game.
   * @param rd a non-null readable input
   * @param ap a non-null appendable output
   * @throws IllegalArgumentException if any arguments are null
   */
  public SimpleFreecellController(FreecellModel<ICard> model, Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("Arguments cannot be null!");
    }

    this.model = model;
    this.rd = Objects.requireNonNull(rd);
    this.ap = Objects.requireNonNull(ap);
    fcv = new FreecellTextView<ICard>(model,ap);
  }


  @Override
  public void playGame(List deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {
    if (deck == null) {
      throw new IllegalArgumentException("Cannot have a null deck!");
    }
    try {
      try {
        model.startGame(deck, numCascades, numOpens, shuffle);

      }
      catch (IllegalArgumentException i) {
        fcv.renderMessage("Could not start game.");
        return;
      }

      try {
        while (!model.isGameOver()) {
          Scanner scan = new Scanner(rd);
          fcv.renderBoard();
          fcv.renderMessage("\n");
          if (!scan.hasNext()) {
            throw new IllegalStateException("Unreadable");
          }
          mover(scan);
          return;
        }

      }

      catch (NoSuchElementException e) {
        throw new IllegalStateException("Ran out of Inputs!");
      }

      catch (IllegalStateException w) {
        throw new IllegalStateException("Unreadable input!");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Ran out of Inputs!");
    }


  }

  /**
   * Moves a user inputted card to its destination based on a string scanner of
   * form "C1 2 F3" for example. This method throws an exception for any other
   * kind of input.
   * @param scan scanner input to iterate through
   * @throws IOException if a error message must be rendered
   */
  private void mover(Scanner scan)
      throws IOException {
    PileType pile = null;
    int pileNum = -1;
    int cardIndex = -1;
    PileType dest = null;
    int destPileNum = -1;
    int num = 0;

    try {
      while (scan.hasNext()) {
        String str = scan.next();

        if (str.contains("q") || str.contains("Q")) {
          fcv.renderMessage("Game quit prematurely.\n");
          return;
        }
        else if (num % 3 == 1) {
          try {
            cardIndex = Integer.parseInt(str) - 1;
            num++;
          }
          catch (NumberFormatException n) {
            continue;
          }
        }
        else if (str.charAt(0) == 'C' || str.charAt(0) == 'O' || str.charAt(0) == 'F') {
          if (num % 3 == 0) {
            pile = getPile(str);
            pileNum = Integer.parseInt(str.substring(1)) - 1;
            num++;
          }
          else if (num % 3 == 2) {
            dest = getPile(str);
            destPileNum = Integer.parseInt(str.substring(1)) - 1;
            num++;

          }
        }

        if (num != 0 && num % 3 == 0) {
          try {
            model.move(pile, pileNum, cardIndex, dest, destPileNum);
          } catch (IllegalArgumentException i) {
            fcv.renderMessage(
                new StringBuilder().append(i.getMessage() + " Try Again!").toString() + "\n");
            continue;
          }
          fcv.renderBoard();
          if (model.isGameOver()) {
            ap.append("Game over.");
          }
        }
      }

      switch (num) {
        case 0:
          fcv.renderMessage("Need moving card!\n");
          return;
        case 1:
          fcv.renderMessage("Need card index!\n");
          return;
        case 2:
          fcv.renderMessage("Need destination!\n");
          return;
        default:

      }
    }

    catch (NullPointerException n) {
      fcv.renderMessage("Please input valid Piles and Indices!\n");
    }
    catch (IOException e) {
      throw new IllegalStateException("Ran out of Inputs!");
    }

  }

  /**
   * Retrieves the piletype of a given string.
   * @param str either "C" or "O" or "F"
   * @return PileType
   */
  private PileType getPile(String str) throws IOException {
    PileType p = null;
    if (str.charAt(0) == 'C') {
      p = PileType.CASCADE;
    }
    else if (str.charAt(0) == 'O') {
      p = PileType.OPEN;
    }
    else if (str.charAt(0) == 'F') {
      p = PileType.FOUNDATION;
    }
    else {
      fcv.renderMessage("Game quit prematurely.");
    }
    return p;
  }
}
