package rbs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KnowledgeBase {
    private FactBase factBase;
    private FactBase factBaseSat;
    private RuleBase ruleBase;

    public KnowledgeBase() {
        this.factBase = new FactBase();
        this.factBaseSat = new FactBase();
        this.ruleBase = new RuleBase();
    }

    public KnowledgeBase(String filepath) {
        BufferedReader file = null;

        file = new BufferedReader(new FileReader(filepath));

        String str = file.readLine();

        // TODO: lecture of text file
    }

    public FactBase factBase() {
        return factBase;
    }

    public FactBase factBaseSat() {
        return factBaseSat;
    }

    public RuleBase ruleBase() {
        return ruleBase;
    }

    public String toString() {
        // TODO: display the knownledge base
        return null;
    }

    public void forwardChainingNaive() {
        boolean done = false;

        for (int i = 0; i < ruleBase.size(); ++i)
            apply(ruleBase.getRule(i));

        while (!done) {
            // TODO:
        }
    }

    public void backwardChaining() {
        // TODO:
    }

    private void apply(Rule r) {
        if (factBaseSat.getAtoms().containsAll(r.getHypothesis())) {
            Atom conclusion = r.getConclusion();
            if (factBaseSat().getAtoms().contains(conclusion)) {
                factBaseSat.addAtomWithoutCheck(conclusion);
            }
        }
    }
}
