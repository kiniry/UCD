using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Storage;
using Microsoft.Xna.Framework;




// Author Hugh, Ahmed

namespace TuneBlaster_.Graphics
{
    class Core : Image
    {
        #region Fields (ballsSize, oldRotation, acceleration, balls, maxAcceleration)

        public int ballsSize, looseBallsSize;
        float oldRotation;
        public float acceleration;
        public List<FixedBall> balls;
        public List<MovingBall> looseBalls;
        static float maxAcceleration = 0.05f;
        
        

        #endregion

        #region Main Methods (Core, Initialise, Draw, Update)
        
        /*
         * Constructor for core
         */
        public Core()
        {
            balls = new List<FixedBall>();
            looseBalls = new List<MovingBall>();
        }

        /*
         * Initialise start values
         */
        public override void Initialise(Vector2 mySize, Vector2 myPosition, Game g)
        {
            base.Initialise(mySize, myPosition, g);
            acceleration = 0f;
            oldRotation = 0f;
        }

        /*
         * Draw on the spritebatch for each frame
         */
        public override void Draw(GameTime gameTime)
        {
            base.Draw(gameTime);
            for (int i = 0; i < ballsSize; i++) 
            {
                balls[i].Draw(gameTime);
            }
            
            for (int i = 0; i < looseBallsSize; i++)
            {
                looseBalls[i].Draw(gameTime);
            }
        }

        /*
         * Update variables for each frame
         */
        public void Update(GameTime gameTime, KeyboardState keyBoardState, GamePadState gamePadState)
        {
            for (int i = ballsSize - 1; i >= 0; i--)
            {
                if (balls[i] != null)
                {
                    if (balls[i].IsDead())
                    {
                        balls.Remove(balls[i]);
                        ballsSize--;
                    }
                }
            }

            Move(keyBoardState, gamePadState);            
        }

        #endregion

        #region Action Methods (Move, AddBall, CheckExplosions)

        /*
         * Move the core based on the input
         */
        public void Move(KeyboardState keyBoardState, GamePadState gamePadState)
        {
            oldRotation = rotation;
            SetKeyboardRotation(keyBoardState);
            SetControllerRotation(gamePadState);
            for (int i = 0; i < ballsSize; i++) 
            {
                balls[i].Move(rotation - oldRotation);
            }
            CheckLoose();
            
            for (int i = 0; i < looseBallsSize; i++)
            {
                looseBalls[i].Move();
            }

            CheckLoose();
        }

        /*
         * Add a ball to the core's ball list
         */
        public void AddBall(FixedBall f)
        {
            balls.Add(f);
            ballsSize++;
            CheckExplosions();
            UpdateLoose();
            UpdateLoose();
            UpdateLoose();
            UpdateLoose();
        }

        /*
         * Remove any loose balls that have become fixed
         * */
        public void CheckLoose()
        {
            for (int i = 0; i < looseBallsSize; i++)
            {
                if (!looseBalls[i].IsLive())
                {
                    looseBalls.RemoveAt(i);
                    looseBallsSize--;
                }
            }
        }

        public void UpdateLoose()
        {
            for (int i = 0; i < ballsSize; i++)
            {
                balls[i].CheckSupports();
            }

            for (int i = 0; i < ballsSize; i++)
            {
                if (!balls[i].IsAgainstCore() && balls[i].Unsupported() && !balls[i].IsDead())
                {
                    MovingBall m = new MovingBall(this, balls[i].colour);
                    m.Initialise(balls[i].Size, balls[i].Position, this.game);
                    m.LoadGraphicsContent(balls[i].spriteBatch, balls[i].texture);
                    balls[i].Destroy();
                    balls.Remove(balls[i]);
                    ballsSize--;
                    looseBalls.Add(m);
                    looseBallsSize++;
                }
            }
        }

        /*
         * Check each ball as an explosion candidate, destroying if necessary
         */
        public void CheckExplosions()
        {

            Vector2 where;
      
            for (int i = 0; i < ballsSize; i++)
            {
                if (balls[i] != null)
                {
                    if (balls[i].numInContact > 4)
                    {
                       balls[i].Destroy();

                       where.X = balls[i].Position.X;
                       where.Y = balls[i].Position.Y;
                       Engine.explosion.AddParticles(where);
                       Engine.smoke.AddParticles(where);
                       Engine.greenblast.AddParticles(where);
                       Engine.purpleblast.AddParticles(where);
                       Engine.redblast.AddParticles(where);
                       Engine.blueblast.AddParticles(where);


                    

                        //to put in code for explosion
                      
                    }
                }
            }
        }


        #endregion

        #region Input Methods (SetControllerRotation, SetKeyBoardRotation)

        /*
         * Set a new rotation based on controller input
         */
        public void SetControllerRotation(GamePadState gamePadState)
        {
            if (gamePadState.ThumbSticks.Left.X < 0.0f)
            {
                acceleration += 0.01f * gamePadState.Triggers.Right;
                acceleration *= 0.9f;

                if (acceleration >= maxAcceleration)
                {
                    acceleration = maxAcceleration;
                }
            }
            else if (gamePadState.ThumbSticks.Left.X > 0.0f)
            {
                acceleration -= 0.01f * gamePadState.Triggers.Right;
                acceleration *= 0.9f;

                if (acceleration <= -maxAcceleration)
                {
                    acceleration = -maxAcceleration;
                }
            }
            else
            {
                acceleration *= 0.9f;
            }

            rotation += acceleration;
        }

        /*
         * Set a new rotation based on Keyboard input
         */
        public void SetKeyboardRotation(KeyboardState keyBoardState)
        {
            if (keyBoardState.IsKeyDown(Keys.Right))
            {
                if (keyBoardState.IsKeyDown(Keys.Left))
                {
                    acceleration *= 0.90f;
                }
                acceleration = 0.03f;
            }
            if (keyBoardState.IsKeyDown(Keys.Left))
            {
                acceleration = -0.03f;
            }
            else
            {
                acceleration *= 0.9f;
            }

            rotation += acceleration;

        }

#endregion
    }
}
