/*
 * A re-implementation of the classic C=64 game 'Thrust'.
 *
 * @author "Joe Kiniry (kiniry@acm.org)"
 * @module "COMP 20050, COMP 30050"
 * @creation_date "March 2007"
 * @last_updated_date "April 2008"
 * @keywords "C=64", "Thrust", "game"
 */

package thrust.physics;

/**
 * Computing the behavior of entities according to physical
 * simulation in two dimensions.
 * @author Joe Kiniry (kiniry@acm.org)
 * @version 2 April 2008
 */
public interface PhysicsInterface {
  //@ constraint (* The gravitational constant never changes. *);
  //@ constraint gravitational_constant() == \old(gravitational_constant());

  /**
   * @return What is your acceleration in meters per second squared?
   */
  //@ ensures \result.length == 2;
  /*@ pure @*/ double[] acceleration();

  /**
   * @return What is the gravitational constant?
   */
  /*@ pure @*/ double gravitational_constant();

  /**
   * @return What is your mass in kilograms?
   */
  //@ ensures 0 <= \result;
  /*@ pure @*/ double mass();

  /**
   * @return What is your momentum in kilograms*meters per second?
   */
  /*@ pure @*/ double momentum();

  /**
   * @return What is your orientation in radians?
   */
  /*@ pure @*/ double orientation();

  /**
   * @return What is your position in meters from the origin?
   */
  //@ ensures \result.length == 2;
  /*@ pure @*/ double[] position();

  /**
   * @return What is your velocity in meters per second?
   */
  /*@ pure @*/ double[] velocity();

  /**
   * Simulate yourself for this many seconds.
   * @param some_seconds the number of seconds to simulate.
   */
  void simulate(double some_seconds);
}