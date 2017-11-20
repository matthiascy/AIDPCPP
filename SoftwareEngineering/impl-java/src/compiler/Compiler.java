package compiler;

public class Compiler {
    protected Lexer lexer;
    protected Parser parser;
    protected Generator gen;
    protected Language language;
    protected String lang;

    public Compiler(String lang) {
        this.lang = lang.toLowerCase();
        language = new Language(this.lang);
        gen = language.createGenerator();
        lexer = language.createLexer();
        parser = language.createParser();
    }

    public void compile(ProgramText txt) {
        System.out.println("Compiling a: " + lang + " program.");
        lexer.scan(txt);
        parser.parse(txt);
        gen.generate(txt);
        System.out.println("Compilation finished");
    }
}
