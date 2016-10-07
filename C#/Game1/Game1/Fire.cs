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
    public class Fire
    {
        // FIELDS 
        Sprite bulletF;
        public bool isFire = false;
        private Rectangle fire;
        Sprite balle;
        protected SpriteBatch spritebatch;
        Texture2D gunSheet, currentCrosshair, pixel; //The pixel is used to create an accurate gun nozzle.

        public SpriteEffects spriteEffect = SpriteEffects.None; //Determines whether the image is flipped.

        public Rectangle gunSheetRect; //Used to cycle through the sheet to display the correct gun.
        public Rectangle gunWorldRect; //The rectangle used for world positioning.
        protected int gunSheetX, gunSheetY, gunSheetBoxSize = 100; //Used to select the correct position in the gun sprite sheet.

        //Crosshair code. Guns can disable whether to show a crosshair or not.
        public bool crossHairVisible = true;
        private MouseState mouse;
        private Vector2 mouseVector;
        float rotation;


        // CONSTRUCTOR
        public Fire(Texture2D balle)
        {
            //this.bulletF = new Sprite("bullet", 0,Settings.SCREEN_WIDTH - 200);
            fire = new Rectangle(200, 0, 40, 30);

        }

        public void Update(GameTime gameTime)
        {

            //gunSheet X and Y are assigned values in whatever gun inherits this class.
            gunSheetRect = new Rectangle(gunSheetX, gunSheetY, gunSheetBoxSize, gunSheetBoxSize);

            // On fait une mise à jours pour chaque rotation effectuer en fonction 
            // de la position de la souris
            AimingRotation();

            gunWorldRect = new Rectangle((int)mouse.X, (int)mouse.Y, gunSheetBoxSize, gunSheetBoxSize);
        }

        private void Crosshair()
        {
            mouse = Mouse.GetState();
            mouseVector = new Vector2(mouse.X - currentCrosshair.Width / 2, mouse.Y 
                                                - currentCrosshair.Height / 2);
        }
        
        private void AimingRotation()
        {
            // 
            Vector2 gunPosition = new Vector2(mouse.X, mouse.Y);
            
            Vector2 direction = mouseVector - gunPosition;
            direction.Normalize();

            rotation = (float)Math.Atan2((double)direction.Y, (double)direction.X);
        }


        public void Draw(SpriteBatch spriteBatch)
        {
            //bulletF.Draw(spriteBatch);
        }

    }
}
