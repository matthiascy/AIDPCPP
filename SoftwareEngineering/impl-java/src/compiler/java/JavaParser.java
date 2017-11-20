package compiler.java;

import compiler.Parser;
import compiler.ProgramText;

public class JavaParser extends Parser {
    @Override
    public void parse(ProgramText txt) {
        System.out.println("I am parsing a Java scaned text and I generate a Java AbstractSyntaxTree");
    }
}
