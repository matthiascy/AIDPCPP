import rbs.*;

import java.util.Scanner;


public class ApplicationOrder0 {
	public static void main(String[] args)
	{
        Scanner reader = new Scanner(System.in);

	    boolean done = false;
	    String filepath = "td4-ex4.txt";
        KnowledgeBase kb = new KnowledgeBase(filepath);

        System.out.println("File loaded: " + filepath);
        System.out.println("Knowledge Base: " + kb);

        kb.forwardChaining();

        System.out.println("ForwardChaining...\n");
        System.out.println("Fact Base saturee size: " + kb.getFactBaseSat().size());
        System.out.println("Fact Base saturee: \n" + kb.getFactBaseSat());

        while (!done) {
            System.out.println("Enter an atom to prove('exit' to exit)> ");
            String atomStr = reader.next();
            if (atomStr.equals("exit")) {
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

                System.out.println("Backward chaining: " + answer);
            }
        }

        reader.close();
	}
}
