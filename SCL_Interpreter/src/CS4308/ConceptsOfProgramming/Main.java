package CS4308.ConceptsOfProgramming;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner("C:\\Users\\heath\\OneDrive\\Desktop\\testing.scl"); // here we scan the given file

        String fileName = "testing.txt"; // name of the file

        System.out.println("Start reading to scan file: " + fileName); // print what file it is reading
        System.out.println();

        // while scanning print the current lex and token
        while(!scan.isDone()) {
            // formatting: '-' is left aligned, 18 fills the string up to 18 chars, '%' means what follows is an argument that will be formatted
            System.out.printf("%-18s :  %s \n", scan.currentLex(), scan.currentToken());
            scan.moveAhead();
        }

        // if scan is successful print it was successful
        if(scan.isSuccessful()) {
            System.out.println("SCAN IS SUCCESSFUL!");
        }
        // otherwise print an error message
        else {
            System.out.println(scan.errorMess());
        }
    }
}
