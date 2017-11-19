package rbs;

public class KnowledgeBase {
    private FactBase fact_base_init;
    private RuleBase rule_base;
    private FactBase fact_base_sat;

    public KnowledgeBase() {
    }

    public KnowledgeBase(String filepath) {

    }

    public FactBase initFactBase() {
        return null;
    }

    public RuleBase ruleBase() {
        return null;
    }

    public FactBase satFactBase() {
        return null;
    }

    public String toString() {
        return null;
    }

    public void forwardChaining() {

    }

    public static void main(String[] args) {
        KnowledgeBase kb = new KnowledgeBase();
        System.out.println(kb);
    }
}
