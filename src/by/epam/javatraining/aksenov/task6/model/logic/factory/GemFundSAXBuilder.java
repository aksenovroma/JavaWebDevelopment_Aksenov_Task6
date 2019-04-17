package by.epam.javatraining.aksenov.task6.model.logic.factory;

import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import by.epam.javatraining.aksenov.task6.model.logic.saxparser.SAXHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class GemFundSAXBuilder extends AbstractGemFundBuilder {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String SAX_ERROR = "SAX parser error ";
    private static final String IO_ERROR = "I/O error ";

    @Override
    public void buildGemFund(String fileName) {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXHandler handler = new SAXHandler();

        try {
            SAXParser saxParser = parserFactory.newSAXParser();
            saxParser.parse(new File(fileName), handler);
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error(SAX_ERROR + e);
        } catch (IOException e) {
            LOGGER.error(IO_ERROR + e);
        }

        GemFund gemFund = handler.getGemFund();
        setGemFund(gemFund);
        LOGGER.info(gemFund);
    }
}
