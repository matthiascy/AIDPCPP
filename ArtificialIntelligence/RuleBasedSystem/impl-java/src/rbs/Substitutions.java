package rbs;

import java.util.ArrayList;

public class Substitutions {
    private ArrayList<ArrayList<TermPair>> substitutions;
    private ArrayList<Term> variables;
    private ArrayList<Term> terms;

    public Substitutions() {
        substitutions = new ArrayList<>();
        variables = new ArrayList<>();
        terms = new ArrayList<>();
    }

    public ArrayList<ArrayList<TermPair>> getSubstitutions() {
        return substitutions;
    }

    public ArrayList<TermPair> getSubstitution(int i) {
        return substitutions.get(i);
    }

    public void addTermPairTo(TermPair pair, int i) {
        if (substitutions.get(i).stream().noneMatch(pair1 -> pair1.equals(pair))) {
            this.substitutions.get(i).add(pair);
        }
    }

    public void addTermPairsTo(ArrayList<TermPair> pairs, int i) {
        this.substitutions.get(i).addAll(pairs);
    }

    public void generateAllSubstitutions() {
    }
}
