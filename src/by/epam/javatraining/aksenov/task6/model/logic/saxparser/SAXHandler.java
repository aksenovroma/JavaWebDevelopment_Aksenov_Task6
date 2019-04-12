package by.epam.javatraining.aksenov.task6.model.logic.saxparser;

import by.epam.javatraining.aksenov.task6.model.entity.Gem;
import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    private static final Logger LOGGER = Logger.getRootLogger();

    private GemFund gemFund = new GemFund();
    private Gem gem = new Gem();
    private Gem.Visual visual = new Gem.Visual();
    private String thisElement;

    @Override
    public void startDocument() {
        LOGGER.trace("Start parse XML");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        thisElement = qName;
        if (thisElement != null) {
            if (thisElement.equals("gem")) {
                gem.setId(attrs.getValue("id"));
            }
        }
        LOGGER.trace(qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (thisElement != null) {
            if (thisElement.equals("name")) {
                gem.setName(new String(ch, start, length));
            }
            if (thisElement.equals("preciousness")) {
                if (new String(ch, start, length).toLowerCase().equals("true")) {
                    gem.setPreciousness(true);
                } else {
                    gem.setPreciousness(false);
                }
            }
            if (thisElement.equals("origin")) {
                gem.setOrigin(new String(ch, start, length));
            }
            if (thisElement.equals("color")) {
                String color = new String(ch, start, length).toUpperCase();

                switch (color) {
                    case "RED": {
                        visual.setColor(Gem.Visual.Color.RED);
                    }
                    case "BLUE": {
                        visual.setColor(Gem.Visual.Color.BLUE);
                    }
                    case "YELLOW": {
                        visual.setColor(Gem.Visual.Color.YELLOW);
                    }
                    case "GREEN": {
                        visual.setColor(Gem.Visual.Color.GREEN);
                    }
                }
            }
            if (thisElement.equals("transparency")) {
                visual.setTransparency(Double.parseDouble(new String(ch, start, length)));
            }
            if (thisElement.equals("faceting")) {
                visual.setFaceting(Integer.parseInt(new String(ch, start, length)));
            }
            if (thisElement.equals("value")) {
                gem.setValue(Double.parseDouble(new String(ch, start, length)));
            }
        }

        LOGGER.trace(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        thisElement = null;
        if (qName.equals("visual")) {
            gem.setVisual(visual);
            visual = new Gem.Visual();
        }
        if (qName.equals("gem")) {
            gemFund.add(gem);
            gem = new Gem();
        }
        LOGGER.trace(localName);
    }

    @Override
    public void endDocument() {
        LOGGER.trace("\nParsing ended");
    }

    public GemFund getGemFund() {
        return gemFund;
    }
}
