import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Copy {
    public static void main(String[] args) {

        // Check if the number of command line arguments is correct
        if (args.length != 2) {
            System.out.println("Use: java Copy <source-file> <destination-file>");
            return;
        }

        // Get agrs[0] as sourceFilePath and args[1] as destioationFilePath
        String sourceFilePath = args[0];
        String destinationFilePath = args[1];
        
        // Check if the source file exists
            try {
                // Perform the copy operation With copyFile Method below 
                copyFile(sourceFilePath, destinationFilePath);
                System.out.println("File copied successfully.");
            } catch (FileNotFoundException e) {
                System.out.println("Error: Source file not found.");
            } catch (FileAlreadyExistsException e) {
                System.out.println("Error: Destination file already exists.");
            } catch (IOException e) {
                System.out.println("Error: An I/O error occurred.");
            }
    }

    public static void copyFile(String sourceFilePath, String destinationFilePath) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        Path destinationPath = Paths.get(destinationFilePath);

        // Copy the file using Files.copy
        Files.copy(sourcePath, destinationPath);
    }
}