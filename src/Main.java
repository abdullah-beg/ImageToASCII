
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

    private static boolean mainLoop(String option) {

        switch (option.trim().toLowerCase()) {

            case "y" : return false;

        }

        return true;

    }
    
}
