/*
 * A re-implementation of the classic C=64 game 'Thrust'.
 *
 * @author "Colin Casey (colin.casey@org.com)"
 * @module "COMP 20050, COMP 30050"
 * @creation_date "April 2008"
 * @last_updated_date "April 2008"
 * @keywords "C=64", "Thrust", "game"
 */

package thrust.display;

/**
 * @author Colin Casey (colin.casey@org.com)
 * @author Neil McCarthy (neil.mccarthy@ucdconnect.ie)
 * @version 15 April 2008
 */
public class HighScoreDisplay extends AbstractHighScoreDisplay {
  
  int[] high_scores;
  
  public boolean displayed() {
    return true;
  }

  public void display() {
      System.out.print(high_scores);
  }

  public void hide() {
    
  }

  public void add_new_high_score() {
    
  }

}
