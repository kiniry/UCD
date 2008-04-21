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

import thrust.physics.Physics;

/**
 * Entities whose position or orientation change.
 * @author Joe Kiniry (kiniry@acm.org)
 * @edited Tara Flood (Tara.Flood@ucdconnect.ie)
 * @version 21 April 2008
 */
public abstract class DynamicEntity extends Entity
  implements Physics {
  /**
   * @return A new dynamic entity with the given physical state.
   * @param the_position the initial position.
   * @param the_orientation the initial orientation.
   * @param the_acceleration the initial acceleration.
   * @param the_grav_constant the initial gravitational constant.
   * @param the_mass the initial mass.
   * @param the_velocity the initial velocity.
   */
  public static DynamicEntity make(final double[] the_position,
                                   final double the_orientation,
                                   final double[] the_acceleration,
                                   final double the_grav_constant,
                                   final double the_mass,
                                   final double[] the_velocity) {
    assert false; //@ assert false;
    return null;
  }
}
