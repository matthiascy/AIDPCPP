package compiler;

import compiler.ada.AdaParser;
import compiler.cpp.CppParser;
import compiler.java.JavaParser;

public class ParserFactory {
    public Parser create(String lang) {
        switch (lang) {
            case "ada":
                return new AdaParser();

            case "c++":
                return new CppParser();

            case "java":
                return new JavaParser();

            default:
                // TODO
                System.out.println();
                return null;
        }
    }
}
