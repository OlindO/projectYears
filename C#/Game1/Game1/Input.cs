using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace Game1
{
    public class Input : Game1
    {

        // FIELD 
        
        private static MouseState mouseState;
      
        Vector2 position;
        float rotation;
        KeyboardState oldState;
        KeyboardState currentState;
        // Taille
        private int _screnWidth;
        private int _screnHeigth;
        // Vitesse
        float speed = 3;
        // CONSTRUCTOR 

        public Input(KeyboardState oldState, KeyboardState state)
        {
            this.oldState = oldState;
            this.currentState = state;
        }

        // METHODS

        // CLAVIER 

        public bool isKeyDown(Keys key)
        {
            return this.currentState.IsKeyDown(key);      
        }
        public bool isKeyUp(Keys key)
        {
            return this.currentState.IsKeyUp(key);
        }

        public bool isKeyPressed(Keys key)
        {
            return this.oldState.IsKeyUp(key) && this.currentState.IsKeyDown(key);
        }

        // Initialize
	// Pour récuprer la hauteur et la largeur du Windows
        public void initialize()
        {
            _screnWidth = Window.ClientBounds.Width;
            _screnHeigth = Window.ClientBounds.Height;
        }
        public void LoadContent (ContentManager Content)
        {
            position = new Vector2(280, 280);
            rotation = 0;
        }
        // DRAW & UPDATE 
        public void Draw(SpriteBatch spriteBatch)
        {

        }

	// L'Update pour la mise a jours a chaque évenement du clavier 
        protected override void Update(GameTime gameTime)
        {
            KeyboardState keyboardState = Keyboard.GetState();
            // KeyBoard Moving
            if (keyboardState.IsKeyDown(Keys.Q))
                if (position.X >= 0)
                    position.X -= speed;

            if (keyboardState.IsKeyDown(Keys.D))
                if ((position.X < (_screnWidth - texture2.Width)) || (position.X < (_screnWidth - texture3.Width)))
                    position.X += speed;

            if (keyboardState.IsKeyDown(Keys.Z))
                if ((position.Y < (_screnHeigth - texture2.Width)) || (position.X < (_screnHeigth - texture3.Width)))
                    position.Y -= speed;
            if (keyboardState.IsKeyDown(Keys.S))
                if ((position.Y < (_screnHeigth - texture2.Width)) || (position.X < (_screnHeigth - texture3.Width)))
                    position.Y += speed;
            // Animation Sprite

            this.oldState = this.currentState;      
        }
    }
}