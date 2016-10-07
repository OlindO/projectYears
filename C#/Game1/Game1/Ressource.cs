using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Game1
{
    public class Ressource
    {
        // STATIC PROPERTIES
        public static Dictionary<string, Texture2D> Images;
        public static Dictionary<string, Texture2D> Sonds;


        // STATIC METHODS
        public static void LoadImages(ContentManager content)
        {
            Images = new Dictionary<string, Texture2D>();
            List<string> imagesName = new List<string>()
        {
            "bullet",
            "tankBase",
            "tankTurret",
            "Pv",
            "backgroundBlue"
        };

            foreach (string img in imagesName)
            {
                Images.Add(img, content.Load<Texture2D>(img));
            }
        }

        public static void LoadSongs(ContentManager content)
        {
            /* Sonds = new Dictionary<string, Texture2D>();
             List<string> soundsNames = new List<string>()
             {
                 "",   // <= Ici les nom des sprites pour le sons 
                 "",
                 ""
             };

             foreach (string sond in soundsNames)
             {
                 Sonds.Add(sond, content.Load<Texture2D>("Sounds" + sond));
             }
              */
        }

    }
}