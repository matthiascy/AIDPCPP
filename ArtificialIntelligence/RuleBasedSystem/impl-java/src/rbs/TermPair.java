package rbs;

public class TermPair {
    // fst is the variable, snd is the substitution value
    private Term fst = null;
    private Term snd = null;

    public TermPair(String fst, String snd, boolean isConstant) {
        this.fst = new Term(fst, isConstant);
        this.snd = new Term(snd, isConstant);
    }

    TermPair(Term fst, Term snd) {
        this.fst = new Term(fst.getLabel(), fst.isConstant());
        this.snd = new Term(snd.getLabel(), snd.isConstant());
    }

    @Override
    public String toString() {
        return "(" + fst + ", " + snd + ")";
    }

    public String toHomomorphismString() {
        return fst + " \u21A6 " + snd;
    }

    public boolean equals(TermPair other) {
        return other.fst.equalsT(fst) && other.fst.equalsT(snd);
    }

    public Term getFst() {
        return fst;
    }

    public Term getSnd() {
        return snd;
    }
}
