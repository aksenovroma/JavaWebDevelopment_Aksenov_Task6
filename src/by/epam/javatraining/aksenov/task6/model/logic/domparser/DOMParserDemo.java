package by.epam.javatraining.aksenov.task6.model.logic.domparser;

import by.epam.javatraining.aksenov.task6.util.ValidatorXML;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMParserDemo {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String FILE_NAME = "data/gemFund.xml";
    private static final String SCHEMA_NAME = "data/gemFund.xsd";

    private static final String PARSE_ERROR = "SAX parser error ";
    private static final String IO_ERROR = "I/O error ";

    public static void main(String[] args) {
        if (ValidatorXML.validate(FILE_NAME, SCHEMA_NAME)) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                Document doc = documentBuilder.parse(new File(FILE_NAME));
            } catch (SAXException | ParserConfigurationException e) {
                LOGGER.error(PARSE_ERROR + e);
            } catch (IOException e) {
                LOGGER.error(IO_ERROR + e);
            }
        }
    }
}
