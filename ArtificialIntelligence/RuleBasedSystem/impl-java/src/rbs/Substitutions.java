package rbs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Substitutions {
    private ArrayList<Substitution> substitutions;
    private ArrayList<Term> variables;
    private ArrayList<Term> terms;

    public Substitutions(FactBase factBase, RuleBase ruleBase) {
        substitutions = new ArrayList<>();
        terms = new ArrayList<>(factBase.getTerms());
        variables = new ArrayList<>();
        for (Rule r : ruleBase.getRules()) {
            variables.addAll(r.getTerms().stream().
                    filter(term -> (!term.isConstant() && !termListContains(variables, term))).
                    collect(Collectors.toCollection(ArrayList::new)));
        }
        //System.out.println("Substitution terms: " + terms);
        //System.out.println("Substitution variables: " + variables);
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

    public void generateAllSubstitutions() {
        for (Term var : variables) {
            Substitution sub = new Substitution();
            for (Term c : terms) {
                sub.add(new TermPair(var, c));
            }
            substitutions.add(sub);
        }
    }

    private boolean termListContains(ArrayList<Term> list, Term t) {
        return list.stream().anyMatch(term -> term.equalsT(t));
    }

    private static <A, B, C> Stream<C> zipWith(Stream<A> as, Stream<B> bs, BiFunction<A, B, C> zipper) {
        final Iterator<A> iterA = as.iterator();
        final Iterator<B> iterB = bs.iterator();
        final Iterator<C> iterC = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return iterA.hasNext() && iterB.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(iterA.next(), iterB.next());
            }
        };
        final boolean parallel = as.isParallel() || bs.isParallel();
        return iteratorToFiniteStream(iterC, parallel);
    }

    private static <T> Stream<T> iteratorToFiniteStream(Iterator<T> iter, boolean parallel) {
        final Iterable<T> iterable = () -> iter;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }
}
