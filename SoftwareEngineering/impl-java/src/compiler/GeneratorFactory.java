package compiler;

import compiler.ada.AdaGenerator;
import compiler.cpp.CppGenerator;
import compiler.java.JavaGenerator;

public class GeneratorFactory {
    public Generator create(String lang) {
        switch (lang) {
            case "ada":
                return new AdaGenerator();

            case "c++":
                return new CppGenerator();

            case "java":
                return new JavaGenerator();

            default:
                // TODO
                System.out.println();
                return null;
        }
    }
}
