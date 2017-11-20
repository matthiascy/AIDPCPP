package compiler.ada;

import compiler.Parser;
import compiler.ProgramText;

public class AdaParser extends Parser {
    @Override
    public void parse(ProgramText txt) {
        System.out.println("I am parsing a Ada scaned text as Ada AbstractSyntaxTree");
    }
}
