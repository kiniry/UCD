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

import thrust.animation.Animatable;
import thrust.animation.AnimatableImp;
import thrust.animation.Animation;
import thrust.entities.EnemyEntity;
import thrust.entities.EnemyEntityImp;
import thrust.entities.NeutralEntity;
import thrust.entities.StaticEntity;
import thrust.entities.behaviors.AI;

/**
 * An enemy factory.
 * @author ursula redmond (ursula.redmond@ucdconnect.ie)
 * Original by Simon.
 * @version 10 May 2008
 */
public class Factory extends StaticEntity
  implements EnemyEntity, Animatable {

  /** Amount factory damage, initially zero. */
  private transient byte my_damage;
  /** Factory's chimney. */
  private transient FactoryChimney my_chimney;
  /** Factory's sphere. */
  private transient FactorySphere my_sphere;
  /** Factory's AI. */
  private transient EnemyEntityImp my_ai;
  /** Factory's animation. */
  private transient AnimatableImp my_animation;

  public AI attack() {
    return my_ai.attack();
  }

  public void attack(final AI the_behavior) {
    my_ai.attack(the_behavior);
  }

  public AI disturb() {
    return my_ai.disturb();
  }

  public void disturb(final AI the_behavior) {
    my_ai.disturb(the_behavior);
  }

  public void animate() {
    my_animation.animate();
  }

  public Animation animation() {
    return my_animation.animation();
  }

  public void animation(final Animation the_animation) {
    my_animation.animation(the_animation);
  }

  public Color color() {
    return chimney().color();
  }

  public void color(final Color the_color) {
    if (the_color == chimney().color()) {
      my_Color(chimney().color());
    }
  }

  /**
   * @return How much damage have you sustained?
   */
  //@ ensures 0 <= \result & \result <= 20;
  public byte damage() {
    return my_damage;
  }

  /**
   * @return What is your chimney?
   */
  public FactoryChimney chimney() {
    return my_chimney;
  }

  /**
   * @return What is your sphere?
   */
  public FactorySphere sphere() {
    return my_sphere;
  }

  /**
   * @param the_damage You have sustained this many units of damage.
   */
  //@ requires 0 <= the_damage;
  //@ ensures damage() == \old(damage() + the_damage);
  public void damage(final byte the_damage) {
    if (the_damage >= 0) {
      my_damage = (byte) (my_damage + the_damage);
    }
  }

  /*@ public invariant (* All factories have exactly one sphere and
    @                     one chimney. *);
    @ public invariant (* A bullet causes 1 unit of damage. *);
    @ public invariant (* Each second 1 unit of damage is eliminated. *);
    @ public initially (* A factory initially has zero units of damage. *);
    @ public initially damage() == 0;
    @ public invariant (* A factory can sustain 20 units of damage before
    @                     it is destroyed. *);
    @ public invariant (* A factory with more than 10 units of damage
    @                     has a chimney that does not smoke. *);
    @ public invariant 10 < damage() ==> !chimney().smoking();
    @ public invariant (* A factory with at most 10 units of damage has
    @                     a smoking chimney. *);
    @ public invariant damage() <= 10 ==> chimney().smoking();
    @*/

  //@ public invariant (* See constraint on color in FactoryChimney. *);
  //@ public invariant color() == chimney().color();

  /**
   * A chimney of a factory.
   * @author Joe Kiniry (kiniry@acm.org)
   * @version 18 April 2008
   */
  public abstract static class FactoryChimney extends StaticEntity
    implements EnemyEntity, Animatable {

    /** Factory's smoking state. */
    private transient boolean my_smoking_state;

    public Color color() {
      return java.awt.Color.GREEN;
    }

    public void color(final Color the_color) {
      if (the_color == java.awt.Color.GREEN) {
        my_Color(java.awt.Color.GREEN);
      }
    }

    /**
     * @return Are you smoking?
     */
    public /*@ pure @*/ boolean smoking() {
      return my_smoking_state;
    }

    /**
     * Your smoking state is dictated by this flag.
     * @param the_smoking_state A flag indicating whether the chimney
     * is smoking or not.
     */
    //@ ensures smoking() <==> the_smoking_state;
    public void smoking(final boolean the_smoking_state) {
      my_smoking_state = the_smoking_state;
    }

    /*@ public invariant (* A factories chimney is the same color as
      @                     its factory. *);
      @ public invariant (* The goal sphere is destroyed by a
      @                     factory's chimney. *);
      @ public invariant (* The spaceship is destroyed by a factory's
      @                     chimney. *);
      @*/
  }

  /**
   * A sphere of a factory.
   * @author Joe Kiniry (kiniry@acm.org)
   * @version 18 April 2008
   */
  public static class FactorySphere extends StaticEntity
    implements NeutralEntity {
    /*@ public invariant (* A factory sphere's color is always green. *);
      @ public invariant color() == java.awt.Color.GREEN;
      @ public invariant (* The goal sphere is not destroyed by a
      @                     factory's sphere. *);
      @*/

    public Color color() {
      return java.awt.Color.GREEN;
    }

    public void color(final Color the_color) {
      if (the_color == java.awt.Color.GREEN) {
        my_Color(java.awt.Color.GREEN);
      }
    }
  }
}
