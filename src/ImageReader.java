import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;


public class ImageReader {

    private BufferedImage image;

    private Color[][] imageColorArray;

    private int imageWidth;
    private int imageHeight;

    private String filePath;
    private String imageFileName;

    private InputReader inputReader;

    /**
     * Constructor for ImageReader
     */
    public ImageReader() {
    
        inputReader = new InputReader();

        setupFile();
    
    }

    /**
     * Get the user to enter a valid file path to a png image.
     */
    public void setupFile() {

        System.out.println("~~~ Load Image File ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (!loadImageFile(inputReader.userInputString("Enter Image File Directory:           Example: 'C:\\Users\\John\\Images\\car.png'"))) {}

    }

    /**
     * Set the image file name.
     */
    private void setImageFileName() {


        String[] path =  filePath.split(Pattern.quote(File.separator));

        imageFileName = path[path.length - 1];

        imageFileName = imageFileName.replace("\\", "");

    }

    /**
     * Set the file path.
     * @param filePath The file path to be set to the field.
     */
    private void setFilePath(String filePath) {

        this.filePath = filePath;

    }


    /**
     * Loads the image file.
     * @param fileName The file path of the image.
     * @return boolean dictating if the image was successfully loaded.
     */
    public boolean loadImageFile(String fileName) {

        try { 
            image = ImageIO.read(new File(fileName));
            setFilePath(fileName);
            setImageFileName();
            setFilePath(filePath.replace(imageFileName,""));
            setImageWidth(image.getWidth());
            setImageHeight(image.getHeight());
            createImageColorArray();
            return true;
        
        } catch (Exception e) {
            System.out.println("File not found!");
        
        }

        return false;

    }

    /**
     * Creates a new Color Array from each of the pixels in the image.
     */
    private void createImageColorArray() {

        imageColorArray = new Color[imageHeight][imageWidth];

        for (int height = 0; height < imageHeight; height++) {
            for (int width = 0; width < imageWidth; width++) {

                imageColorArray[height][width] = new Color(image.getRGB(width, height));

            }

        }

    }

    /**
     * Set the image's width.
     * @param imageWidth The value to set the imageWidth field.
     */
    public void setImageWidth(int imageWidth) {

        this.imageWidth = imageWidth;

    }

    /**
     * Set the image's height.
     * @param imageHeight The value to set the imageHeight field.
     */
    public void setImageHeight(int imageHeight) {

        this.imageHeight = imageHeight;

    }

    /**
     * Getter for the imageColorArray field.
     * @return Returns the imageColorArray field.
     */
    public Color[][] getImageColorArray() {

        return imageColorArray;

    }

    /**
     * Getter for the imageWidth field.
     * @return Returns the imageWidth field.
     */
    public int getImageWidth() {

        return imageWidth;

    }

    /**
     * Getter for the imageHeight field.
     * @return Returns the imageHeight field.
     */
    public int getImageHeight() {

        return imageHeight;

    }

    /**
     * Getter for the filePath field.
     * @return Returns the filePath field.
     */
    public String getFilePath() {

        return filePath;

    }

    /**
     * Getter for the imageFileName field.
     * @return Returns the imageFileName field.
     */
    public String getImageFileName() {

        return imageFileName;

    }
 
}
