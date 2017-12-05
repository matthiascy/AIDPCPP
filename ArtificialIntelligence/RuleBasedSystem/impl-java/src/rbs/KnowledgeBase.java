package rbs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KnowledgeBase {
    private FactBase factBase;
    private FactBase factBaseSat;
    private RuleBase ruleBase;
    //private boolean order0 = false;

    public KnowledgeBase() {
        this.factBase = new FactBase();
        this.factBaseSat = new FactBase();
        this.ruleBase = new RuleBase();
    }

    public KnowledgeBase(String filepath) {
        this.factBase = new FactBase();
        this.factBaseSat = new FactBase();
        this.ruleBase = new RuleBase();

        BufferedReader file = null;

        try {
            file = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String facts = null;
        try {
            facts = file.readLine();
            // System.out.println("[DEBUG]Facts :" + facts);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (facts != null)
            this.factBase = new FactBase(facts);

        String r = null;
        try {
            r = file.readLine();

            while (r != null) {
                // System.out.println("[DEBUG]Rule :" + r);
                Rule rule = new Rule(r);
                this.ruleBase.addRule(rule);
                r = file.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        factBaseSat.addAtoms(factBase.getAtoms());
    }

    public FactBase getFactBase() {
        return factBase;
    }

    public FactBase getFactBaseSat() {
        return factBaseSat;
    }

    public RuleBase getRuleBase() {
        return ruleBase;
    }

    public String toString() {
        String str = "";
        str += "\n== Fact Base ==\n" + factBase + "\n";
        str += "== Rule Base ==\n" + ruleBase + "\n";
        str += "== Fact Base Saturee ==\n" + factBaseSat + "\n";
        return str;
    }

    public void forwardChaining() {
        ArrayList<Atom> atraiter = new ArrayList<>(this.factBase.getAtoms());
        int rule_num = this.ruleBase.size();
        int counter[] = new int[rule_num];

        for (int i = 0; i < rule_num; ++i) {
            counter[i] = ruleBase.getRule(i).getHypothesis().size();
        }

        while (!atraiter.isEmpty()) {
            for (int j = 0; j < atraiter.size(); ++j) {
                Atom fact = atraiter.get(j);
                for (int i = 0; i < rule_num; ++i) {
                    Rule rule = ruleBase.getRule(i);
                    ArrayList<Atom> hypo = rule.getHypothesis();
                    Atom conc = rule.getConclusion();
                    if (hypo.stream().anyMatch(atom -> atom.equalsA(fact))) {
                        counter[i] -= 1;

                        if (counter[i] == 0) {
                            ArrayList<Atom> union = new ArrayList<>(atraiter);
                            for (Atom f : factBase.getAtoms()) {
                                if (!atomListContains(union, f)) {
                                    union.add(f);
                                }
                            }
                            if (!atomListContains(union, conc)) {
                                atraiter.add(conc);
                                this.factBaseSat.addAtomWithoutCheck(conc);
                            }
                        }
                    }
                }
                atraiter.remove(j);
            }
        }
    }

    public boolean backwardChaining(Atom atomQ, ArrayList<Atom>atomList) {
        if (factBase.getAtoms().stream().anyMatch(atom -> atom.equalsA(atomQ))) {
            return true;

        } else {

            for (Rule rule : ruleBase.getRules()) {
                if (rule.getConclusion().equalsA(atomQ)) {
                    if (!isAtomListsIntersected(rule.getHypothesis(), atomList)) {
                        int size = rule.getHypothesis().size();
                        int i = 0;

                        ArrayList<Atom> union = new ArrayList<>();

                        if (atomList != null) {
                            union.addAll(atomList);
                            if (!atomListContains(union, atomQ))
                                union.add(atomQ);
                        } else {

                            union.add(atomQ);
                        }

                        while (i < size && backwardChaining(rule.getHypothesis().get(i), union))
                            ++i;

                        if (i >= size)
                            return true;
                    }
                }
            }

            return false;
        }
    }

    public void instanciation() {
        RuleBase newRuleBase = new RuleBase();
        Substitutions subs = new Substitutions(factBase, ruleBase);
        subs.generateAllSubstitutions();
        System.out.println(subs.getSubstitutions());

        for (Substitution sub : subs.getSubstitutions()) {
            for (TermPair pair : sub) {
                for (Rule rule : ruleBase.getRules()) {
                    if (rule.getTerms().stream().anyMatch(term -> term.equalsT(pair.getFst())))
                        //newRuleBase.addRule(rule.replaceVarByConstant(pair));
                        rule.replaceVarByConstant(pair);
                }
            }
        }
        //System.out.println(newRuleBase);
    }

    /**
     * Return whether two ArrayList `atomsB` and `atomsA` is intersected
     */
    private boolean isAtomListsIntersected(ArrayList<Atom> atomsA, ArrayList<Atom> atomsB) {
        return atomsB != null && atomsA.stream().anyMatch(atom -> atomListContains(atomsB, atom));
    }

    /**
     * Return whether an ArrayList `atoms` has the element `atom`.
     */
    private boolean atomListContains(ArrayList<Atom> atoms, Atom atom) {
        return atoms != null && atoms.stream().anyMatch(atom1 -> atom1.equalsA(atom));
    }
}
