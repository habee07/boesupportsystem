import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
public class BasicCsvWriter {
    public static void main(String[] args) {
        try {
            //We have to create the CSVPrinter class object 
            Writer writer = Files.newBufferedWriter(Paths.get("Testing.csv"));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Salary"));
            //Writing records in the generated CSV file
            csvPrinter.printRecord("Craig Sevell", 100000);
            csvPrinter.printRecord("Jane Doe", 20000);
            csvPrinter.printRecord("James Bonf", 30000);
            //Writing records in the form of a list
            csvPrinter.printRecord(Arrays.asList("Dev Bhatia", 4000));
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}