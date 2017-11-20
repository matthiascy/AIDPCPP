package location;

public class CompteAvecReduction extends Compte {
    private float coeff = 0.f;

    public CompteAvecReduction(Client c, float coeff) {
        super(c);
        this.coeff = coeff;
    }

    public float prixLocation(Produit p) {
        return super.prixLocation(p) * (1.f - coeff);
    }
}
