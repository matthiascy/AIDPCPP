import rbs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Application1 {
    private static void input(KnowledgeBase kb) {
        boolean done = false;
        Scanner reader = new Scanner(System.in);
        while (!done) {
            System.out.println("Enter a request consists of atoms('exit' to exit):> ");
            String atomsStr = reader.nextLine();
            // clear spaces in string
            atomsStr = atomsStr.replaceAll("\\s","");

            if (atomsStr.equals("exit")) {

                done = true;

            } else {

                String[] atomsStrs = atomsStr.split(";");
                if (Arrays.stream(atomsStrs).allMatch(atomstr -> kb.backwardChaining(new Atom(atomstr), null)))
                    System.out.println("True, it's been proven");
                else
                    System.out.println("False, it's not been proven");
            }
        }
        reader.close();
    }

    public static void main(String args[]) {
        boolean done = false;
        String filepath = "animal.txt";
        KnowledgeBase kb = new KnowledgeBase(filepath);

        System.out.println("File loaded: " + filepath);
        System.out.println("Knowledge Base: " + kb);
        System.out.println("Rule Base(before): " + kb.getRuleBase());

        kb.instanciation();

        System.out.println("Rule Base(after): " + kb.getRuleBase());

        kb.forwardChaining();

        System.out.println("ForwardChaining...\n");
        System.out.println("Fact Base saturee size: " + kb.getFactBaseSat().size());
        System.out.println("Fact Base saturee: \n" + kb.getFactBaseSat());

        input(kb);
    }
}



