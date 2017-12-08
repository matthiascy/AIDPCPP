package rbs;

import java.util.ArrayList;

public class Substitution extends ArrayList<TermPair> {
    Substitution() {
        super();
    }

    public Substitution clone() {
        return (Substitution)super.clone();
    }

    ArrayList<Term> getVariables() {
        ArrayList<Term> out = new ArrayList<>();
        for (TermPair termPair : this) {
            out.add(termPair.getFst());
        }
        return out;
    }

    @Override
    public boolean add(TermPair pair) {
        return this.stream().noneMatch(termpair -> termpair.equals(pair)) && super.add(pair);
    }

    @Override
    public String toString() {
        String str = "";
        str += "{";
        for (TermPair termPair : this)
            str += termPair.toHomomorphismString() + " ";
        str += "}";
        return str;
    }
}
