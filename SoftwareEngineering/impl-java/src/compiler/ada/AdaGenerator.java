package compiler.ada;

import compiler.Generator;
import compiler.ProgramText;

public class AdaGenerator extends Generator {
    @Override
    public void generate(ProgramText txt) {
        System.out.println("I am generating a ada program text from a Ada AbstractSyntaxTree");
    }
}
