package tr.springcloudrabbitcsv.file;

import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileReader {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    String fileName = "allpago/src/main/resources/input/03.csv";

//    try (
//    InputStreamReader streamReader =
//            new InputStreamReader(is, StandardCharsets.UTF_8);
//    BufferedReader reader = new BufferedReader(streamReader)) {
//
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }

    public void readCsvFile() {
        logger.info("Reading file: {}", fileName);
        InputStream inputStream = TypeReference.class.getResourceAsStream("/csv/testing.csv");
        List<String> lines = new ArrayList<>();
        try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader)) {
            // Read the file
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.replace(",",""));
            }
            logger.info("File read successfully: {}", lines);
        } catch (Exception e) {
            logger.error("Error reading file: {}", e.getMessage());
        }


    }

}
