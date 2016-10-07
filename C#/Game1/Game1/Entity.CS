using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Game1
{
    public abstract class Entity
    {
        // FIELDS 
        protected Sprite sprite;
        protected Sprite hitBox;
        // CONSTRUCTOR 
        protected Entity(Sprite sprite)
        {
            this.sprite = sprite;
        }
        // METHODS

        // UPDATE & DRAW 
    }
}
