package Class;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by yanis on 18/03/2017.
 */

public class Product {

    public String title;
    public ImageView productImage;
    public String description;
    public double price;
    public boolean selected;

    public Product(String title, ImageView productImage, String description,
                   double price) {
        this.title = title;
        this.productImage = productImage;
        this.description = description;
        this.price = price;
    }

}