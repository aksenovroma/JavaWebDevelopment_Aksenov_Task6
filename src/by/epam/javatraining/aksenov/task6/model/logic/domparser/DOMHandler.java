package by.epam.javatraining.aksenov.task6.model.logic.domparser;

import by.epam.javatraining.aksenov.task6.model.entity.Gem;
import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static by.epam.javatraining.aksenov.task6.model.logic.GemTagType.*;

public class DOMHandler {
    private static final Logger LOGGER = Logger.getRootLogger();

    private GemFund gemFund = new GemFund();

    public GemFund getGemFund() {
        return gemFund;
    }

    public void buildGenFund(Document doc) {
        if (doc != null) {
            Element root = doc.getDocumentElement();
            NodeList gemList = root.getElementsByTagName(GEM.getValue());
            for (int i = 0; i < gemList.getLength(); i++) {
                Element gemElement = (Element) gemList.item(i);
                Gem gem = buildGem(gemElement);
                gemFund.add(gem);
            }
            LOGGER.trace("gemFund created");
        }
    }

    public Gem buildGem(Element gemElement) {
        Gem gem = new Gem();

        if (gemElement != null) {
            gem.setId(gemElement.getAttribute("id"));

            gem.setName(getElementTextContext(gemElement, NAME.getValue()));

            String preciousness = getElementTextContext(gemElement, PRECIOUSNESS.getValue()).toLowerCase();
            if (preciousness.equals("true")) {
                gem.setPreciousness(true);
            }

            gem.setOrigin(getElementTextContext(gemElement, ORIGIN.getValue()));

            Gem.Visual visual = gem.getVisual();
            Element visualElement = (Element) gemElement.getElementsByTagName(VISUAL.getValue()).item(0);

            String color = getElementTextContext(visualElement, COLOR.getValue()).toUpperCase();
            switch (color) {
                case "RED": {
                    visual.setColor(Gem.Visual.Color.RED);
                    break;
                }
                case "BLUE": {
                    visual.setColor(Gem.Visual.Color.BLUE);
                    break;
                }
                case "GREEN": {
                    visual.setColor(Gem.Visual.Color.GREEN);
                    break;
                }
                case "YELLOW": {
                    visual.setColor(Gem.Visual.Color.YELLOW);
                    break;
                }
            }
            double transparency = Double.parseDouble(getElementTextContext(visualElement, TRANSPARENCY.getValue()));
            visual.setTransparency(transparency);

            int faceting = Integer.parseInt(getElementTextContext(visualElement, FACETING.getValue()));
            visual.setFaceting(faceting);

            double value = Double.parseDouble(getElementTextContext(gemElement, VALUE.getValue()));
            gem.setValue(value);

            LOGGER.trace("gem '" + gem.getName() + "' created");
        }

        return gem;
    }

    private static String getElementTextContext(Element element, String elementName) {
        if (element != null && elementName != null) {
            NodeList nodeList = element.getElementsByTagName(elementName);
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }
}
