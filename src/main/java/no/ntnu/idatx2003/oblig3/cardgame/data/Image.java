package no.ntnu.idatx2003.oblig3.cardgame.data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for loading and processing images.
 * This class provides methods to load images from files and perform basic image processing tasks.
 * The main method demonstrates how to load images from a folder and print their names.
 */
public class Image {
    /**
     * Main method to demonstrate image loading and processing.
     * This method loads images from a specified folder and prints their names.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Specify the path to your source folder containing the images
        String folderPath = "oblig3-cardgame-bidha001/src/main/cards_images/1C.png";

        // Create a File object for the folder
        File folder = new File(folderPath);

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Get a list of files in the folder
            File[] files = folder.listFiles();

            // Loop through each file in the folder
            for (File file : files) {
                // Check if the file is an image (you can add more specific checks if needed)
                if (file.isFile() && isImageFile(file.getName())) {
                    try {
                        // Load the image using ImageIO
                        BufferedImage image = ImageIO.read(file);

                        // Process the image as needed (e.g., display, manipulate, etc.)
                        // For simplicity, let's just print the image name
                        System.out.println("Loaded image: " + file.getName());
                    } catch (IOException e) {
                        // Handle any errors that occur during image loading
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("Folder does not exist or is not a directory.");
        }
    }

    /**
     * Helper method to check if a file has an image extension.
     * This method checks the file extension to determine if it is an image file.
     *
     * @param fileName The name of the file to check
     * @return true if the file has an image extension, false otherwise
     */
    private static boolean isImageFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif");
    }
}
