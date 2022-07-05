
import java.util.Scanner;

public class InputReader {
    
    private Scanner userInput;

    public InputReader() {

        userInput = new Scanner(System.in);

    }

    public String userInputString(String printMessage) {

        System.out.println(printMessage);
        System.out.print(">");

        return userInput.nextLine();

    }

    public int userInputInteger(String printMessage) {

        System.out.println(printMessage);
        System.out.print(">");

        try {
            return Integer.parseInt(userInput.nextLine());

        } catch (Exception e) {
            return -1;

        }

    }

    public Scanner getUserInput() {

        return userInput;

    }

}
