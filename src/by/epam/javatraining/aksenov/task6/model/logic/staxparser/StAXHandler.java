package by.epam.javatraining.aksenov.task6.model.logic.staxparser;

import by.epam.javatraining.aksenov.task6.model.entity.Gem;
import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import by.epam.javatraining.aksenov.task6.model.logic.GemTagType;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static by.epam.javatraining.aksenov.task6.model.logic.GemTagType.*;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class StAXHandler {
    private static final Logger LOGGER = Logger.getLogger(StAXHandler.class);

    private static final String PARSE_ERROR = "StAX parsing error! ";
    private static final String FILE_NOT_FOUND_ERROR = " File not found! ";
    private static final String CLOSE_FILE_ERROR = " Impossible close file ";
    private static final String UNKNOWN_ELEMENT_GEM = "Unknown element in tag Gem";
    private static final String UNKNOWN_ELEMENT_VISUAL = "Unknown element in tag Visual";

    private GemFund gemFund = new GemFund();
    private XMLInputFactory inputFactory;

    public StAXHandler() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public GemFund getGemFund() {
        return gemFund;
    }

    public void buildGemFund(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == START_ELEMENT) {
                    name = reader.getLocalName();
                    if (GemTagType.valueOf(name.toUpperCase()) == GEM) {
                        Gem gem = buildGem(reader);
                        gemFund.add(gem);
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error(PARSE_ERROR + e.getMessage());
        } catch (FileNotFoundException e) {
            LOGGER.error(fileName + FILE_NOT_FOUND_ERROR + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(fileName + CLOSE_FILE_ERROR + e);
            }
        }
    }

    private Gem buildGem(XMLStreamReader reader) throws XMLStreamException {
        Gem gem = new Gem();

        gem.setId(reader.getAttributeValue(null, "id"));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemTagType.valueOf(name.toUpperCase())) {
                        case NAME: {
                            gem.setName(getXMLText(reader));
                            break;
                        }
                        case PRECIOUSNESS: {
                            String preciousness = getXMLText(reader).toLowerCase();
                            if (preciousness.equals("true")) {
                                gem.setPreciousness(true);
                            }
                            break;
                        }
                        case ORIGIN: {
                            gem.setOrigin(getXMLText(reader));
                            break;
                        }
                        case VISUAL: {
                            gem.setVisual(getXMLVisual(reader));
                            break;
                        }
                        case VALUE: {
                            double value = Double.parseDouble(getXMLText(reader));
                            gem.setValue(value);
                            break;
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemTagType.valueOf(name.toUpperCase()) == GEM) {
                        return gem;
                    }
                    break;
            }
        }
        throw new XMLStreamException(UNKNOWN_ELEMENT_GEM);
    }

    private Gem.Visual getXMLVisual(XMLStreamReader reader) throws XMLStreamException {
        Gem.Visual visual = new Gem.Visual();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case START_ELEMENT: {
                    name = reader.getLocalName();
                    switch (GemTagType.valueOf(name.toUpperCase())) {
                        case COLOR: {
                            switch (getXMLText(reader).toUpperCase()) {
                                case "RED": {
                                    visual.setColor(Gem.Visual.Color.RED);
                                    break;
                                }
                                case "BLUE": {
                                    visual.setColor(Gem.Visual.Color.BLUE);
                                    break;
                                }
                                case "YELLOW": {
                                    visual.setColor(Gem.Visual.Color.YELLOW);
                                    break;
                                }
                                case "GREEN": {
                                    visual.setColor(Gem.Visual.Color.GREEN);
                                    break;
                                }
                            }
                        }
                        case TRANSPARENCY: {
                            double transparency = Double.parseDouble(getXMLText(reader));
                            visual.setTransparency(transparency);
                            break;
                        }
                        case FACETING: {
                            int faceting = Integer.parseInt(getXMLText(reader));
                            visual.setFaceting(faceting);
                            break;
                        }
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    name = reader.getLocalName();
                    if (GemTagType.valueOf(name.toUpperCase()) == VISUAL) {
                        return visual;
                    }
                    break;
                }
            }
        }
        throw new XMLStreamException(UNKNOWN_ELEMENT_VISUAL);
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {

            text = reader.getText();
            reader.next();
        }
        return text;
    }
}
