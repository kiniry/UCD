/*
 * A re-implementation of the classic C=64 game 'Thrust'.
 *
 * @author "Joe Kiniry (kiniry@acm.org)"
 * @module "COMP 20050, COMP 30050"
 * @creation_date "March 2007"
 * @last_updated_date "April 2008"
 * @keywords "C=64", "Thrust", "game"
 */

package thrust.entities;

/**
 * Entities whose position or orientation do not change.
 * @author Joe Kiniry (kiniry@acm.org)
 * @version 18 April 2008
 */
public abstract class StaticEntity extends DynamicEntity {

  /** Double array to hold position of StaticEntity. */
  private static double[] my_position;
  /** Double to hold orientation (radians) of StaticEntity. */
  private static double my_orientation;
  /** Int to hold array length value for my_position array. */
  private static final int MY_ARRAYLENGTH = 2;

  /**
   * @return A new static entity with this position and orientation.
   * @param the_position the immutable position.
   * @param the_orientation the immutable orientation.
   */
  //@ ensures position().equals(the_position);
  //@ ensures orientation().equals(the_orientation);
  public static StaticEntity make(final double[] the_position,
                                  final double the_orientation) {

    my_position = new double[MY_ARRAYLENGTH];
    my_position[0] = the_position[0];
    my_position[1] = the_position[1];

    my_orientation = the_orientation;

    return null;
  }
/**

  // Why are any of the following methods here? I thought this was
  // StaticEntity..

  /* (non-Javadoc)
   * @see thrust.physics.PhysicsInterface#mass()
   *
  //@ ensures \result == 0;
  public abstract double mass();

  /* (non-Javadoc)
   * @see thrust.physics.PhysicsInterface#velocity()
   *
  //@ ensures \result[0] == 0 & \result[1] == 0;
  public abstract double[] velocity();

  /* (non-Javadoc)
   * @see thrust.physics.PhysicsInterface#acceleration()
   *
  //@ ensures \result[0] == 0 & \result[1] == 0;
  public abstract double[] acceleration();

  /* (non-Javadoc)
   * @see thrust.physics.PhysicsInterface#momentum()
   *
  //@ ensures \result == 0;
  public abstract double momentum();

  //@ public invariant (* All queries are constant. *);
  //@ public constraint position() == \old(position());
  //@ public constraint orientation() == \old(orientation());

  /*@ public invariant (* Mass, velocity, acceleration, and momentum
    @                     are all zero. *);
    @*/
}
