using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Game1
{
    public class Player
    {
        // FIELDS 
        public Texture2D texture2, texture3, bulletTexture;

        public Rectangle BondingBox;


        public float bulletDelay;
        public float rotation;


        // BOOL 
        public bool isColliding;
        public bool diedOnce = false;

        public int speed;
        public int health=10;
        public int playerScore;
        public int timeBetweenShoot = 300;


        // Statistic variables
        protected int bulletsFired = 0;
        protected int missilesFired = 0;
        protected int missilesHits = 0;
        protected int bulletsHits = 0;

        protected int missileFireDelay = 1000; // Time between firing two missiles
        protected int missilesCount = 0; // Current fired missiles
        protected int maxNumOfMissiles = 2; // Max number of missiles the player may launch

        protected float missile1Progress = 0;
        protected float missile2Progress = 0;

        public List<Bullet> bulletListe;

        //MouseLocation
        public MouseState mouse = Mouse.GetState();

        public Vector2 origine;
        public Vector2 position;
        public Vector2 mousePosition;
        public Vector2 direction;

        // CONSTRUCTOR
        public Player(Texture2D newTexture, Vector2 position, int health, int bulletFireDelay)
        {

            bulletListe = new List<Bullet>();
            this.speed = 3;
            this.texture2 = null;
            texture3 = null;
            this.health = health;
            this.position = new Vector2(280, 280);
            isColliding = false;
            bulletDelay = 20;
        }

        public void loadContent(ContentManager Content)
        {
            texture2 = Content.Load<Texture2D>("tankBase");
            texture3 = Content.Load<Texture2D>("tankTurret");
            bulletTexture = Content.Load<Texture2D>("bullet");
        }

        // METHODS 

        public void Shoot()
        {
            //if (isShotting)
            //{
            // if (timeSinceLastShot > timeBetweenShots)
            // {
            timeBetweenShoot = 0;
            /* 
                Si le délais de tire est à 0, alors créer une nouvelle balle a 
                la position du joueur , et on le rend visible sur l'écran 
                on l'ajoute à notre liste 
            */
            Bullet newBullet = new Bullet(this.texture2);
            newBullet.velocity = new Vector2((float)Math.Cos(rotation), (float)Math.Sin(rotation)) * 2f;
            newBullet.positions = position + newBullet.velocity * 5;
            newBullet.isVisible = true;

            //if (bullets.Count < 100)
            bulletListe.Add(newBullet);
            //}

        }

        // Après chaque tire on va Updaté le tire 

        public void updateBullet()
        {

            foreach (Bullet bullet in bulletListe)
            {
                //bullet
                bullet.positions += bullet.velocity;
                if (Vector2.Distance(bullet.positions, position) > 500)
                    bullet.isVisible = false;
            }
            for (int i = 0; i < bulletListe.Count; i++)
            {
                if (!bulletListe[i].isVisible)
                {
                    bulletListe.RemoveAt(i);
                    i--;
                }
            }

        }
        // PROPERITES 
        public Vector2 mPosition
        { get { return mousePosition = new Vector2(mouse.X, mouse.Y); } }
           
        public Vector2 mDirection
        { get { return direction = mousePosition - position; direction.Normalize(); } }

        public float mRotation
        { get { return rotation = (float) Math.Atan2(direction.Y, direction.X); } }

        public Vector2 PlayerPosition
        {
            get { return position; }
        }

        public int getHealth
        { get { return health; } }

        public void addScore (int score)
        {
            this.playerScore = score;  
        }
        
        public void Kill()
        {
            if(!diedOnce)
            {
                health = 0;
                diedOnce = true;
            }
        }
        // UDPATE & DRAW 
        public void Draw (SpriteBatch spriteBatch)
        {
            spriteBatch.Draw(texture2, position, Color.White);
            spriteBatch.Draw(texture3, position, Color.White);

            foreach (Bullet b in bulletListe)
                b.Draw(spriteBatch);
        }

        public void Update(GameTime gameTime)
        {
            MouseState state = new MouseState();
            if (state.LeftButton == ButtonState.Pressed)
            {
                Shoot();
            }
            updateBullet();
        }

    }
}
