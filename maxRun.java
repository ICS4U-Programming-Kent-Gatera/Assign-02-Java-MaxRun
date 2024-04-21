import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * My program reads from an input file, and writes the max run of input to an output file.
 *
 * @author  Kent Gatera
 * @version 1.0
 * @since   2024-Apr - 14
 */

public class maxRun {

    /**
    * This is where the main code will be.
    *
    * @param args
    */
    
    public static void main(String[] args) {
        // Write the output to a file
        try {
            String filePath = "input.txt";
            File inputFile = new File(filePath);
            Scanner fileReader = new Scanner(inputFile);
            String outputFilePath = "output.txt";
            // Initializing file writer object and output file directory.
            FileWriter fileWriter = new FileWriter(outputFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (fileReader.hasNextLine()) {
                String inString = fileReader.nextLine();
                int[] result = runCalc(inString);
                // We use the '999' because there is no line that many characters long (As a default case).
                if (result[0] == 999) {
                    bufferedWriter.write("No recurring characters in this line.\n");

                } else {
                    char maxRunChar = (char) result[1];
                    int maxRunLength = result[0];
                    bufferedWriter
                            .write("The max run is '" + maxRunChar + "' and '" + maxRunLength + "' characters long.\n");
                }
            }
            // Close the writer and file reader when done
            bufferedWriter.close();
            fileReader.close();
            System.out.println("Output written to " + outputFilePath);
        } catch (IOException e) {
            // Print error message if file cannot be written
            System.out.println("Error: File cannot be written.");
        }
    }
    
    /**
    * This is where the main code will be.
    *
    * @param str Passing the string input.
    */
    public static int[] runCalc(String str) {
        int maxRun = 0;
        // Reset the most recurring character for each line
        char runChar = '0'; 
        int len = str.length();
        // For loop iterating over the string.
        for (int iter = 0; iter < len; iter++) {
            // Picking a character to use as comparison.
            char currentLetter = str.charAt(iter);
            // Reset character run length for each new character
            int charRun = 1;
            // While the next character is withing the size of string, we compare it to our chose char.
            while (iter + 1 < len && str.charAt(iter + 1) == currentLetter) {
                charRun++;
                // Move to the next char.
                iter++;
            }

            // If a character does have repetitions.
            if (charRun > maxRun && charRun > 1) {
                maxRun = charRun;
                runChar = currentLetter;
            // Checking if the line has no recurrences.
        } else if (charRun == 1 && runChar == '0') {
                // Marking "999" as a default case.
                maxRun = 999;
            }
        }
        // Returning a list of out values.(Run and index of run).
        return new int[]{maxRun, runChar};
    }
}

