package rbs;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Homomorphisms {
    private ArrayList<Atom> atom_variables;
    private ArrayList<Term> term_variables;
    private ArrayList<Atom> facts;
    private Substitutions solutions;
    private boolean calculated = false;

    Homomorphisms(ArrayList<Atom> atomVars, ArrayList<Atom> facts) {
        this.atom_variables = atomVars;
        this.facts = facts;
        this.solutions = new Substitutions();
        this.term_variables = new ArrayList<>();
        for (Atom atom : atom_variables) {
            for (Term term : atom.getArgs()) {
                if (term.isVariable()) {
                    if (term_variables.stream().noneMatch(term1 -> term1.equalsT(term)))
                        term_variables.add(term);
                }
            }
        }
    }

    @Override
    public String toString() {
        if (!calculated)
            generateAllMorphisms();

        return "Homomorphisms: \n\t" + solutions.getSubstitutions();
    }

    private void generateAllMorphisms() {
        Substitution assignments = new Substitution();
        backtrackAll(assignments);
        calculated = true;
    }

    private Substitution backtrackAll(Substitution assignments) {
        Substitution result;

        if (assignments.getVariables().size() == term_variables.size())
            return assignments;

        Term var = chooseVariable(assignments);

        if (var == null)
            return assignments;

        for (Term val : getVariableDom(assignments)) {
            TermPair tmp_pair = new TermPair(var, val);
            if (assignments.add(tmp_pair)) {

                if (!violation(assignments)) {
                    result = backtrackAll(assignments);

                    if (result != null)
                        solutions.addSolution(result);
                }

                assignments.remove(tmp_pair);

            }
        }

        return null;
    }

    private Term chooseVariable(Substitution assignments) {
        for (Term var : term_variables) {
            if (assignments.getVariables().stream().noneMatch(term -> term.equalsT(var)))
                return var;
        }
        return null;
    }

    private ArrayList<Term> getVariableDom(Substitution assignments) {
        ArrayList<Term> out = new ArrayList<>();
        // no duplicates
        for (Atom atom : facts) {
            for (Term term : atom.getArgs()) {
                if (term.isConstant() && out.stream().noneMatch(term1 -> term1.equalsT(term))) {
                    if (assignments.isEmpty())
                        out.add(term);
                    else
                        for (TermPair pair : assignments)
                            if (!pair.getSnd().equalsT(term))
                                out.add(term);
                }
            }
        }

        return out;
    }

    private boolean violation(Substitution assignments) {
        // Used to store the atom_variables in which variables have been partially replaced by assignment value
        ArrayList<Atom> intermediate = new ArrayList<>(atom_variables);
        ArrayList<Atom> tmp_atoms = new ArrayList<>();

        for (TermPair pair : assignments) {
            for (Atom atom : intermediate) {
                for (Term term : atom.getArgs()) {
                    if (term.equalsT(pair.getFst())) {
                        String atomstr = atom.toString();
                        atomstr = atomstr.replace(pair.getFst().getLabel(), "\'" + pair.getSnd().getLabel() + "\'");
                        tmp_atoms.add(new Atom(atomstr));
                    }
                }
            }

            intermediate = intermediate.stream().filter(atom -> !atom.containsVariable(pair.getFst())).collect(Collectors.toCollection(ArrayList::new));
            intermediate.addAll(tmp_atoms);
        }

        for (Atom atom : intermediate)
            if (!atom.hasVariable())
                if (facts.stream().noneMatch(atom1 -> atom1.equalsA(atom)))
                    return true;

        return false;
    }
}
