package tr.springcloudrabbitcsv.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tr.springcloudrabbitcsv.entity.EligibilityRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileReader {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final String fileRelativePathName = "/csv/testing.csv";

    private ObjectMapper objectMapper;

    public CsvFileReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<EligibilityRequest> readCsvFile() {
        List<EligibilityRequest> requests = new ArrayList<>();
        logger.info("Reading file: {}", fileRelativePathName);
        InputStream inputStream = TypeReference.class.getResourceAsStream(fileRelativePathName);
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String jsonLine = line.replace(",", "");
                EligibilityRequest request = objectMapper.readValue(jsonLine, EligibilityRequest.class);
                requests.add(request);
            }
            logger.info("File read successfully: {}", requests);
            return requests;
        } catch (Exception e) {
            logger.error("Error reading file: {}", e.getMessage());
            return requests;
        }
    }

}
