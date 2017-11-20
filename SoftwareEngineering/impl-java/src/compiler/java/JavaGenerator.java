package compiler.java;

import compiler.Generator;
import compiler.ProgramText;

public class JavaGenerator extends Generator {
    @Override
    public void generate(ProgramText txt) {
        System.out.println("I am generating a JVM program text from a Java AbstractSyntaxTree");
    }
}
