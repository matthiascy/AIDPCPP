package compiler.cpp;

import compiler.Parser;
import compiler.ProgramText;

public class CppParser extends Parser {
    @Override
    public void parse(ProgramText txt) {
        System.out.println("I am parsing a C++ scaned text as C++ AbstractSyntaxTree");
    }
}
