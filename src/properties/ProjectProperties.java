package properties;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String PROPERTIES_ERROR = "Properties error ";

    public static String INPUT_FILE;
    public static String SCHEMA_FILE;
    public static String PARSER_TYPE;

    public static void loadProperties(String fileName) {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(fileName);
            property.load(fis);

            INPUT_FILE = property.getProperty("filename.input");
            SCHEMA_FILE = property.getProperty("filename.schema");
            PARSER_TYPE = property.getProperty("parser");
        } catch (IOException e) {
            LOGGER.error(PROPERTIES_ERROR + e);
        }
    }
}
