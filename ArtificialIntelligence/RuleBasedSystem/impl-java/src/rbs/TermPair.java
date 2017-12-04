package rbs;

public class TermPair {
    private Term fst = null;
    private Term snd = null;

    public TermPair(String fst, String snd, boolean isConstant) {
        this.fst = new Term(fst, isConstant);
        this.snd = new Term(snd, isConstant);
    }

    public TermPair(Term fst, Term snd) {
        this.fst = new Term(fst.getLabel(), fst.isConstant());
        this.snd = new Term(snd.getLabel(), snd.isConstant());
    }

    @Override
    public String toString() {
        return "TermPair: (" + fst + ", " + snd + ")";
    }

    public boolean equals(TermPair other) {
        // TODO: implement
        return false;
    }

}
