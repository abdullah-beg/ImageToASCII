
public class Main {

    public static void main(String[] args) {

        InputReader inputReader = new InputReader();
        ImageReader imageReader = new ImageReader();
        ImageConverter imageConverter = new ImageConverter(imageReader);

        while (!mainLoop(inputReader.userInputString("Convert Another Image? (Y/N) :"))) {
            imageReader = new ImageReader();
            imageConverter = new ImageConverter(imageReader);

        }

    }

    /**
     * The main loop which allows the user to convert more images.
     * @param option The user's input.
     * @return Returns true if the user has entered something that starts with Y.
     */
    private static boolean mainLoop(String option) {

        return (!option.toLowerCase().trim().startsWith("y"));

    }
    
}
