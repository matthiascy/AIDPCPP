package csp;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ConstraintEq extends Constraint {
    public ConstraintEq(ArrayList<String> vars) {
        super(vars);
    }

    public ConstraintEq(ArrayList<String> vars, String name) {
        super(vars, name);
    }

    public ConstraintEq(BufferedReader in) throws Exception {
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

            //return valList.stream().noneMatch(varList.get(0)::equals);
            for (int i = 0; i < valList.size(); ++i) {
                for (int j = 0; j < valList.size(); ++j) {
                    if (i == j)
                        continue;

                    if (!valList.get(i).equals(valList.get(j)))
                        return true;
                }
            }
        }

        return false;
    }
}
