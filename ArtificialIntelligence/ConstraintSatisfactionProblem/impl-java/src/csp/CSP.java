package csp;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Solveur : permet de résoudre un problème de contrainte par Backtrack :
 * 	Calcul d'une solution,
 * 	Calcul de toutes les solutions
 *
 */
public class CSP {

    private Network network;			// le réseau à résoudre
    private ArrayList<Assignment> solutions; 	// les solutions du réseau (résultat de searchAllSolutions)
    private Assignment assignment;			// l'assignation courante (résultat de searchSolution)
    private int cptr;					// le compteur de noeuds explorés


    /**
     * Crée un problème de résolution de contraintes pour un réseau donné
     *
     * @param r le réseau de contraintes à résoudre
     */
    public CSP(Network r) {
        network = r;
        solutions = new ArrayList<Assignment>();
			assignment = new Assignment();

    }



    /********************** BACKTRACK UNE SOLUTION *******************************************/

    /**
     * Cherche une solution au réseau de contraintes
     *
     * @return une assignation solution du réseau, ou null si pas de solution
     */

    public Assignment searchSolution() {
        cptr=1;
        assignment.clear();

			// Implanter appel a backtrack
        Assignment sol = null;
        sol = backtrack(assignment);

        System.out.println(cptr + " noeuds ont été explorés");
        return sol;
    }

    /* La methode bactrack ci-dessous travaille directement sur l'attribut assignment.
		 * On peut aussi choisir de ne pas utiliser cet attribut et de créer plutot un objet Assignment local à searchSolution :
		 * dans ce cas il faut le passer en parametre de backtrack
		 */
    /**
		 * Exécute l'algorithme de backtrack à la recherche d'une solution en étendant l'assignation courante
     * Utilise l'attribut assignment
     * @return la prochaine solution ou null si pas de nouvelle solution
     */

    private Assignment backtrack(Assignment assignment) {
        // AJOUTER UN PARAMETRE DE TYPE ASSIGNMENT SI ON NE TRAVAILLE PAS DIRECTEMENT SUR L'ATTRIBUT assignment
        // quelque part : cptr++

        Assignment result;

        if (assignment.getVars().containsAll(network.getVars()))
            return assignment;

        String var = chooseVar(assignment);

        if (var == null) {
            return assignment;
        }

        for (Object val : network.getDom(var)) {
            ++cptr;

            assignment.put(var, val);

            if (consistant(assignment, var)) {
                result = backtrack(assignment);

                if (result != null) {
                    return result;
                }
            }

            assignment.remove(var, val);
        }

        return null;
    }


    /********************** BACKTRACK TOUTES SOLUTIONS *******************************************/

    /**
		 * Calcule toutes les solutions au réseau de contraintes
		 *
		 * @return la liste des assignations solution
		 *
		 */
    public ArrayList<Assignment> searchAllSolutions(){
        cptr=1;
        solutions.clear(); // SI ON CHOISIT DE TRAVAILLER DIRECTEMENT SUR L'ATTRIBUT SOLUTIONS
        assignment.clear();

        backtrackAll(assignment);

        return solutions;
    }

    /**
		 * Exécute l'algorithme de backtrack à la recherche de toutes les solutions
		 * étendant l'assignation courante
		 *
		 */
    private Assignment backtrackAll(Assignment assignment) {
        // AJOUTER UN PARAMETRE DE TYPE ArrayList<Assignment> SI ON NE TRAVAILLE PAS DIRECTEMENT SUR L'ATTRIBUT solutions
        // quelque part : cptr++;

        Assignment result;

        if (assignment.getVars().containsAll(network.getVars()))
            return assignment;

        String var = chooseVar(assignment);

        if (var == null) {
            return assignment;
        }

        for (Object val : network.getDom(var)) {
            ++cptr;

            assignment.put(var, val);

            if (consistant(assignment, var)) {
                result = backtrackAll(assignment);

                if (result != null) {
                    solutions.add(result.clone());
                }
            }

            assignment.remove(var, val);
        }

        return null;
    }


    // IMPLANTER l'UNE DES DEUX METHODES CHOOSEVAR CI-DESSOUS (SELON QUE L'ASSIGNATION COURANTE EST PASSEE EN PARAMETRE OU PAS)

    /**
     * Retourne la prochaine variable à assigner étant donné assignment (qui doit contenir la solution partielle courante)
     *
     * @return une variable non encore assignée
     */
    private String chooseVar() {
        // A IMPLANTER
        System.err.println("Méthode chooseVar() à implanter !!!");
        return null;
    }

    /*****************************************************************/

    /**
     * Retourne la prochaine variable à assigner étant donné la solution partielle passée en paramètre
     *
     *  @param sol solution partielle courante
     * @return une variable non encore assignée
     */
    private String chooseVar(Assignment sol) {

        for (String var : network.getVars()) {
            if (sol.getVars().stream().noneMatch(v -> v.equals(var)))
                return var;
        }

        return null;
    }


    /**
     * Fixe un ordre de prise en compte des valeurs d'un domaine
     *
     * @param values une liste de valeurs
     * @return une liste de valeurs
     */
    private ArrayList<Object> tri(ArrayList<Object> values) {
        return values.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
    }


    // IMPLANTER l'UNE DES DEUX METHODES CONSISTANT CI-DESSOUS (SELON QUE L'ASSIGNATION COURANTE EST PASSEE EN PARAMETRE OU PAS)

    /**
     * Teste si l'assignation courante stokée dans l'attribut assignment est consistante, c'est à dire qu'elle
     * ne viole aucune contrainte.
     *
     * @param lastAssignedVar la variable que l'on vient d'assigner à cette étape
     * @return vrai ssi l'assignment courante ne viole aucune contrainte
     */
    private boolean consistant(String lastAssignedVar) {
        return network.getConstraints().stream().noneMatch(c -> c.violation(assignment));
    }

    /**
     * Teste si l'assignation courante stockée dans assignment est consistante par rapport à sol, c'est à dire qu'elle
     * ne viole aucune contrainte.
     *
     * @param sol solution partielle courante
     * @param lastAssignedVar la variable que l'on vient d'assigner à cette étape
     * @return vrai ssi l'assignment courante ne viole aucune contrainte
     */
    private boolean consistant(Assignment sol, String lastAssignedVar) {
        return network.getConstraints().stream().noneMatch(c -> c.violation(sol));
    }

}
