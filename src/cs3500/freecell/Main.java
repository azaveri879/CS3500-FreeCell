package cs3500.freecell;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;

import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiFreecellModel;

import java.io.InputStreamReader;


/**
 * Main Class for playing a FreecellGame.
 */
public class Main {

  /**
   * main method for running and playing the FreecellGame.
   * @param args Any arguments for the console
   */
  public static void main(String[] args) {
    Readable r = new InputStreamReader(System.in);
    Appendable ap = System.out;

    FreecellModel<ICard> frc = new SimpleFreecellModel();
    FreecellModel<ICard> mrc = new MultiFreecellModel();

    FreecellController<ICard> frC = new SimpleFreecellController(mrc,r,ap);


    frC.playGame(frc.getDeck(),10,4,true);
  }
}
