
#region Using Statements
using System;
using System.Collections.Generic;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Storage;
using TuneBlaster_.Graphics;
#endregion

namespace TuneBlaster_
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Engine : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        ContentManager content;
        SpriteBatch spriteBatch;
        Texture2D texture;
        Core core;
        BallManager ball;
        GamePadState gamePadState = GamePad.GetState(PlayerIndex.One);
        
        public Engine()
        {
            graphics = new GraphicsDeviceManager(this);
            content = new ContentManager(Services);
            core = new Core(gamePadState);
            ball = new BallManager(core);
        }


        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            core.Initialise(new Vector2(150f, 150f), new Vector2(400f, 400f));
            ball.Initialise();
            base.Initialize();
        }


        /// <summary>
        /// Load your graphics content.  If loadAllContent is true, you should
        /// load content from both ResourceManagementMode pools.  Otherwise, just
        /// load ResourceManagementMode.Manual content.
        /// </summary>
        /// <param name="loadAllContent">Which type of content to load.</param>
        protected override void LoadGraphicsContent(bool loadAllContent)
        {
            if (loadAllContent)
            {
                spriteBatch = new SpriteBatch(graphics.GraphicsDevice);
                texture = content.Load<Texture2D>(@"Resources\Textures\Arrow");
                core.LoadGraphicsContent(spriteBatch, texture);
                texture = content.Load<Texture2D>(@"Resources\Textures\Ball");
                ball.LoadGraphicsContent(spriteBatch, texture);
                // TODO: Load any ResourceManagementMode.Automatic content
            }

            // TODO: Load any ResourceManagementMode.Manual content
        }


        /// <summary>
        /// Unload your graphics content.  If unloadAllContent is true, you should
        /// unload content from both ResourceManagementMode pools.  Otherwise, just
        /// unload ResourceManagementMode.Manual content.  Manual content will get
        /// Disposed by the GraphicsDevice during a Reset.
        /// </summary>
        /// <param name="unloadAllContent">Which type of content to unload.</param>
        protected override void UnloadGraphicsContent(bool unloadAllContent)
        {
            if (unloadAllContent == true)
            {
                content.Unload();
            }
        }


        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            // Allows the default game to exit on Xbox 360 and Windows
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();
            core.Update(gameTime, Keyboard.GetState());
            ball.Update(gameTime);
            // TODO: Add your update logic here

            base.Update(gameTime);
        }


        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            graphics.GraphicsDevice.Clear(Color.Black);
            spriteBatch.Begin();
            core.Draw(gameTime);
            ball.Draw(gameTime);
            spriteBatch.End();

            base.Draw(gameTime);
        }
    }
}