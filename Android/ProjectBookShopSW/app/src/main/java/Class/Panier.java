package Class;

/**
 * Created by yanis on 28/03/2017.
 */
import java.util.ArrayList;
import java.util.Collection;
import Class.Livre;
import Class.Livre;
public class Panier extends ArrayList<Livre> {
    int capacity = 5;
    int subTotale;
    double totale;
    double prixHere;
    private Livre [] livOb;

    public Panier(int initialCapacity) {
        super(initialCapacity);
    }

    public Panier(Livre livre) {
    }
    public Panier() {
    }
    public Panier(Collection<? extends Livre> c) {
        super(c);
    }

    public void removeProduct()
    {

    }

    public double updatePriceOfCart(ArrayList<Livre> livres, int quantity)
    {
        double total = 0.0;

        for(int i =0; i<livres.size(); i++)
        {
            if(livOb[i]!= null)
            {
                prixHere = livres.get(i).getPrix();
                int subTotale = (int) (quantity * prixHere);
            }

        }
        total += subTotale;
        return total;
    }
    public int sizeOfCart()
    {
        return 0;
    }
}