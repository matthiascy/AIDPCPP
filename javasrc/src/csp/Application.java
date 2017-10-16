package csp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        /*
        String fileName = args[0];
        Network myNetwork;

        try {
            System.out.println("Chargement du fichier : " + new File(".").getCanonicalPath() + "/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader readFile = null;
        try {
            readFile = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            myNetwork = new Network(readFile);
            System.out.println("[Debug] myNetwork : " + myNetwork);

            CSP myCSP = new CSP(myNetwork);

            System.out.println("One possible solution is : " + myCSP.searchSolution());
            ArrayList<Assignment> sol = myCSP.searchAllSolutions();
            System.out.println("All solutions are : " + sol + "\n Nomber: " + sol.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (readFile != null) {
                readFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        // -----

        Network network = new Network();

        java.lang.String[] variables = {"x", "y", "z"};
        java.lang.String[] values = {"R", "G", "B"};
        for (java.lang.String s : variables) {
            network.addVariable(s);
            for (java.lang.String v : values) {
                network.addValue(s, v);
            }
        }


        ArrayList<String> tuple = new ArrayList<String>(2);
        tuple.add("x");
        tuple.add("y");
        Constraint c1 = new ConstraintDif(tuple);

        tuple = new ArrayList<String>(2);
        tuple.add("y");
        tuple.add("z");
        Constraint c2 = new ConstraintDif(tuple);

        tuple = new ArrayList<String>(2);
        tuple.add("x");
        tuple.add("z");
        Constraint c3 = new ConstraintDif(tuple);

        network.addConstraint(c1);
        network.addConstraint(c2);
        network.addConstraint(c3);


        System.out.println(network);

        CSP sample = new CSP(network);

        System.out.println(sample.searchSolution());
        System.out.println("final: " + sample.searchAllSolutions());

    }
}
