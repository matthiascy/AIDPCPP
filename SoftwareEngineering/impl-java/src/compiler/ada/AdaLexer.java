package compiler.ada;

import compiler.Lexer;
import compiler.ProgramText;

public class AdaLexer extends Lexer {
    @Override
    public void scan(ProgramText txt) {
        System.out.println("I am scanning a Ada program text");
    }
}
