package rbs;

import java.util.ArrayList;

public class Homomorphisms {
    private ArrayList<Atom> variables;
    private ArrayList<Atom> facts;
    private Substitutions solutions;
    private boolean calculated = false;

    public Homomorphisms(ArrayList<Atom> variables, ArrayList<Atom> facts) {
        this.variables = variables;
        this.facts = facts;
        this.solutions = new Substitutions();
    }

    @Override
    public String toString() {
        if (!calculated)
            generateAllMorphisms();

        return "Homomorphisms {\n" +
                "variables=\n\t" + variables +
                ", \nfacts=\n\t" + facts +
                ", \nsolutions=\n\t" + solutions +
                "\n}";
    }

    private void generateAllMorphisms() {
        Substitution assignments = new Substitution();
        backtrackAll(assignments);
        calculated = true;
    }

    private Substitution backtrackAll(Substitution assignments) {
        Substitution result;

        if (assignments.getVariables().size() == variables.size())
            return assignments;

        Term var = chooseAtomVariable(assignments);

        if (var == null)
            return assignments;

        for (Term val : getFactsConstant()) {
            TermPair tmp_pair = new TermPair(var, val);
            assignments.add(tmp_pair);

            if (!violation(assignments, tmp_pair)) {
                result = backtrackAll(assignments);

                if (result != null)
                    solutions.addSolution(result);
            }

            assignments.remove(tmp_pair);
        }

        return null;
    }

    private Term chooseAtomVariable(Substitution assignments) {
        for (Atom varAtom : variables) {
            for (Term var : varAtom.getArgs())
                if (assignments.getVariables().stream().noneMatch(v -> v.equalsT(var)))
                    return var;
        }
        return null;
    }

    private ArrayList<Term> getFactsConstant() {
        ArrayList<Term> out = new ArrayList<>();
        for (Atom atom : facts) {
            for (Term term : atom.getArgs()) {
                if (term.isConstant() && out.stream().noneMatch(term1 -> term1.equalsT(term))) {
                    out.add(term);
                }
            }
        }

        return out;
    }

    private boolean violation(Substitution assignments, TermPair varval) {
        for (Atom atom : variables) {
            for (Term term : atom.getArgs()) {
                if (term.equalsT(varval.getFst())) {
                    String atomstr = atom.toString();
                    atomstr = atomstr.replace(varval.getFst().getLabel(), "\'" + varval.getSnd().getLabel() + "\'");
                    atom = new Atom(atomstr);
                }
            }

            Atom finalAtom = atom;
            if (facts.stream().noneMatch(atom1 -> atom1.equalsA(finalAtom)))
                return true;
        }

        return false;
    }
}
