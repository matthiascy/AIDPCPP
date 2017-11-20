package compiler.cpp;

import compiler.Generator;
import compiler.ProgramText;

public class CppGenerator extends Generator {
    @Override
    public void generate(ProgramText txt) {
        System.out.println("I am generating an assembler program text from a C++ AbstractSyntaxTree");
    }
}
