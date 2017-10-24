package csp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Demo {

    public static void main(String[] args) {
        String fileName = args[0];
        Network myNetwork;

        System.out.println("Problème " + fileName.replaceFirst("[.][^.]+$", ""));

        BufferedReader readFile = null;
        try {
            readFile = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            myNetwork = new Network(readFile);

            CSP myCSP = new CSP(myNetwork);
            System.out.println("searchSol:    ");
            System.out.println("    Première solution trouvée: \n" + "    " + myCSP.searchSolution());
            System.out.println("    Nombre de noeuds générés: \n" + "    " + myCSP.getCount());
            System.out.println("searchAllSol:    ");
            ArrayList<Assignment> sol = myCSP.searchAllSolutions();
            System.out.println("  Nombre de solutions trouvées:\n" + "    " + sol.size());
            System.out.println("  Toutes les solutions trouvées: \n" + "    " + sol);
            System.out.println("  Nombre de noeuds générés: \n" + "    " + myCSP.getCount());

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
    }
}
