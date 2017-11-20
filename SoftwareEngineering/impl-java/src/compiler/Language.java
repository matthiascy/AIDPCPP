package compiler;

public class Language {
    private String lang;
    /*
    private LexerFactory lexerFactory;
    private GeneratorFactory generatorFactory;
    private ParserFactory parserFactory;
    */

    public Language(String lang) {
        this.lang = lang;
        /*
        lexerFactory = new LexerFactory();
        generatorFactory = new GeneratorFactory();
        parserFactory = new ParserFactory();
        */
    }

    public Parser createParser() {
        return new ParserFactory().create(lang);
    }

    public Generator createGenerator() {
        return new GeneratorFactory().create(lang);
    }

    public Lexer createLexer() {
        return new LexerFactory().create(lang);
    }

}
