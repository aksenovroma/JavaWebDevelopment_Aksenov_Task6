package by.epam.javatraining.aksenov.task6.model.logic.staxparser;

import by.epam.javatraining.aksenov.task6.model.entity.Gem;
import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import by.epam.javatraining.aksenov.task6.model.logic.GemTagType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static by.epam.javatraining.aksenov.task6.model.logic.GemTagType.*;

public class StAXHandler {
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
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (GemTagType.valueOf(name.toUpperCase()) == GEM) {
                        Gem gem = buildGem(reader);
                        gemFund.add(gem);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file "+fileName+" : "+e);
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
                case XMLStreamConstants.START_ELEMENT:
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
        throw new XMLStreamException("Unknown element in tag Gem");
    }

    private Gem.Visual getXMLVisual(XMLStreamReader reader) throws XMLStreamException {
        Gem.Visual visual = new Gem.Visual();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case  XMLStreamConstants.START_ELEMENT: {
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
        throw new XMLStreamException("Unknown element in tag Visual");
    }


    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            if (!"".equals(reader.toString()) && reader.hasNext()){
                text = reader.getText();
            }


        }
        return text;
    }
}
