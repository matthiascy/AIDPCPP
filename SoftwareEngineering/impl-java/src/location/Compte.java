package location;

public abstract class Compte{
    private Client client;

    public Compte(Client c) {
        this.client = c;
    }

    public float prixLocation(Produit p) {
        return p.prixLocation();
    }

    public String compteName() {
        return client.name();
    }
}
