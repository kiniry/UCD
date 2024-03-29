
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
    /// The Class that calls everything
    /// Defalt Class
    /// Authors Hugh Corrigan, Ahmed Warreth, Dermot Kirby
    /// </summary>
    /// 



    public class Engine : Microsoft.Xna.Framework.Game
    {


  

        public GraphicsDeviceManager graphics;
        ContentManager content;
        public SpriteBatch spriteBatch;

        // a random number generator 
        public static Random Random = new Random();


        Texture2D texture;
        Core core;
        BallManager ball;
        BallGenerator ballGenerator;
        Image background;
        GameAudio music;
        Image frame;
        Vector3 cameraPos;
        Vector3 ballPos;
        GamePadState gamePadState = GamePad.GetState(PlayerIndex.One);
        bool specialModeOn;
        String theNextMode;
        Image gameOver;

        public static ColouredParticle explosion;
        public static ColouredParticle smoke;
        public static ColouredParticle redblast;
        public static ColouredParticle greenblast;
        public static ColouredParticle purpleblast;
        public static ColouredParticle blueblast;
        public static ColouredParticle Rnote;
        public static ColouredParticle Bnote;
        public static ColouredParticle Gnote;
        public static ColouredParticle Pnote;


       
        float elapsedTime = 30;
        Vector2 timePosition = new Vector2(45, 100);
      



        // this bool controls the vibration in the gamepad

        public static bool blast = false;
        public static int blastTime = 0;


        public static int Score;
        SpriteFont lucidaConsole;
        SpriteFont specialmode;
        Vector2 scorePosition = new Vector2(80, 50);


        Image.value colour;

        public Engine()
        {

            graphics = new GraphicsDeviceManager(this);
            content = new ContentManager(Services);
            core = new Core();
            ballGenerator = new BallGenerator(core, this);
            ball = new BallManager(core, this, ballGenerator);
            background = new Image();
            frame = new Image();
            music = new GameAudio();
            cameraPos = new Vector3(400f, 300f, 0f);
            gameOver = new Image();

            this.graphics.PreferredBackBufferWidth = 1280;
            this.graphics.PreferredBackBufferHeight = 720;
            //this.graphics.IsFullScreen;

            // sets what texture each methid should load
            //and how many effects it needs to draw (this, num of effects, which text valuse to load)

            explosion = new ColouredParticle(this, 3, 5);
            Components.Add(explosion);
            smoke = new ColouredParticle(this, 3, 6);
            Components.Add(smoke);
            redblast = new ColouredParticle(this, 3, 1);
            Components.Add(redblast);
            greenblast = new ColouredParticle(this, 3, 4);
            Components.Add(greenblast);
            purpleblast = new ColouredParticle(this, 3, 3);
            Components.Add(purpleblast);
            blueblast = new ColouredParticle(this, 3, 2);
            Components.Add(blueblast);
            Rnote = new ColouredParticle(this, 3, 7);
            Components.Add(Rnote);
            Bnote = new ColouredParticle(this, 3, 8);
            Components.Add(Bnote);
            Gnote = new ColouredParticle(this, 3, 9);
            Components.Add(Gnote);
            Pnote = new ColouredParticle(this, 3, 10);
            Components.Add(Pnote);




            //this.graphics.IsFullScreen = true;
        }



        // gives a random float between two values
        public static float RandomBetween(float min, float max)
        {
            return min + (float)Random.NextDouble() * (max - min);
        }





        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            core.Initialise(new Vector2(150f, 150f), new Vector2(620f, 360f), this);
            background.Initialise(new Vector2(1200, 800), new Vector2(600, 400), this);
            ball.Initialise();
            ballGenerator.Initialise();
            frame.Initialise(new Vector2(1280, 720), new Vector2(640, 360), this);
            base.Initialize();
            music.Initialise();
            gameOver.Initialise(new Vector2(720, 720), new Vector2(640, 0),  this);
            music.listener.Position = cameraPos;
            specialModeOn = false;
            theNextMode = "Special Mode in:";
            //Content.RootDirectory = "Content";
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

                texture = content.Load<Texture2D>(@"Resources\Textures\space-background");
                background.LoadGraphicsContent(spriteBatch, texture);
                texture = content.Load<Texture2D>(@"Resources\Textures\Core");
                core.LoadGraphicsContent(spriteBatch, texture);
                texture = content.Load<Texture2D>(@"Resources\Textures\sidemenu");
                frame.LoadGraphicsContent(spriteBatch, texture);
                ball.LoadGraphicsContent(spriteBatch);
                ballGenerator.LoadGraphicsContent(spriteBatch, texture);
                lucidaConsole = Content.Load<SpriteFont>("Fonts/Lucida Console");
                texture = Content.Load<Texture2D>(@"Resources\Textures\gameover");
                gameOver.LoadGraphicsContent(spriteBatch, texture);
                specialmode = Content.Load<SpriteFont>("Fonts/ArialMedium");
       

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
            if (!core.GameOver())
            {
                // Allows the default game to exit on Xbox 360 and Windows
                if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                    this.Exit();

                colour = core.Update(gameTime, Keyboard.GetState(), GamePad.GetState(PlayerIndex.One));
                ballPos = core.getPos();

                if (blast) GamePad.SetVibration(PlayerIndex.One, 1.0f, 1.0f);
                if (blastTime > 0) blastTime--;
                if (blastTime == 0)
                {
                    blast = false;
                    GamePad.SetVibration(PlayerIndex.One, 0.0f, 0.0f);

                }
                ballGenerator.Update();
                ball.Update(gameTime);
                music.UpdateAudio();
                base.Update(gameTime);

                this.elapsedTime -= (float)gameTime.ElapsedGameTime.TotalSeconds;
                //Console.WriteLine(elapsedTime);
                if ((int)elapsedTime == 0)
                {
                    if (!specialModeOn)
                    {
                        core.NextSpecial();
                        specialModeOn = true;
                        theNextMode = "Normal Mode in:";
                        elapsedTime = 20;
                        music.pauseAllCues();

                        if (core.blackwhite)
                        {
                            music.SuspendThreads();
                            music.bwCue.Play();
                        }

                        if (core.searchLightOn)
                        {
                            music.SuspendThreads();
                            music.searchCue.Play();
                        }
                    }

                    else
                    {
                        core.ResetSpecial();
                        specialModeOn = false;
                        theNextMode = "Special Mode in:";
                        elapsedTime = 30;
                        music.resumeAllCues();
                        music.ResumeThreads();
                        music.disposeSpecialCues();
                        music.resetSpecialCues();
                    }

                    
                }

                if (!specialModeOn)
                {
                    if (colour == Image.value.green)
                    {
                        music.setPosition(ballPos);
                        music.InstrChanger(Image.value.green);
                    }

                    if (colour == Image.value.blue)
                    {
                        music.setPosition(ballPos);
                        music.InstrChanger(Image.value.blue);
                    }

                    if (colour == Image.value.red)
                    {
                        music.setPosition(ballPos);
                        music.InstrChanger(Image.value.red);
                    }

                    if (colour == Image.value.purple)
                    {
                        music.setPosition(ballPos);
                        music.InstrChanger(Image.value.purple);
                    }
                }
            }

            if (core.GameOver())
            {
                if (gameOver.Position != new Vector2(640, 360))
                {
                    music.pauseAllCues();
                    music.SuspendThreads();

                    if (!music.gameOverCue.IsPlaying)
                    {
                        music.setPosition(ballPos);
                        music.deathCue.Apply3D(music.listener, music.emitter);
                        music.deathCue.Play();
                        music.gameOverCue.Play();
                    }

                    gameOver.Position = new Vector2(gameOver.Position.X, gameOver.Position.Y + 2);
                }
            }
        }

    


        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            graphics.GraphicsDevice.Clear(Color.Black);
            spriteBatch.Begin();
            background.Draw(gameTime);
            core.Draw(gameTime);
            ball.Draw(gameTime);
            core.DrawSearchLight(gameTime);
            frame.Draw(gameTime);
            ballGenerator.Draw(gameTime);
            spriteBatch.End();




            spriteBatch.Begin(SpriteBlendMode.AlphaBlend,
                 SpriteSortMode.Immediate, SaveStateMode.None);
            spriteBatch.DrawString(lucidaConsole, "Score: " + Score,
                                   scorePosition, Color.WhiteSmoke);
            spriteBatch.End();


            spriteBatch.Begin(SpriteBlendMode.AlphaBlend,
                SpriteSortMode.Immediate, SaveStateMode.None);
            spriteBatch.DrawString(specialmode, theNextMode + elapsedTime.ToString("N0"),
                                   timePosition, Color.Pink);

            if (core.GameOver())
            {
                gameOver.Draw(gameTime);
            }
            spriteBatch.End();

            


            base.Draw(gameTime);
        }
    }
}
