import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * This class is a test class for the Copy class, which is responsible for copying files.
 * It contains test methods to verify the functionality of the copyFile() method.
 */
public class CopyTest {

    private Path sourceFile;
    private Path destinationFile;

    @Before
    public void setUp() throws IOException {
        // Create a temporary source file
        sourceFile = Files.createTempFile("source", ".txt");
        Files.write(sourceFile, "Hello, World!".getBytes());

        // Create a temporary destination file path
        destinationFile = Paths.get(sourceFile.toString().replace("source", "destination"));
    }

    @After
    public void tearDown() throws IOException {
        // Delete the temporary files
        Files.deleteIfExists(sourceFile);
        Files.deleteIfExists(destinationFile);
    }

    @Test
    public void testCopyFile() throws IOException {
        Copy.copyFile(sourceFile.toString(), destinationFile.toString());

        // Verify that the destination file exists
        assertTrue(Files.exists(destinationFile));

        // Verify that the content of the destination file is the same as the source file
        assertEquals(Files.readAllLines(sourceFile), Files.readAllLines(destinationFile));
    }

    @Test(expected = IOException.class)
    public void testCopyFileSourceNotFound() throws IOException {

        Copy.copyFile("nonexistent.txt", destinationFile.toString());
    }

    @Test(expected = IOException.class)
    public void testCopyFileDestinationExists() throws IOException {
        // Create the destination file
        Files.createFile(destinationFile);


    }
}