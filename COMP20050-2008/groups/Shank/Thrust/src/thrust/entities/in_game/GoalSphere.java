/*
 * A re-implementation of the classic C=64 game 'Thrust'.
 *
 * @author "Joe Kiniry (kiniry@acm.org)"
 * @module "COMP 20050, COMP 30050"
 * @creation_date "March 2007"
 * @last_updated_date "April 2008"
 * @keywords "C=64", "Thrust", "game"
 */
package thrust.entities.in_game;

import java.awt.Color;

import thrust.entities.DynamicEntity;
import thrust.entities.NeutralEntity;
import thrust.entities.behaviors.Tow;

/**
 * The goal sphere that the spaceship needs to tow into
 * space away from the terrain to escape.
 * @author Joe Kiniry (kiniry@acm.org)
 * @version 18 April 2008
 */
public class GoalSphere extends DynamicEntity
  implements NeutralEntity, Tow {
  /*@ public invariant (* The fuel pod is destroyed by a bullet. *);
    @ public invariant (* If the fuel pod is destroyed, the spaceship
    @                     is destroyed. *);
    @ public invariant (* The goal sphere is destroyed by the factory's
    @                     chimney, but not its sphere. *);
    @ public invariant (* The goal sphere is not affected by the gun turret. *);
    @ public invariant (* The goal sphere is not affected by the fuel pod. *);
    @ public invariant (* The goal sphere is not affected by space. *);
    @ public invariant (* The goal sphere is not affected by stars. *);
    @ public invariant (* The goal sphere is destroyed by the terrain. *);
    @ public invariant (* When rend ered on the terrain, the goal sphere
    @                     sits on a pedestal. *);
    @ public invariant (* When being towed, the goal sphere is rendered
    @                     as a sphere. *);
    @ public invariant (* The shape of the goal sphere is always a circle. *);
    @ public invariant (* The color of the goal sphere is always green. *);
    @ public invariant color() == java.awt.Color.GREEN;
    @*/

  //@ public invariant (* The mass of the goal sphere is 10,000kg. *);
  /**
   * The mass of the goal sphere is 10,000kg.
   */
  public static final int MASS = 10000;
  /**
   * The color of the goal sphere.
   */
  private Color my_color;
  /**
   * The towing status of the sphere.
   */
  private boolean my_tow;
  public void tow() {
    my_tow = true;
  }
  /**
   * @return*/
  public boolean towed() {
    return my_tow;
  }
  /**@return*/
  public void color(final Color the_color) {
    my_color = the_color;
  }
  /**@return*/
  public Color color() {
    return my_color;
  }
  /**
   * @param*/
  public void simulate(final double the_amount) {
  }
}
