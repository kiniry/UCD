using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace TuneBlaster_.Graphics
{
    /// <summary>
    /// The class for all balls that are not stationary, that gravitate towards the core
    /// Author Hugh Corrigan
    /// </summary>
    class MovingBall : Image
    {    
        #region Fields (core, live, angles, colour)

        protected Core core;
        bool live;
        float hypotenuse, x, y;
        public value colour;
        FixedBall collisionBall;
        MovingBall toFollow;
        Vector2 oldPosition;
        protected Texture2D bw;
        public Texture2D colourTexture;
        Game game;
        bool blackwhite;

        #endregion

        #region Main Methods (MovingBall, Initialise)

        /*
         * Constructor
         */
        public MovingBall(Core c, value v)
        {
            core = c;
            colour = v;
        }

        /*
         * Initialise starting values
         */
        public override void Initialise(Game g)
        {
            base.Initialise(g);
            oldPosition = Position;
            live = true;
            game =g;
            blackwhite = false;
            bw = game.Content.Load<Texture2D>(@"Resources\Textures\blackwhite");
        }

        /*
         * Initialise starting values with specified position and size
         */
        public void Initialise(Vector2 size, Vector2 position, Game g)
        {
            base.Initialise(size, position, g);
            live = true;
        }

        #endregion

        #region Return Methods (IsLive)

        public bool IsLive()
        {
            return live;
        }

        #endregion

        #region Action Methods (Collide, Move)

        /*
         * Check for Collisions with the FixedBalls and the Core
         */
        public bool Collide()
        {
            if (Collide(core)) {
                return true;
            }

            for (int i = 0; i < core.ballsSize; i++) 
            {
                if (Collide(core.balls[i])) 
                {
                    collisionBall = core.balls[i];
                    return true;
                }
            }
            return false;
        }

        public void SwitchBlack()
        {
            if (core.blackwhite)
            {
                if (!blackwhite)
                {
                    colourTexture = texture;
                    texture = bw;
                    blackwhite = true;
                }
            }

            else
            {
                if (blackwhite)
                {
                    texture = colourTexture;
                    blackwhite = false;
                }
            }
        }

        /*
         * Move towards the core
         */
        public virtual void Move()
        {
            if (colourTexture == null)
            {
                colourTexture = texture;
            }
            SwitchBlack();
            if (toFollow == null)
            {
                if (!Collide())
                {
                    oldPosition = Position;
                    hypotenuse = Vector2.Distance(this.Position, core.Position);
                    x = core.Position.X - this.Position.X;
                    y = core.Position.Y - this.Position.Y;

                    Position = new Vector2(2 * (x / hypotenuse) + Position.X, 2 * (y / hypotenuse) + Position.Y);
                    //move with gravity
                }
                else
                {
                    FixedBall f = new FixedBall(core, colour);
                    f.Initialise(size, Position, game);
                    if (collisionBall == null)
                    {
                        f.SetAgainstCore();
                        f.DoAngles();
                    }
                    else
                    {
                        f.CorrectPostion(collisionBall);
                        f.DoAngles();
                        f.AddSupport(collisionBall);
                        f.SetCollisionBall(collisionBall);
                    }
                    f.UpdateContactBalls();
                    f.LoadGraphicsContent(spriteBatch, colourTexture);
                    core.AddBall(f);
                    live = false;
                }
            }
            else
            {
                if (!Collide())
                {
                    Position += (toFollow.Position - toFollow.oldPosition);
                }
                else
                {
                    FixedBall f = new FixedBall(core, colour);
                    f.Initialise(size, Position, game);
                    if (collisionBall == null)
                    {
                        f.SetAgainstCore();
                        f.DoAngles();
                    }
                    else
                    {
                        f.CorrectPostion(collisionBall);
                        f.DoAngles();
                        f.AddSupport(collisionBall);
                        f.SetCollisionBall(collisionBall);
                    }
                    f.UpdateContactBalls();
                    f.LoadGraphicsContent(spriteBatch, colourTexture);
                    core.AddBall(f);
                    live = false;
                }
            }
        }

        #endregion
    }
}
