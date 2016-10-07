using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Game1
{
    public class Enemy : Game1
    {
        // FIELD *********************

        TimeSpan previousSpawnTime;
        TimeSpan UnitsPerSecond;

        public SpriteBatch spriteBatch;

        public int SpawnUnitsCount;
        public int maximumTankSpawning = 5;
        public int LIFE = 5;
        public int damage = 1; 

        // BOOL 
        public bool spawn;
        public bool noMoreEnemy = false;
       
        // LIST
        private List <Enemy> listEnemy = new List<Enemy>();
        public  List <Bullet> bulletObject = new List<Bullet>();

        // WINDOWS BOUNDS
        private int _screnWidth;
        private int _screnHeigth;

        // POSITION 
        public Vector2 enemyVelocity;
        public Vector2 enemyPosition;
        public Vector2 origineEnemyPosition; // .? Origine Of starting position
        public Vector2 Target;
        public Vector2 TargDirection;

        public Rectangle enemyRectangle;
        public Rectangle projectilesRectangle;
        
        // SPRITE 
        public Texture2D enemyTexture;
        public Texture2D bulletTextureEnemy;

        // ENTITY 
        Player playCurrent;
        Game1 dataBullet;

        // RAND
        Random rand;

        // CONSTRUCTOR ****************
        public Enemy(Texture2D enemyTexture, int x , int y )
        {
            spawn = true;
            SpawnUnitsCount++;
            enemyPosition = new Vector2(x, y);
            LIFE = 3;   
        }
        
        public Enemy(Texture2D enemyTexture,Texture2D secondTexture, int x, int y)
        {
            spawn = true;
            SpawnUnitsCount++;
            enemyPosition = new Vector2(x, y);
            LIFE = 3;
        }
        
        protected override void Initialize()
        {
            _screnWidth = Window.ClientBounds.Width;
            _screnHeigth = Window.ClientBounds.Height;
            UnitsPerSecond = TimeSpan.FromSeconds(2.0f);
            enemyTexture = Content.Load<Texture2D>("MotherShip");
            bulletTextureEnemy = Content.Load<Texture2D>("MissileGauge");
            rand = new Random();
            base.Initialize();
        }
        

        // METHODS
        public void Move()
        {
            enemyRectangle = new Rectangle((int)enemyPosition.X, (int)enemyPosition.Y, 
                                                    enemyTexture.Width, enemyTexture.Height);
            enemyPosition += enemyVelocity;
            origineEnemyPosition = new Vector2(enemyRectangle.Width/2, 
                                               enemyRectangle.Height/2);
        }
      
        public Vector2 generateRandomPosition()
        {

            int randY = rand.Next(100, 400);
            int randX = rand.Next(100, 300);
            for (int i =0; i<listEnemy.Count(); i++)
            {
                if(listEnemy.Count() < 8)
                    listEnemy[i].enemyPosition = new Vector2(randX, randY);
            }
            return enemyPosition;
        }

        public Vector2 getEnemyPosition
        { get { return enemyPosition; } }

        // AI 
        public void tryShooting()
        {
            Target = playCurrent.PlayerPosition;
            TargDirection = playCurrent.mDirection;


        }

        public void UpdateColision()
        {
            
            bulletObject = dataBullet.getBulletList;
            foreach (Bullet bullet in bulletObject)
            {
                // On crée deux rectangle pour determiner les colisions avec l'un et l'autre
                enemyRectangle = new Rectangle((int)enemyPosition.X, (int)enemyPosition.Y,
                                            enemyTexture.Width, enemyTexture.Height);
                projectilesRectangle = new Rectangle((int)bullet.positions.Y, (int)bullet.positions.X,
                                            bulletTextureEnemy.Width, bulletTextureEnemy.Height);

                

                if(enemyRectangle.Intersects(projectilesRectangle))
                {
                    playCurrent.health -= damage; 
                }
            }    
        }
        
        // PROPERTIES
        public SpriteBatch getSpriteBatch 
        { get { return spriteBatch; } }

        public int getHealth
        { get { return LIFE; } }

        public bool isGameFinish
        { get { return noMoreEnemy; } }
        
        
        private void addEnemy()
        {
            Vector2 position = new Vector2(GraphicsDevice.Viewport.Width 
                                                    + enemyTexture.Width / 2);
            Enemy enemy = new Enemy(this.texture2,this.texture3,  45, 45);
            listEnemy.Add(enemy);
        }

        // DRAW & UPDATE 
        protected override void Update (GameTime gameTime)
        {
            int augmenter = 0;
            
            if (gameTime.TotalGameTime - previousSpawnTime > UnitsPerSecond)
            {
                previousSpawnTime = gameTime.TotalGameTime;
                addEnemy();
            }
            for(int i = 0; i<listEnemy.Count(); i++)
            {
                listEnemy[i].Update(gameTime);
                if(listEnemy[i].spawn == false) // alouer la mémoire à notre liste 
                    if(listEnemy[i].getHealth <= 0)
                    {
                        playCurrent.addScore(augmenter + 1);
                        listEnemy.RemoveAt(i);
                    }     
            }

            if(listEnemy.Count > maximumTankSpawning)
               noMoreEnemy = false; 
            else
               noMoreEnemy = true;  

            base.Update(gameTime);

        }

  
        // UPDATE & DRAW 

        public void Update(GraphicsDevice graphics)
        {
            enemyPosition += enemyVelocity;

            if (enemyPosition.Y <= 0 || enemyPosition.Y >= graphics.Viewport.Height 
                                                                        - texture.Height)
            {
                enemyVelocity.Y = -enemyVelocity.Y;
            }

            if (enemyPosition.X < 0 - texture.Width)
            {
                spawn = false;
            }
        }

        public void Draw (SpriteBatch spriteBatch)
        {
            enemyPosition = generateRandomPosition();
            foreach (Enemy enemy in listEnemy)
            {
                spriteBatch.Draw(enemyTexture, enemyPosition,null);                               
            }
        }
    }
}
