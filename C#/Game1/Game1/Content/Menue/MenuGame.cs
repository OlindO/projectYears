using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Game1
{
    public class MenuGame 
    {
        // FIELDS 
        private Sprite backgroundRoom;
        private Texture2D tankBaseI;
        public MenuGame()
            :base()
        {
            //backgroundRoom = new Sprite("bullet",
                         //   (Settings.SCREEN_WIDTH - 240) / 2,
                          //   Settings.SCREEN_HEIGHT - 160);
        }

        public void Update(GameTime gameTime)
        {
        }
        public void Draw(SpriteBatch spriteBatch)
        {
            backgroundRoom.Draw(spriteBatch);            
        }

     
    }
}
