package CS4308.ConceptsOfProgramming;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token {
    // tokens defined here
    TK_ASSIGN (":="),
    TK_ADDITION ("\\+"),
    TK_SUBTRACTION ("-"),
    TK_MULT ("//*"),
    TK_DIVISION("/"),
    TK_EQUAL ("="),
    TK_UNEQUAL("<>"),
    TK_L_THAN ("<"),
    TK_G_THAN (">"),
    TK_L_EQUAL ("<="),
    TK_G_EQUAL (">="),
    TK_PARENOP ("\\("),
    TK_PARENCL ("\\)"),
    TK_INDEXOP ("\\["),
    TK_INDEXCL ("\\]"),
    TK_COMMENTOP("\\{"),
    TK_COMMENTCL("\\}"),
    // keywords
    TK_BEGIN ("begin"),
    TK_END ("end"),
    TK_PROGRAM("program"),
    TK_AND("and"),
    TK_ARRAY("array"),
    TK_CASE("case"),
    TK_CONST("const"),
    TK_DIV("div"),
    TK_DO("do"),
    TK_DOWNTO("downto"),
    TK_ELSE("else"),
    TK_FILE("file"),
    TK_FOR("for"),
    TK_FUNCTION("function"),
    TK_GOTO("goto"),
    TK_IF("if"),
    TK_IN("in"),
    TK_LABEL("label"),
    TK_MOD("mod"),
    TK_NIL("nil"),
    TK_NOT("not"),
    TK_OF("of"),
    TK_OR("or"),
    TK_PACKED("packed"),
    TK_PROCEDURE("procedure"),
    TK_RECORD("record"),
    TK_REPEAT("repeat"),
    TK_SET("set"),
    TK_THEN("then"),
    TK_TO("to"),
    TK_TYPE("type"),
    TK_UNTIL("until"),
    TK_VAR("var"),
    TK_WHILE("while"),
    TK_WITH("with"),

    INTEGER ("\\d"),
    IDENTIFIER("\\w+"),

    TK_COMMA(","),
    TK_COLON(":"),
    TK_SEMI (";"),
    TK_QUOTE("'");

    private final Pattern pattern; // create Pattern for pattern matching

    Token(String regex) {
        pattern = Pattern.compile("^" + regex); // compile to create pattern using compile()
    }

    int matchEnd(String s) {
        s.toLowerCase();
        Matcher m = pattern.matcher(s); // get a matcher object from pattern

        if (m.find()) {
            return m.end();
        }
        return -1;
    }

}
