using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System;
using System.Collections.Generic;


namespace Game1
{
    public class Game1 : Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        
        //Input input;
        public Texture2D pv,texture, texture2, texture3, motherShip;

        // BOOL 
        public bool isShotting;
        // Clavier
        KeyboardState oldState;
        KeyboardState currentState;

       

        // Pour la souris et sont orientation
        Vector2 position;
        float rotation, rotation2;
        float speed = 3;

        // Le deplacement du tank 
        private Vector2 _TankDeplacement;

        // Taille 
        private int _screnWidth;
        private int _screnHeigth;
        float scale = .5f;
        double timeSinceLastShot;

        // Instance
        public MenuGame currentMenue;
        public Fire img;
        public Enemy clEnemy;
        
        // Bullets Field
        public int bulletDelay = 0;
        public int timeBetweenShots = 0; // Ce sont les millisecondes 
        public int bulletFireDeley = 0;
        public int shootCounter = 0; 


        public TimeSpan fireTime = TimeSpan.FromSeconds(.15f);

        public float shooTimer = 1000f;
        // Bullets List 
        List<Bullet> bullets = new List<Bullet> ();
        
        // USELESS
        Bullet btbt;
        public Player play;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
            graphics.PreferredBackBufferWidth = Settings.SCREEN_WIDTH;
            graphics.PreferredBackBufferHeight = Settings.SCREEN_HEIGHT;
            this.IsMouseVisible = Settings.IS_MOUSE_VISIBLE;
        }

        protected override void Initialize()
        {
            timeSinceLastShot = 2;
            _screnWidth = Window.ClientBounds.Width;
            _screnHeigth = Window.ClientBounds.Height;
            this.oldState = Keyboard.GetState();
            this.currentState = this.oldState;
            position = new Vector2(280, 280);
            rotation = 0.1f;
            
            Window.Title = " MONOGAME - TANK "; 
            //imageTank = new Fire();
            //this.currentMenue = new MenuGame();

            base.Initialize();
            
        }

        protected override void LoadContent()
        {
            spriteBatch = new SpriteBatch(GraphicsDevice);
            img = new Fire(Content.Load<Texture2D>("bullet"));
            pv = Content.Load<Texture2D>("3W2KGC1");
            texture = Content.Load<Texture2D>("backgroundBlue");
            texture2 = Content.Load<Texture2D>("tankBase");
            texture3 = Content.Load<Texture2D>("tankTurret");
            motherShip = Content.Load<Texture2D>("MotherShip");
            
            //Ressource.LoadImages(this.Content);        
       
        }

        protected override void UnloadContent()
        {
            // TODO
        }

        // PROPERTIES 
        public List<Bullet> getBulletList
        { get { return bullets; } }

        


        protected override void Update(GameTime gameTime)
        {
            timeSinceLastShot =+ gameTime.ElapsedGameTime.TotalSeconds;

            if (Keyboard.GetState().IsKeyDown(Keys.Escape))
                Exit();
            //this.currentMenue.Update(gameTime, new Input(this.oldState, this.currentState));
            this.oldState = this.currentState;
            IsMouseVisible = true;
           
            KeyboardState keyboardState = Keyboard.GetState();
            // Current Moving
            if (keyboardState.IsKeyDown(Keys.Q))
                if(position.X >= 0)
                    position.X -= speed;
            
            if (keyboardState.IsKeyDown(Keys.D))
                if((position.X < (Settings.SCREEN_WIDTH- texture2.Width) ) || (position.X < (Settings.SCREEN_WIDTH - texture3.Width)))
                    position.X += speed; 

            if (keyboardState.IsKeyDown(Keys.Z))
                if ((position.Y < (Settings.SCREEN_HEIGHT - texture2.Width)) || (position.X < (Settings.SCREEN_HEIGHT - texture3.Width)))
                    position.Y -= speed;
            if (keyboardState.IsKeyDown(Keys.S))
                if ((position.Y < (Settings.SCREEN_HEIGHT - texture2.Width)) || (position.X < (Settings.SCREEN_HEIGHT - texture3.Width)))
                    position.Y += speed;


      
            //MouseLocation
            MouseState mouse = Mouse.GetState();
            Vector2 mousePosition = new Vector2(mouse.X, mouse.Y);

            //Direction
            Vector2 direction = mousePosition - position;
            direction.Normalize();
            rotation = (float)(Math.Atan2((double)direction.Y, 
                                                    (double)direction.X));
            rotation2 = 0;

            double testDuring;
            //testDuring += gameTime.ElapsedGameTime.TotalSeconds;

                if (mouse.LeftButton == ButtonState.Pressed)
                {
                    if (timeSinceLastShot >= 1)
                    {
                        Shoot();
                     //testDuring;
                        timeSinceLastShot = 0;
                        //timeSinceLastShot++;
                    }
                    else
                    {
                        shootCounter++; 
                    }    
                }
                updateBullet();

            //**********************************
           //clEnemy.Update(gameTime); 

            img.Update(gameTime);
            //**********************************
            base.Update(gameTime);
        }
        public void updateBullet()
        {
            
            foreach (Bullet bullet in bullets)
            {
                //bullet
                bullet.positions += bullet.velocity;
                if (Vector2.Distance(bullet.positions, position) > 700)
                    bullet.isVisible = false; 
            }
            for(int i = 0; i < bullets.Count; i++)
            {
                if(!bullets[i].isVisible)
                {
                    bullets.RemoveAt(i);
                    i--;
                }
            }
         }

        public void Shoot()
        {
                        timeSinceLastShot = 0;
                    // Si le délais de tire est à 0, alors créer une nouvelle balle a 
                    // la position du joueur , et on le rend visible sur l'écran 
                    // on l'ajoute à notre liste
                        Bullet newBullet = new Bullet(Content.Load<Texture2D>("bullet"));
                        newBullet.velocity = new Vector2((float)Math.Cos(rotation), 
                                            (float)Math.Sin(rotation)) * 3f;
                        newBullet.positions = position + newBullet.velocity * 5;
                        newBullet.isVisible = true;

                        bullets.Add(newBullet);
           
                
         }
        
      
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

   
            spriteBatch.Begin();

            spriteBatch.Draw(texture, Vector2.Zero);
            spriteBatch.Draw(pv, new Vector2(0, 0), null, Color.White, 0, new Vector2(0, 0), scale, SpriteEffects.None, 0);
           
             // BulletDrawing
            foreach (Bullet bullet in bullets)
                bullet.Draw(spriteBatch);
                  

            //img.Draw(spriteBatch);
            
            // TURRET 
            spriteBatch.Draw(texture2, position,
                                         null,
                                         Color.White,
                                         rotation2,
                                         new Vector2(
                                             texture2.Width / 2,
                                             texture2.Height / 2),
                                         1.0f,
                                         SpriteEffects.None,
                                         1.0f);
            // Tank Base
            spriteBatch.Draw(texture3, position,
                                         null,
                                         Color.White,
                                         rotation,
                                         new Vector2(
                                             texture3.Width / 2,
                                             texture3.Height / 2),
                                         1.0f,
                                         SpriteEffects.None,
                                         1.0f);
            
            //this.imageTank.Draw(spriteBatch);

            //clEnemy.Draw(spriteBatch);

            spriteBatch.End();
            base.Draw(gameTime);
            
        }
    }
}
