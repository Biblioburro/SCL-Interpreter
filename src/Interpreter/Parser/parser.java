package Interpreter.Parser;
import Interpreter.Scanner.*;

import java.util.ArrayList;
public class parser {
    //constructor for provided data
   public parser(ArrayList<String> inputTokens,ArrayList<String> inputFile,ArrayList<String> identTable){
        tokenList = inputTokens;
        lexemes = inputFile;
//intitalize the identifier table using the provided names for the



    }

   public parser(scanner sc){
    lexemes = sc.getLexemes();
    tokenList = sc.getTokens();
    identTable = new identifier[sc.getIdentTable().size()];
    for(int i =0 ;i< sc.getIdentTable().size();i++){
        identTable[i] = new identifier(sc.getIdentTable().get(i));
    }


    }
    //function to evaluate the full list of tokens.
    public void parse(){
        //1.break up the sentences in the declaration block
        breakUpVarBlock();
        //2.update the identTable with their initial values and data types
        processVarBlock();
        //3.break up the body into its part sentences
        breakUpBodyBlock();
        //4.process the body block sentence by sentence
        processBodyBlock();
    }
    public void processBodyBlock(){
       //arraylist
       ArrayList<String> currentSentence;
       //for loop to go sentence by sentence
        for(int i =0 ; i<bodyBlockTokens.size();i++){
        currentSentence = bodyBlockTokens.get(i);
        switch(currentSentence.get(0)){
            case "WRITE_KEYWORD":
                statementList.add("Write Statement");
                break;
            case "WRITELN_KEYWORD":
                statementList.add("Write line Statement");
                break;
            case "READ_KEYWORD":
                statementList.add("Read Statement");
                break;
            case "READLN_KEYWORD":
                statementList.add("Read line Statement");
                break;

            case "IDENTIFIER":
                statementList.add("Assignment Statement");
        }
        }



    }

    //look up method for a given identifier


    //methods for handling each of the statements




    //method to process the var sentences
    public void processVarBlock(){
        //declaration for initializing the identifiers
       ArrayList<String> varNames = new ArrayList<>();
       String dataType;

      // sentences in parallel arrays of tokens and lexemes
      // for loop to handle each sentence in the block
        for(int i = 0; i<varBlockTokens.size(); i ++){
            //for loop to process each sentence

            //temporary arrays for handling the current sentence
            ArrayList<String> currSentenceT = varBlockTokens.get(i);
            ArrayList<String> currSentenceL = varBlockLex.get(i);

            //get the data type for the current sentence
            dataType = currSentenceT.get(currSentenceT.size()-1);

            //for loop to add the identifiers
            for(int j = 0;j<currSentenceT.size();j++) {
                //handle the case where an identifier is found
                // add its name, data type and initial value
                //NOTE: potentially add error checking to see if its been declared/ same name
                if(currSentenceT.get(j).equals("IDENTIFIER")){
                  identifier currIdent =  identLookup(currSentenceL.get(j));
                 currIdent.name = currSentenceL.get(j);
                 currIdent.dataType = dataType;
                 currIdent.currentValue = defaultValue(dataType);
                }
            }
        }
    }

    //method to break up the declaration block into sentences
    public void breakUpVarBlock(){

        ArrayList<String> currentSentenceTokens = new ArrayList<>();
        ArrayList<String> currentSentenceLexemes = new ArrayList<>();
        //for loop to iterate through all the tokens within the body block
        for(int i =4;!tokenList.get(i).equals("BEGIN_KEYWORD") ;i++){
            //while loop to construct an arraylist for the current sentence
            while(!tokenList.get(i).equals("SEMICOLON")){
            currentSentenceLexemes.add(lexemes.get(i));
            currentSentenceTokens.add(tokenList.get(i));
            i++;
            }
            //add the currentSentence to the bodyBlock
            varBlockTokens.add(currentSentenceTokens);
            varBlockLex.add(currentSentenceLexemes);
            //reset the current sentence
            currentSentenceTokens = new ArrayList<>();
            currentSentenceLexemes = new ArrayList<>();
        }
    }

    //method to break up the body block
    public void breakUpBodyBlock(){
        System.out.println("in breakup body block");
        ArrayList<String> currentSentenceTokens = new ArrayList<>();
        ArrayList<String> currentSentenceLexemes = new ArrayList<>();
        //for loop to iterate through all the tokens within the body block
        for(int i =tokenList.indexOf("BEGIN_KEYWORD")+1;i< tokenList.size() ;i++){
            //while loop to construct an arraylist for the current sentence
            while(!tokenList.get(i).equals("SEMICOLON")){
                currentSentenceLexemes.add(lexemes.get(i));
                currentSentenceTokens.add(tokenList.get(i));
                i++;
            }
            //add the currentSentence to the bodyBlock
            bodyBlockTokens.add(currentSentenceTokens);
            bodyBlockLex.add(currentSentenceLexemes);
            //reset the current sentence
            currentSentenceTokens = new ArrayList<>();
            currentSentenceLexemes = new ArrayList<>();
        }
    }




    //return the identifier with the given name;
   public identifier identLookup(String name){

            for (identifier i : identTable) {
                if (i.name == name) {
                    return i;
                }
            }
       System.out.println("IDENTIFIER NOT FOUND");
       return null;
    }
    //store the default value
    public String defaultValue(String dataType){
       //expand this as we add more data types
        //not sure if this is the correct default types for scl
       switch (dataType){
           case "INTEGER_KEYWORD":
               return "0";
           case "FLOAT_KEYWORD":
                return "0.0f";
           case "DOUBLE_KEYWORD":
               return "0.0";
           case "CHARACTER_KEYWORD":
           case "STRING_KEYWORD":
               return "";
       }
       return "UNRECOGNIZED TYPE";
    }

    //debug function for printing the current contents of the parser
    public void printParser(){
        System.out.println("Parsers Current Values:");

        //print out the copied values for assurance
       /* for(int i = 0 ; i< lexemes.size();i++) {
            System.out.println(lexemes.get(i)+"\t"+ tokenList.get(i));

        }*/

        System.out.println("Var Block :");
        for(ArrayList<String> sentence : varBlockTokens){
            System.out.println(sentence + "\tInitialization statement");
        }

        System.out.println("Body Block :");
        for(int i = 0 ; i <bodyBlockTokens.size();i++ ){
            System.out.println(bodyBlockTokens.get(i) + "\t"+statementList.get(i));
        }


        //print out the identifiers and their current values
        System.out.println("identTable:");
       for(identifier id : identTable){
           System.out.println(id);
       }

        System.out.println();
    }

//DATA DECLARATION
    //list of lexemes from the file
    private ArrayList<String> lexemes;
    //store the entire program here
    private ArrayList<String> tokenList;
    //store the list of statements
    private ArrayList<String> statementList = new ArrayList<>();
    //parallel Array Lists to store the sentences for the variable block as tokens and lexemes
    private ArrayList<ArrayList<String>> varBlockTokens = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> varBlockLex = new ArrayList<ArrayList<String>>();
    //parallel parallel Array Lists to store the sentences for the body block as tokens and lexemes
    private ArrayList<ArrayList<String>> bodyBlockTokens = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> bodyBlockLex = new ArrayList<ArrayList<String>>();
    //issues with using an arraylist for the ident table but a traditional array works ¯\_(ツ)_/¯
    private identifier[] identTable;
}
