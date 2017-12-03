import rbs.*;

import java.util.Scanner;


public class Application0 {
	public static void main(String[] args)
	{
	    boolean done = false;
        KnowledgeBase kb = new KnowledgeBase("reunion.txt");

        System.out.println("File loaded: reunion.txt");
        System.out.println("Knowledge Base: " + kb);

        kb.forwardChaining();

        System.out.println("Fact Base saturee size: " + kb.getFactBaseSat().size());
        System.out.println("Fact Base saturee: " + kb.getFactBaseSat());

        Scanner reader = new Scanner(System.in);

        while (!done) {
            System.out.println("Enter an atom to prove('quit' to exit)> ");
            String atomStr = reader.next();
            if (atomStr.equals("quit")) {
                done = true;

            } else {

                Atom atom = new Atom(atomStr);
                String answer;
                if (kb.getFactBaseSat().getAtoms().stream().anyMatch(atom1 -> atom1.equalsA(atom))) {
                    answer = "oui";
                } else {
                    answer = "non";
                }

                System.out.println("Search in fact base saturee: " + answer);

                if (kb.backwardChaining(atom, null)) {
                    answer = "oui";
                } else {
                    answer = "non";
                }

                System.out.println("Backword chaining: " + answer);
            }
        }

        reader.close();
	}
}
