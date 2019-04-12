package by.epam.javatraining.aksenov.task6.model.logic.saxparser;

import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import by.epam.javatraining.aksenov.task6.util.ValidatorXML;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXParserDemo {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String FILE_NAME = "data/gemFund.xml";
    private static final String SCHEMA_NAME = "data/gemFund.xsd";

    private static final String SAX_ERROR = "SAX parser error ";
    private static final String IO_ERROR = "I/O error ";

    public static void main(String[] args) {
        if (ValidatorXML.validate(FILE_NAME, SCHEMA_NAME)) {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXHandler handler = new SAXHandler();

            try {
                SAXParser saxParser = parserFactory.newSAXParser();
                saxParser.parse(new File(FILE_NAME), handler);
            } catch (ParserConfigurationException | SAXException e) {
                LOGGER.error(SAX_ERROR + e);
            } catch (IOException e) {
                LOGGER.error(IO_ERROR + e);
            }

            GemFund gemFund = handler.getGemFund();
            LOGGER.info(gemFund);
        }
    }
}
