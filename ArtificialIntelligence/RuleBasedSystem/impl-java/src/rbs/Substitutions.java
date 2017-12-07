package rbs;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Substitutions {
    private ArrayList<Substitution> substitutions;
    //private ArrayList<Atom> atoms;  // atoms that contain variables
    private ArrayList<Term> variables;
    private ArrayList<Term> value_terms;

    public Substitutions() {
        this.substitutions = new ArrayList<>();
        this.variables = new ArrayList<>();
        this.value_terms = new ArrayList<>();
//        this.atoms = new ArrayList<>();
    }

    public Substitutions(FactBase factBase, RuleBase ruleBase) {
        substitutions = new ArrayList<>();
        value_terms = new ArrayList<>(factBase.getTerms());
        variables = new ArrayList<>();
        for (Rule r : ruleBase.getRules()) {
  //          atoms.addAll(r.getHypothesis());
//            atoms.add(r.getConclusion());
            variables.addAll(r.getTerms().stream().
                    filter(term -> (!term.isConstant() && !termListContains(variables, term))).
                    collect(Collectors.toCollection(ArrayList::new)));
        }
    }

    public ArrayList<Substitution> getSubstitutions() {
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

    public void addSolution(Substitution sub) {
        this.substitutions.add(sub.clone());
    }

    public void generateAllSubstitutions() {
        for (Term var : variables) {
            Substitution sub = new Substitution();
            for (Term c : value_terms) {
                sub.add(new TermPair(var, c));
            }
            substitutions.add(sub);
        }
    }

    public ArrayList<Term> getVariables() {
        return variables;
    }

    public ArrayList<Term> getValueTerms() {
        return value_terms;
    }

    @Override
    public String toString() {
        return  substitutions + ", variables=" + variables + ", value_terms=" + value_terms;
    }

    private boolean termListContains(ArrayList<Term> list, Term t) {
        return list.stream().anyMatch(term -> term.equalsT(t));
    }
}
