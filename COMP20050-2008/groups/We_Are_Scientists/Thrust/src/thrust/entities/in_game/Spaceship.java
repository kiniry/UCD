/*
 * A re-implementation of the classic C=64 game 'Thrust'.
 * @author "Joe Kiniry (kiniry@acm.org)"
 * @module "COMP 20050, COMP 30050"
 * @creation_date "March 2007"
 * @last_updated_date "April 2008"
 * @keywords "C=64", "Thrust", "game"
 */
package thrust.entities.in_game;

import java.awt.Color;

import thrust.entities.DynamicEntity;
import thrust.entities.FriendEntity;
import thrust.entities.about.Fuelable;
import thrust.entities.behaviors.Tow;

/**
 * The player's main vehicle.
 * @author ursula redmond (ursula.redmond@ucdconnect.ie)
 * @version 10 May 2008
 */
public class Spaceship extends DynamicEntity implements FriendEntity, Fuelable,
    Tow {
  /*
   * @ public invariant (* A spaceship's mass when empty of all fuel is @
   * 10000kg. *); @ public invariant EMPTY_MASS <= mass(); @
   */
  /** A spaceship's mass when empty of all fuel is 10,000kg. */
  public static final int EMPTY_MASS = 10000;

  /*
   * @ public initially (* The spaceship's initial fuel is 1000 units. *); @
   * public initially fuel() == INITIAL_FUEL; @
   */
  /** The spaceship's initial fuel is 1000 units. */
  public static final int INITIAL_FUEL = 1000;

  /** The fuel, begins at initial level. */
  private transient int my_fuel = INITIAL_FUEL;

  /** The tow state of the SpaceShip. */
  private transient boolean my_tow_state;

  /** The shield state of the SpaceShip. */
  private transient boolean my_shield_state;

  /*
   * (non-Javadoc)
   * @see thrust.entities.about.Fuelable#change_fuel_content(int)
   */
  public void change_fuel_content(final int the_fuel_change) {
    if (0 > my_fuel + the_fuel_change) {
      my_fuel = 0;
    } else if ((my_fuel + the_fuel_change) > (int) maximum_fuel()) {
      my_fuel = (int) maximum_fuel();
    } else {
      my_fuel = my_fuel + the_fuel_change;
    }
  }

  /*
   * (non-Javadoc)
   * @see thrust.entities.about.Fuelable#fuel_mass()
   */
  public int fuel_mass() {
    return my_fuel;
  }

  /*
   * (non-Javadoc)
   * @see thrust.entities.about.Fuelable#fuel()
   */
  public int fuel() {
    int answer;
    if (0 <= my_fuel && my_fuel <= maximum_fuel()) {
      answer = my_fuel;
    } else {
      answer = 0;
    }
    return answer;
  }

  /*
   * (non-Javadoc)
   * @see thrust.entities.about.Fuelable#maximum_fuel()
   */
  public int maximum_fuel() {
    return (int) Float.POSITIVE_INFINITY;
  }

  /*
   * (non-Javadoc)
   * @see thrust.entities.about.Fuelable#set_fuel_content(int)
   */
  public void set_fuel_content(final int the_fuel_content) {
    if (the_fuel_content >= 0 && the_fuel_content <= maximum_fuel()) {
      my_fuel = the_fuel_content;
    }
  }

  /*
   * (non-Javadoc)
   * @see thrust.entities.behaviors.Tow#tow()
   */
  public void tow() {
    my_tow_state = true;
  }

  /*
   * (non-Javadoc)
   * @see thrust.entities.behaviors.Tow#towed()
   */
  public boolean towed() {
    return my_tow_state;
  }

  /**
   * Returns true if the shield is up.
   * @return return true if spaceship is shielded
   */
  public boolean shielded() {
    return my_shield_state;
  }

  /**
   * Sets the shield state.
   * @param the_state state of shield
   */
  public void set_shield(final boolean the_state) {
    my_shield_state = the_state;
  }

  /*
   * A spaceship is always white.
   * (non-Javadoc)
   * @see thrust.entities.properties.GameColor#color()
   */
  public Color color() {
    return java.awt.Color.WHITE;
  }

  /*
   * (non-Javadoc)
   * @see thrust.entities.properties.GameColor#color(java.awt.Color)
   */
  public void color(final Color the_color) {
    if (the_color == java.awt.Color.WHITE) {
      my_Color(java.awt.Color.WHITE);
    }
  }

  /*
   * (non-Javadoc)
   * @see thrust.physics.PhysicsInterface#mass()
   */
  public double mass() {
    return super.mass() + fuel_mass() + (towed() ? GoalSphere.MASS : 0);
  }

  /*@ public invariant (* The spaceship is destroyed by the barrier. *);
  @ public invariant (* The spaceship is destroyed by a bullet. *);
  @ public invariant (* The spaceship is destroyed by the factory. *);
  @ public invariant (* The spaceship is destroyed by the fuel pod. *);
  @ public invariant (* If the spaceship is towing the goal sphere,
  @                     and the spaceship is destroyed, the goal
  @                     sphere is also destroyed. *);
  @ public invariant (* The spaceship is destroyed by the gun turret. *);
  @ public invariant (* The spaceship is not affected by space. *);
  @ public invariant (* The spaceship is not affected by a star. *);
  @ public invariant (* The spaceship is destroyed by the terrain. *);
  @ public invariant (* A spaceship's mass is the sum of its empty mass,
  @                     plus the mass of its fuel, plus the mass of
  @                     the goal sphere, if it is being towed. *);
  @ public invariant mass() == EMPTY_MASS + fuel_mass() +
  @                  (towed() ? GoalSphere.MASS : 0);
  @ public invariant (* The spaceship's shape is always that of a ship. *);
  @ public invariant (* The spaceship's color is always white. *);
  @ public invariant color() == java.awt.Color.WHITE;
  @*/
}
