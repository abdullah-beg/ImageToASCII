
import java.util.Scanner;

public class InputReader {
    
    private Scanner userInput;

    /**
     * Constructor for the InputReader.
     */
    public InputReader() {

        userInput = new Scanner(System.in);

    }

    /**
     * Get a String input from the user.
     * @param printMessage The output message.
     * @return Returns the inputting string.
     */
    public String userInputString(String printMessage) {

        System.out.println(printMessage);
        System.out.print(">");

        return userInput.nextLine();

    }

    /**
     * Get an Integer input from the user.
     * @param printMessage The output message.
     * @return Returns the inputting integer.
     */
    public int userInputInteger(String printMessage) {

        System.out.println(printMessage);
        System.out.print(">");

        try {
            return Integer.parseInt(userInput.nextLine());

        } catch (Exception e) {
            return -1;

        }

    }

}
