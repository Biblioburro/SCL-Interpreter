package CS4308.ConceptsOfProgramming;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Scanner {

    // defining vars
    private StringBuilder input = new StringBuilder();
    private Token token;
    private String lex;
    private boolean finished = false;
    private String errorMess = "";
    private static Set<Character> blankChars = new HashSet<>();

    // static block to have one copy for all instances of the class since static{} handles initialization
    static {
        // adding to the HashSet
        blankChars.add('\r');
        blankChars.add('\n');
        blankChars.add((char) 8);
        blankChars.add((char) 9);
        blankChars.add((char) 11);
        blankChars.add((char) 12);
        blankChars.add((char) 32);
    }

    public Scanner(String fileName) {
        try (Stream<String> s = Files.lines(Paths.get(fileName))) {
            s.forEach(input::append);
        }
        catch (IOException ex) {
            finished = true;
            errorMess = "Unable to read file:  " + fileName;
            return;
        }

        moveAhead();
    }

    // moveAhead if not finished, if finished then we stop, if something unexpected given an error message
    public void moveAhead() {
        if (finished) {
            return;
        }

        if (input.length() == 0) {
            finished = true;
            return;
        }

        ignoreWhiteSpace();

        if (findNextToken()) {
            return;
        }

        finished = true;

        if (input.length() > 0) {
            errorMess = "Unexpected symbol: " + input.charAt(0);
        }
    }

    // need to ignore white spaces otherwise it would be counted
    private void ignoreWhiteSpace() {
        int charsToDelete = 0;

        while (blankChars.contains(input.charAt(charsToDelete))) {
            charsToDelete++;
        }

        if(charsToDelete > 0) {
            input.delete(0, charsToDelete);
        }
    }

    // finds next token if there is any if not returns false
    private boolean findNextToken() {
        // building a data structure that goes the other way would probably be a better way to do this because this tries for each token
        // example: first char is 'f' the token can be 'for' or an identifier
        // it will NOT be a '{' or '=' so it drops the unnecessary comparisons and narrows it down to 2 (sometimes only 1)
        // not sure how I could add that functionality just yet, but it would be ideal to increase performance
        for (Token t : Token.values()) {
            int end = t.matchEnd(input.toString().toLowerCase());

            if (end != -1) {
                token = t;
                lex = input.substring(0, end);
                input.delete(0, end);
                return true;
            }
        }
        return false;
    }

    // get current token
    public Token currentToken() {
        return token;
    }

    // return current lex and make lower case
    public String currentLex() {
        return lex.toLowerCase();
    }

    // if its successful then there isn't an error message
    public boolean isSuccessful() {
        return errorMess.isEmpty();
    }

    // return error message string
    public String errorMess() {
        return errorMess;
    }

    // return finished if its done
    public boolean isDone() {
        return finished;
    }


}
