package location;

public class CompteAvecSeuil extends Compte {
    private int x = 0;
    private static int times = 1;

    public CompteAvecSeuil(Client c, int x) {
        super(c);
        this.x = x;
    }

    public float prixLocation(Produit p) {
        times += 1;

        if (times <= x) {

            return 0.f;

        } else {

            return super.prixLocation(p);
        }
    }

}
