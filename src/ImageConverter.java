
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ImageConverter {

    private InputReader inputReader;

    private Color[][] imageColors;
    private int imageWidth;
    private int imageHeight;

    private String[][] asciiImage;

    private String symbols;
    private int charactersPerPixel;

    private static final double redWeight = 0.299;
    private static final double greenWeight = 0.587;
    private static final double blueWeight = 0.114;

    private PrintWriter printString;

    private ImageReader imageReader;

    private String outputFileName;
    private String outputFilePath;

    public ImageConverter(ImageReader imageReader) {

        inputReader = new InputReader();

        this.imageReader = imageReader;

        this.imageColors = imageReader.getImageColorArray();
        this.imageWidth = imageReader.getImageWidth();
        this.imageHeight = imageReader.getImageHeight();

        asciiImage = new String[imageHeight][imageWidth];

        convertImageToASCII();

    }

    private void convertImageToASCII() {

        System.out.println("~~~ Select Symbols ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (!setSymbols(inputReader.userInputString("Enter Symbols to be used: \n 1) '@%#*+=-:. '\n\n  2) '$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. '\n\n   Custom\n")));
        System.out.println("~~~ Characters Per Pixels ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (!setCharactersPerPixel(inputReader.userInputInteger("Enter Number of Characters per Pixel:      (Must be greater than 0)     Example: 2")));

        System.out.println("~~~ Output ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        convertImage();

        while (!createOutputFile());

        writeFile();

        System.out.println("Image Successfully Converted!");

    }

    private boolean setSymbols(String symbols) {

        switch (symbols.toLowerCase()) {

            case "1" : this.symbols = " .:-=+*#%@"; break;

            case "2" : this.symbols = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "; break;

            case "custom" : this.symbols = inputReader.userInputString("Enter Custom String:        (Dark -> Light)"); break;

            default : return false;

        }

        if (this.symbols.length() == 1) {
            System.out.println("String is too short! Using basic default symbols...");
            this.symbols = " .:-=+*#%@";

        }

        return true;

    }

    private boolean setCharactersPerPixel(int charactersPerPixel) {

        if (charactersPerPixel < 1) {
            return false;

        }

        this.charactersPerPixel = charactersPerPixel;
        return true;

    }
    
    private double calculatePixelGrayscale(Color pixel) {

        return (redWeight * pixel.getRed()) + (greenWeight * pixel.getGreen()) + (blueWeight * pixel.getBlue());

    }

    private String getApproximateSymbol(double value) {

        int index = (int)Math.round(((((value / 255)) * (symbols.length() - 1))));

        return Character.toString(symbols.charAt(index));

    }

    private void convertImage() {

        for (int height = 0; height < imageHeight; height++) {
            for (int width = 0; width < imageWidth; width++) {

                String holder = getApproximateSymbol(calculatePixelGrayscale(imageColors[height][width]));

                asciiImage[height][width] = "";

                for (int copy = 0; copy < charactersPerPixel; copy++) {
                    asciiImage[height][width] += holder;

                }

            }

        }

    }

    public void setOutputFilePath(String path) {

        if (path.trim().equals(".")) {
            outputFilePath = imageReader.getFilePath();
            return;

        } 

        outputFilePath = path;

    }

    public void setOutputFileName(String fileName) {

        if (fileName.equals(".")) {
            fileName = imageReader.getImageFileName().replace(".png", "");

        }


        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";

        }


        if (outputFilePath.endsWith("\\")) {
            outputFileName = outputFilePath + fileName;
            return;

        }

        outputFileName = outputFilePath + "\\" + fileName;

    }

    public boolean createOutputFile() {

        try {
            setOutputFilePath(inputReader.userInputString("Enter Output File Directory:       (Type '.' if same location as image)    Example: 'C:\\Users\\John\\Images\\'"));
            setOutputFileName(inputReader.userInputString("Enter Output File Name:            (Type '.' if same name as image)        Example: 'car.txt'"));
            printString = new PrintWriter(new File(outputFileName));
            return true;

        } catch (Exception e) {
            System.out.println("Invalid File Path!");

        }

        return false;

    }

    private void writeFile() {

        String output = "";

        for (int height = 0; height < imageHeight; height++) {
            for (int width = 0; width < imageWidth; width++) {

                output += "" + asciiImage[height][width];

            }

            output += "\n";

        }

        printString.println(output);

    }

    
}
