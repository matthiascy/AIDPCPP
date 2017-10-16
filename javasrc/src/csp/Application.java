package csp;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        /*
        String fileName = "";
        Network myNetwork;

        try {
            System.out.println("Chargement du fichier : " +
                    new java.io.File(".").getCanonicalPath() + "/" + fileName);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            readFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

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
        ConstraintExt c1 = new ConstraintExt(tuple);

        ArrayList<Object> vtuple = new ArrayList<Object>(2);
        vtuple.add("R");
        vtuple.add("G");
        c1.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("R");
        vtuple.add("B");
        c1.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("G");
        vtuple.add("R");
        c1.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("G");
        vtuple.add("B");
        c1.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("B");
        vtuple.add("R");
        c1.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("B");
        vtuple.add("G");
        c1.addTuple(vtuple);

        tuple = new ArrayList<String>(2);
        tuple.add("y");
        tuple.add("z");
        ConstraintExt c2 = new ConstraintExt(tuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("R");
        vtuple.add("G");
        c2.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("R");
        vtuple.add("B");
        c2.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("G");
        vtuple.add("R");
        c2.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("G");
        vtuple.add("B");
        c2.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("B");
        vtuple.add("R");
        c2.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("B");
        vtuple.add("G");
        c2.addTuple(vtuple);

        tuple = new ArrayList<String>(2);
        tuple.add("x");
        tuple.add("z");
        ConstraintExt c3 = new ConstraintExt(tuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("R");
        vtuple.add("G");
        c3.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("R");
        vtuple.add("B");
        c3.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("G");
        vtuple.add("R");
        c3.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("G");
        vtuple.add("B");
        c3.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("B");
        vtuple.add("R");
        c3.addTuple(vtuple);
        vtuple = new ArrayList<Object>(2);
        vtuple.add("B");
        vtuple.add("G");
        c3.addTuple(vtuple);

        network.addConstraint(c1);
        network.addConstraint(c2);
        network.addConstraint(c3);


        System.out.println(network);

        CSP sample = new CSP(network);

        System.out.println(sample.searchSolution());
        System.out.println("final: " + sample.searchAllSolutions());
    }
}
