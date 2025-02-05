import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {

    public static void main(String[] args) {
        String filePath = "my_text_file.txt"; // Replace with your desired file path

        // Write to the file
        writeToFile(filePath, "This is the initial content.\n");
        writeToFile(filePath, "Adding more text.\n", true); // Append mode

        // Read from the file
        readFromFile(filePath);

        // Modify the file (replace a line)
        modifyFile(filePath, "This is the initial content.", "This is the modified content.");
        readFromFile(filePath); // Read again to see the changes
    }

    // Method to write to a file
    public static void writeToFile(String filePath, String content, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(content);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    // Overload the writeToFile method for append = false by default
    public static void writeToFile(String filePath, String content) {
        writeToFile(filePath, content, false);
    }

    // Method to read from a file
    public static void readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("\n--- Reading from file ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }
    }

    // Method to modify a line in a file
    public static void modifyFile(String filePath, String oldLine, String newLine) {
        try {
            // Read all lines from the file
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals(oldLine)) {
                        line = newLine; // Replace the line
                    }
                    fileContent.append(line).append("\n");
                }
            }

            // Write the modified content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(fileContent.toString());
            }
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println("Error modifying the file: " + e.getMessage());
        }
    }
}