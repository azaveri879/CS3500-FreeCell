
import static org.junit.Assert.assertTrue;

import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiFreecellModel;
import org.junit.Test;

/**
 * Represents the tests for FreeCellCreator.
 */
public class FreecellModelCreatorTests {
  FreecellModelCreator fmc = new FreecellModelCreator();

  @Test
  public void testFreeCellCreator() {
    assertTrue(fmc.create(GameType.MULTIMOVE) instanceof MultiFreecellModel);
    assertTrue(fmc.create(GameType.SINGLEMOVE) instanceof SimpleFreecellModel);

  }
}
