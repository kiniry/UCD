/*
 * A re-implementation of the classic C=64 game 'Thrust'.
 *
 * @author "Joe Kiniry (kiniry@acm.org)"
 * @module "COMP 20050, COMP 30050"
 * @creation_date "March 2007"
 * @last_updated_date "April 2008"
 * @keywords "C=64", "Thrust", "game"
 */

package thrust.animation;

import thrust.display.*;

/**
 * The frames in an animation.
 * @author Joe Kiniry (kiniry@acm.org)
 * @version 18 April 2008
 */
public interface Animation 
{
	 gameFrame[] myFrames();
}
