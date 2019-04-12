package by.epam.javatraining.aksenov.task6.model.logic.factory;

import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import by.epam.javatraining.aksenov.task6.model.logic.domparser.DOMHandler;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class GemFundDOMBuilder extends AbstractGemFundBuilder {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String PARSE_ERROR = "SAX parser error ";
    private static final String IO_ERROR = "I/O error ";

    @Override
    public void buildGemFund(String fileName) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            doc = documentBuilder.parse(new File(fileName));
        } catch (SAXException | ParserConfigurationException e) {
            LOGGER.error(PARSE_ERROR + e);
        } catch (IOException e) {
            LOGGER.error(IO_ERROR + e);
        }

        if (doc != null) {
            DOMHandler domHandler = new DOMHandler();
            domHandler.buildGenFund(doc);
            GemFund gemFund = domHandler.getGemFund();
            LOGGER.info(gemFund);
        }
    }
}
