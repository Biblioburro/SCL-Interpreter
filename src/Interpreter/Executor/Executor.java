package Interpreter.Executor;
import Interpreter.Parser.*;
import Interpreter.Scanner.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Executor {

    //generate the executor from the provided parser.
    public Executor (parser p){
    varBlockTokens= p.getVarTokens();
    varBlockLex = p.getVarLex();

    bodyBlockLex = p.getBodyLex();
    bodyBlockTokens = p.getBodyTokens();

    identTable = p.getIdentTable();
    }
    //execute for the provided statements and identifier.
    public void execute(){
       //printIdentTable();
        //for loop to execute over every statement in the body
        for(int i =0 ; i< bodyBlockTokens.size();i++){

            ArrayList<String> currentSentence = bodyBlockTokens.get(i);
            //debug and demonstration printing showing state change
         /*   System.out.println("\nCurrent sentence being processed");
            System.out.println(currentSentence);
            System.out.println("output of sentence");
*/



            //switch for the first token of a sentence to determine the next
    switch(currentSentence.get(0)){
        case "WRITE_KEYWORD":
                //handles the case when the token is an identifier or an literal
               if(currentSentence.get(2).equals("IDENTIFIER")){
                   write(identLookup(bodyBlockLex.get(i).get(2)).currentValue);
               }else{
                   write(bodyBlockLex.get(0).get(2).substring(1,bodyBlockLex.get(0).get(2).length()-1));
               }
               break;
               //handle the case for
        case "WRITELN_KEYWORD":
                //handles the case when the token is an identifier or an literal
                if(currentSentence.get(2).equals("IDENTIFIER")){
                    writeLine(identLookup(bodyBlockLex.get(0).get(2)).currentValue);
                //handles literals
                }else{
                    writeLine(bodyBlockLex.get(0).get(2).substring(1,bodyBlockLex.get(0).get(2).length()-1));
                }
                break;
         //case to handle user input
        case "READLN_KEYWORD":
            readLine(identLookup(bodyBlockLex.get(i).get(2)));
            break;
        case "IDENTIFIER":
            //potential way to expand implementation for longer statements
            assign(identLookup(bodyBlockLex.get(i).get(0)),add(identLookup(bodyBlockLex.get(i).get(3)).currentValue,identLookup(bodyBlockLex.get(i).get(5)).currentValue));
    }


           // printIdentTable();
        }
    }
public void printIdentTable(){
    //print out the statements
    System.out.println("identTable");
    for(identifier id : identTable){
        System.out.println(id);
    }
}

//method to read the line
public void readLine(identifier id){
    id.currentValue = input.nextLine();
}
//function to add two integers and return their values as a string
public String add(String val1,String val2 ){
    return Integer.toString((Integer.parseInt(val1)+Integer.parseInt(val2)));
    }
    //assign the value to the
public void assign(identifier i , String newValue){
        i.currentValue = newValue;
}
//write to console without new line
public void write(String content){
    System.out.print(content);
}
//write to console with a new line
public void writeLine(String content){
    System.out.println(content);
}

    //return the identifier with the given name;
    public identifier identLookup(String name){

        for (identifier i : identTable) {
            if (i.name.equals(name)) {
                return i;
            }
        }
        System.out.println(name+"IDENTIFIER NOT FOUND");
        return null;
    }

    //Data Declaration
    //scanner for handling write key words.
    private static Scanner input = new Scanner(System.in);


    //structures provided from the parser and scanner
    //parallel Array Lists to store the sentences for the variable block as tokens and lexemes
    private ArrayList<ArrayList<String>> varBlockTokens = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> varBlockLex = new ArrayList<ArrayList<String>>();
    //parallel parallel Array Lists to store the sentences for the body block as tokens and lexemes
    private ArrayList<ArrayList<String>> bodyBlockTokens = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> bodyBlockLex = new ArrayList<ArrayList<String>>();
    //issues with using an arraylist for the ident table but a traditional array works ¯\_(ツ)_/¯
    private identifier[] identTable;

}
