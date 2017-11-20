package location;

public class Produit {
    private String name;
    private float prix;

    public Produit(String name, float prix) {
        this.name = name;
        this.prix = prix;
    }

    public String name() {
        return this.name;
    }

    public float prix() {
        return this.prix;
    }

    public float prixLocation() {
        return this.prix / 2;
    }
}
