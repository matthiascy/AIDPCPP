package csp;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ConstraintDif extends Constraint {
    public ConstraintDif(ArrayList<String> vars) {
        super(vars);
    }

    public ConstraintDif(ArrayList<String> vars, String name) {
        super(vars, name);
    }

    public ConstraintDif(BufferedReader in) throws Exception {
        super(in);
    }

    @Override
    public boolean violation(Assignment a) {
        if (a.size() <= 1)
            return false;

        if (a.getVars().containsAll(varList)) {
            ArrayList<Object> valList = new ArrayList<>();

            for (String var : varList) {
                valList.add(a.get(var));
            }

            for (int i = 0; i < valList.size(); ++i) {
                for (int j = 0; j < valList.size(); ++j) {
                    if (i == j)
                        continue;

                    if (valList.get(i).equals(valList.get(j)))
                        return true;
                }
            }
        }

        return false;
    }
}
