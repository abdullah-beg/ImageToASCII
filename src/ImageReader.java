import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public ImageReader() {
    
        inputReader = new InputReader();

        setupFile();
    
    }

    public void setupFile() {

        System.out.println("~~~ Load Image File ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (!loadImageFile(inputReader.userInputString("Enter Image File Directory:           Example: 'C:\\Users\\John\\Images\\car.png'"))) {}

    }

    private void setImageFileName() {


        String[] path =  filePath.split(Pattern.quote(File.separator));

        imageFileName = path[path.length - 1];

        imageFileName = imageFileName.replace("\\", "");

    }

    private void setFilePath(String filePath) {

        this.filePath = filePath;

    }


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

    private void createImageColorArray() {

        imageColorArray = new Color[imageHeight][imageWidth];

        for (int height = 0; height < imageHeight; height++) {
            for (int width = 0; width < imageWidth; width++) {

                imageColorArray[height][width] = new Color(image.getRGB(width, height));

            }

        }

    }

    public void setImageWidth(int imageWidth) {

        this.imageWidth = imageWidth;

    }

    public void setImageHeight(int imageHeight) {

        this.imageHeight = imageHeight;

    }

    public Color[][] getImageColorArray() {

        return imageColorArray;

    }

    public int getImageWidth() {

        return imageWidth;

    }

    public int getImageHeight() {

        return imageHeight;

    }

    public String getFilePath() {

        return filePath;

    }

    public String getImageFileName() {

        return imageFileName;

    }
 
}
