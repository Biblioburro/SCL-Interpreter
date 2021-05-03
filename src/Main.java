


import java.io.*;
import java.util.Scanner;

import Interpreter.Executor.Executor;
import Interpreter.Parser.*;
import Interpreter.Scanner.*;

import javax.swing.text.html.parser.Parser;


public class Main {
    public static void main(String[] args){
        //Java provide Scanner for user input
        Scanner scan = new Scanner(System.in);
        // User defined scanner for processing files
        scanner sc;
        //code for user input of file path
        /*
        System.out.println("Enter the absolute path of the file you want to enter");
        sc = new scanner(scan.nextLine());
        */
         //code for predefined input file
        sc = new scanner(new File("C:\\Users\\Samme\\Documents\\1-KSU\\SPRING-2021\\CPL\\Course Projects\\COURSE PROJECT REPO\\src\\testing.scl"));
        System.out.println("IF YOU GET A FILE ERROR CHANGE THE ABSOLUTE PATH TO " +
                                "WHEREVER YOU HAVE THE TESTING FILE SAVED");
        //methods for processing and printing the scanner.
        //sc.printScanner();
        sc.processFile();
        parser parse = new parser(sc);
        parse.parse();
        //parse.printParser();
        Executor exec = new Executor(parse);
        exec.execute();




    }
}
