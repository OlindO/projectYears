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
    public class Sprite
    {
        public Vector2 position;
        private Texture2D texture;
        Vector2 direction;
        
        Vector2 positionFire = new Vector2(280, 280);

        float speed = 10;

        public int health = 3; 
        // CONSTRUCTOR
        public Sprite(Texture2D texture)
        {
           this.texture = texture;
        }

        // PROPERTIES
        //public static MouseState LeftButton { get { return LeftButton; } }

        // Initialize
        public void Initialize()
        {
            //MouseState mouse = Mouse.GetState();
        }
        // METHODS 

        // PROPERTIES
        public virtual float GetHealth
        {
            get
            {
                return health;
            }
        }

        public void Update(GameTime gameTime)
        {
            
        }
        public void Draw(SpriteBatch sp)     
        {
            sp.Draw(texture, position, null, Color.White); 
        }
       }
    }