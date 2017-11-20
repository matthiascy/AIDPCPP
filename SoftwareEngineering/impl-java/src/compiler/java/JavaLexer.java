package compiler.java;

import compiler.Lexer;
import compiler.ProgramText;

public class JavaLexer extends Lexer {
    @Override
    public void scan(ProgramText txt) {
        System.out.println("I am scanning a Java program text");
    }
}
