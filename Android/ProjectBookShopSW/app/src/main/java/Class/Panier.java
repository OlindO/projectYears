package Class;

/**
 * Created by yanis on 28/03/2017.
 */
import java.util.ArrayList;
import java.util.Collection;
import Class.Livre;
import Class.Livre;
public class Panier extends ArrayList<Livre> {

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
}