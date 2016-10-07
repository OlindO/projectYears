using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Game1
{
    public class Bullet 
    {
        // FIELDS // VARIABLES ***
        
        public Texture2D texture;

        public Vector2 positions;
        public Vector2 velocity;
        public Vector2 origine;
        public float speed;
       

        //BOOL
        public bool isVisible;
        public bool isShotting=true;

        // 
        public Rectangle boudingBox;
        // State 
        public int damage;
        public int timeBetweenShots=0; // Ce sont les millisecondes     
        // COSNTRUCTOR
        public Bullet (Texture2D newTexture)
        {          
            speed = 10;  
            texture = newTexture;
            isVisible = false;
            damage = 1;
        }

        // SURCHAGER AU CAS OU *

      
        // PROPERTIES 
        public int Damage
        {
            get { return damage; }
            set { damage = value;}
        }

        public bool IsShooting { get; set; }

        // METHODS
        public void Deplacement()
        {

        }
        public void Update (GameTime gameTime)
        {
            timeBetweenShots += gameTime.ElapsedGameTime.Milliseconds;
            
        }

        // UPDATE & DRAW 
        public void Draw(SpriteBatch spriteBatch)
        {
            spriteBatch.Draw(texture, positions, null, Color.White, 0f, origine, 1f, SpriteEffects.None, 0);
        }
    }
}
