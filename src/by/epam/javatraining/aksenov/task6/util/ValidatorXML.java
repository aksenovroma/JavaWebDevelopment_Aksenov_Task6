package by.epam.javatraining.aksenov.task6.util;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorXML {
    private static final Logger LOGGER = Logger.getRootLogger();

    public static boolean validate(String fileName, String schemaName) {
        if (fileName != null && schemaName != null) {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);

            try {
                Schema schema = factory.newSchema(schemaLocation);
                Validator validator = schema.newValidator();
                Source source = new StreamSource(fileName);
                validator.validate(source);
                LOGGER.info(fileName + " is valid");
                return true;

            } catch (SAXException e) {
                LOGGER.error(fileName + " is not valid. " + e.getMessage());

            } catch (IOException e) {
                LOGGER.error(e.getMessage() + " IOException");
            }
            return false;
        }
        LOGGER.error("File name or schema name is null");
        return false;
    }
}
