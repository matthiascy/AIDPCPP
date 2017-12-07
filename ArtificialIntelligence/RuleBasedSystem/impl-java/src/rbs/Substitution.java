package rbs;

import java.util.ArrayList;

public class Substitution extends ArrayList<TermPair> {
    public Substitution() {
        super();
    }

    public Substitution clone() {
        return (Substitution)super.clone();
    }

    public ArrayList<Term> getVariables() {
        ArrayList<Term> out = new ArrayList<>();
        for (TermPair termPair : this) {
            out.add(termPair.getFst());
        }
        return out;
    }

    @Override
    public String toString() {
        String str = "";
        for (TermPair termPair :this)
            str += termPair.toString();
        return str;
    }
}
