package Interpreter.Scanner;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class scanner {
    //constructor for provided contents
   public scanner(String fileContents){
        contents = fileContents;
    }
    //constructor for a provided file
    public scanner(File inputFile){
       try {

           Scanner progRead = new Scanner(inputFile);

           //loop to append the contents of the file to the contents variable
            while(progRead.hasNext()){
                contents += progRead.nextLine();
            }
       }catch (IOException e){
           System.out.println("invalid path ");
       }
    }
    //method for processing the contents into their subsequent lexemes
    public void processFile() {
       String currentLex = "";

       for(int i =0; i< contents.length();i++){
           //statement for ignoring comments
           if(contents.charAt(i) == '{') {
               if(currentLex.length() >0){
                   lexemes.add(currentLex);
                   currentLex = "";
               }
               i++;
               while(contents.charAt(i) != '}' && i <contents.length()-1){
                  // currentLex+= contents.charAt(i);
                   i++;
               }
             //  currentLex +="\'";
             //  lexemes.add(currentLex);
               currentLex = "";
               //statement for handling string literals
           }else if(contents.charAt(i)== '\'') {
            if(currentLex.length() >0){
                lexemes.add(currentLex);
                currentLex = "";
            }
            //beggining of string literal
            currentLex += "\'";
            i++;
            //
            while(contents.charAt(i) != '\'' && i <contents.length()-1){
                currentLex+= contents.charAt(i);
                i++;
            }
            currentLex +="\'";
            lexemes.add(currentLex);
            currentLex = "";

               //statement for handling blank characters
           }else if(contents.charAt(i)==' ' || contents.charAt(i)=='\t'){
               //we have a currently constructed lex
               if(currentLex.length()!=0){
                   //adding currentLex and resetting it
                lexemes.add(currentLex);
                currentLex = "";
               }
           //case to handle stopping characters that separate
           }else if(isStoppingCharacter(contents.charAt(i))){

              //we have a currently cosntructed lex
               if(currentLex.length()!=0){
                   //adding currentLex and resetting it
                   lexemes.add(currentLex);
                   currentLex = "";
               }
               //add the current stopping character to the list of lexemes
               lexemes.add(contents.charAt(i)+"");
           }else {
               //build the current lex with the character
               currentLex+=contents.charAt(i);
           }


       }
        tokenize();


    }

    //process the arraylist of lexemes into tokens
    public void tokenize(){
       //for loop to process the lexemes into their tokens
    for(int i = 0 ; i<lexemes.size();i++){
        if(lexemes.get(i).charAt(0)=='\''){

        tokens.add("STRING_LITERAL");
           //if the lexeme starts with a digit it's a integer literal
        }else if (Character.isDigit(lexemes.get(i).charAt(0))) {
            tokens.add("INTEGER_LITERAL");


        }else if(isReserved(lexemes.get(i))){
            tokens.add(tokenLookup(lexemes.get(i)));


            //case where it's an identifier
        }else{
            //add code to identify whether the identifier already exists
            if(!identTable.contains(lexemes.get(i))) {
             identTable.add(lexemes.get(i));
            }
        tokens.add("IDENTIFIER");
        }

    }
  //remove duplicated by using a linkedhashset
    if(!identTable.isEmpty()){
        identTable.remove(0);
    }

    }
    //returns whether the provided string is a keyword
    public boolean isReserved(String lex){

       switch(lex){
           case "Read":
           case "Readln":
           case "program":
           case "Begin":
           case "Write":
           case "Writeln":
           case "Integer":
           case "var":
           case "End":
           case ",":
           case "(":
           case ")":
           case ";":
           case ":":
           case ":=":
           case "+":
           case "-":
           case "/":
           case "*":
           case "=":
           case "int":
           case ">":
           case "<":
           case "<=":
           case "=>":
           case "[":
           case "]":
           case "{":
           case "}":
           case "if":
           case "else":
           case "for":
           case "function":
           case "goto":
           case "in":
           case "label":
           case "mod":
           case "nil":
           case "not":
           case "of":
           case "or":
           case "packed":
           case "procedure":
           case "record":
           case "repeat":
           case "set":
           case "then":
           case "to":
           case "type":
           case "until":
           case "while":
           case "with":

           // add cases as you add keywords to token lookup
               return true;
       }
//
       return false;
    }
    //return the token for the given reserved work
    public String tokenLookup(String lex) {
        String token;
        switch (lex) {
            case "Read":
                return "READ_KEYWORD";
            case "Readln":
                return "READLN_KEYWORD";
            case "program":
                return "PROGRAM_KEYWORD";
            case "Begin":
                return "BEGIN_KEYWORD";
            case "Write":
                return "WRITE_KEYWORD";
            case "Writeln":
                return "WRITE_KEYWORD";
            case "Integer":
                return "INTEGER_KEYWORD";
            case "var":
                return "VAR_KEYWORD";
            case "End":
                return "END_KEYWORD";
            // add the remaining lexemes and return their tokens
            case ",":
                return "COMMA";
            case "(":
                return "LEFT_PAREN";
            case ")":
                return "RIGHT_PAREN";
            case ";":
                return "SEMICOLON";
            case":":
                return "COLON";
            case ":=":
                return "ASSIGNMENT_OP";
            case "+":
                return "ADDITION_OP";
            case "-":
                return "SUBTRACTION_OP";
            case "/":
                return "DIVISION_OP";
            case "*":
                return "MULTIPLICATION_OP";
            case "=":
                return "EQUAL_OP";
            case "int":
                return "INT_VAR";
            case ">":
                return "GRT_THAN";
            case "<":
                return "LESS_THAN";
            case "<=":
                return "LESS_THANEQUAL";
            case "=>":
                return "GRT_THANEQUAL";
            case "[":
                return "OPEN_INDEX";
            case "]":
                return "CLOSED_INDEX";
            case "{":
                return "OPEN_BRACKET";
            case "}":
                return "CLOSED_BRACKET";
            case "if":
                return "IF_TK";
            case "else":
                return "ELSE_TK";
            case "for":
                return "FOR_TK";
            case "function":
                return "FUNCTION_TK";
            case "goto":
                return "GOTO_TK";
            case "in":
                return "IN_TK";
            case "label":
                return "LABEL_TK";
            case "mod":
                return "MOD_TK";
            case "nil":
                return "NIL_TK";
            case "not":
                return "NOT_TK";
            case "of":
                return "OF_TK";
            case "or":
                return "OR_TK";
            case "packed":
                return "PACKED_TK";
            case "procedure":
                return "PROCEDURE_TK";
            case "record":
                return "RECORD_TK";
            case "repeat":
                return "REPEAT_TK";
            case "set":
                return "SET_TK";
            case "then":
                return "SET_THEN";
            case "to":
                return "TO_TK";
            case "type":
                return "TYPE_TK";
            case "until":
                return "UNTIL_TK";
            case "while":
                return "WHILE_TK";
            case "with":
                return "WITH_TK";
        }
        return "!unrecognized!";
    }

    //method to return whether ch is a stopping character
    public boolean isStoppingCharacter(char ch){
       switch(ch){
           case ';':
           case ',':
           case '{':
           case '}':
           case '(':
           case ')':
           case ':':
               return true;
           default:
               return false;
       }
    }


    //Accessor methods
    public ArrayList<String> getLexemes(){
       return lexemes;
    }
    public ArrayList<String> getTokens(){
        return tokens;
    }
    public ArrayList<String> getIdentTable(){
       return identTable;
    }

    //utility method to print the scanner
    public void printScanner(){
        System.out.println(contents);
        for(int i = 0 ; i< lexemes.size();i++) {
            System.out.println(lexemes.get(i)+"\t"+ tokens.get(i));

        }
        System.out.println(identTable);
       // System.out.println(tokens);

    }

private String contents = "";
private ArrayList<String> lexemes =new ArrayList<>();
private ArrayList<String> tokens = new ArrayList<>();
private ArrayList<String>identTable = new ArrayList<>();

}
