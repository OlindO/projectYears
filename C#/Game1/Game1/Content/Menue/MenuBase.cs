using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Game1
{
    public abstract class MenuBase
    {
        // FIELDS 

        // CONSTRUCTOR
        protected MenuBase()
        {
        }

        // METHODS

        // UPDATE & DRAW 
        public abstract void Update(GameTime gameTime, Input input);
        public abstract void Draw(SpriteBatch spriteBatch);
    }
}
