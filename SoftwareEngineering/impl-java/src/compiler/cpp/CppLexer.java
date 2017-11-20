package compiler.cpp;

import compiler.Lexer;
import compiler.ProgramText;

public class CppLexer extends Lexer {
    @Override
    public void scan(ProgramText txt) {
        System.out.println("I am scanning a C++ program text");
    }
}
