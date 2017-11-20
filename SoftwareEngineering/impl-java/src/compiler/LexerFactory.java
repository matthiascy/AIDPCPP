package compiler;

import compiler.ada.AdaLexer;
import compiler.cpp.CppLexer;
import compiler.java.JavaLexer;

public class LexerFactory {
    public Lexer create(String lang) {
        switch (lang) {
            case "ada":
                return new AdaLexer();

            case "c++":
                return new CppLexer();

            case "java":
                return new JavaLexer();

            default:
                // TODO
                System.out.println();
                return null;
        }
    }
}
