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
import java.awt.Shape;

import thrust.animation.Animatable;
import thrust.animation.Animation;
import thrust.entities.EnemyEntity;
import thrust.entities.NeutralEntity;
import thrust.entities.StaticEntity;
import thrust.entities.behaviors.AI;

/**
 * An enemy factory.
 * @author Joe Kiniry (kiniry@acm.org)
 * @version 18 April 2008
 */
public class Factory extends StaticEntity
  implements EnemyEntity, Animatable {
  /**
   * @return How much damage have you sustained?
   */
  //@ ensures 0 <= \result & \result <= 20;
  public /*@ pure @*/ byte damage() {
    assert false; //@ assert false;
    return 0;
  }

  /**
   * @return What is your chimney?
   */
  public /*@ pure @*/ FactoryChimney chimney() {
    assert false; //@ assert false;
    return null;
  }

  /**
   * @return What is your sphere?
   */
  public /*@ pure @*/ FactorySphere sphere() {
    assert false; //@ assert false;
    return null;
  }

  /**
   * @param the_damage You have sustained this many units of damage.
   */
  //@ requires 0 <= the_damage;
  //@ ensures damage() == \old(damage() - the_damage);
  public void damage(byte the_damage) {
    assert false; //@ assert false;
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
    @ public invariant 10 < damage() ==> !chimney.smoking();
    @ public invariant (* A factory with at most 10 units of damage has
    @                     a smoking chimney. *);
    @ public invariant damage() <= 10 ==> chimney.smoking();
    @*/

  //@ public invariant (* See constraint on color in FactoryChimney. *);
  //@ public invariant color() == chimney().color();

  /**
   * A chimney of a factory.
   * @author Joe Kiniry (kiniry@acm.org)
   * @version 18 April 2008
   */
  public class FactoryChimney extends StaticEntity
    implements EnemyEntity, Animatable {

    public FactoryChimney() {
    }
    /**
     * @return Are you smoking?
     */
    public /*@ pure @*/ boolean smoking() {
      assert false; //@ assert false;
      return false;
    }

    /**
     * Your smoking state is dictated by this flag.
     * @param the_smoking_state A flag indicating whether the chimney
     * is smoking or not.
     */
    //@ ensures smoking() <==> the_smoking_state;
    public void smoking(boolean the_smoking_state) {
      assert false; //@ assert false;
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
  public class FactorySphere extends StaticEntity
    implements NeutralEntity {
    /*@ public invariant (* A factory sphere's color is always green. *);
      @ public invariant color() == thrust.entities.properties.GameColor.GREEN;
      @ public invariant (* The goal sphere is not destroyed by a
      @                     factory's sphere. *);
      @*/
  }

  public double[] acceleration() {
    // TODO Auto-generated method stub
    return null;
  }

  public double mass() {
    // TODO Auto-generated method stub
    return 0;
  }

  public double momentum() {
    // TODO Auto-generated method stub
    return 0;
  }

  public double[] velocity() {
    // TODO Auto-generated method stub
    return null;
  }

  public void render() {
    // TODO Auto-generated method stub
    
  }

  public Shape shape() {
    // TODO Auto-generated method stub
    return null;
  }

  public void shape(Shape the_shape) {
    // TODO Auto-generated method stub
    
  }

  public String shape_name() {
    // TODO Auto-generated method stub
    return null;
  }

  public byte state() {
    // TODO Auto-generated method stub
    return 0;
  }

  public void state(byte the_state) {
    // TODO Auto-generated method stub
    
  }

  public AI attack() {
    // TODO Auto-generated method stub
    return null;
  }

  public void attack(AI the_behavior) {
    // TODO Auto-generated method stub
    
  }

  public AI disturb() {
    // TODO Auto-generated method stub
    return null;
  }

  public void disturb(AI the_behavior) {
    // TODO Auto-generated method stub
    
  }

  public void animate() {
    // TODO Auto-generated method stub
    
  }

  public Animation animation() {
    // TODO Auto-generated method stub
    return null;
  }

  public void animation(Animation the_animation) {
    // TODO Auto-generated method stub
    
  }

  public double gravitational_constant() {
    // TODO Auto-generated method stub
    return 0;
  }

  public double orientation() {
    // TODO Auto-generated method stub
    return 0;
  }

  public double[] position() {
    // TODO Auto-generated method stub
    return null;
  }

  public void simulate(double some_seconds) {
    // TODO Auto-generated method stub
    
  }

  public Color color() {
    // TODO Auto-generated method stub
    return null;
  }

  public void color(Color the_color) {
    // TODO Auto-generated method stub
    
  }
}
