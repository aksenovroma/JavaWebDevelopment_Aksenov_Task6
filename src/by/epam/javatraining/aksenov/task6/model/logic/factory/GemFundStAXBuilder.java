package by.epam.javatraining.aksenov.task6.model.logic.factory;

import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import by.epam.javatraining.aksenov.task6.model.logic.staxparser.StAXHandler;
import org.apache.log4j.Logger;

public class GemFundStAXBuilder extends AbstractGemFundBuilder {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public void buildGemFund(String fileName) {
        StAXHandler stAXHandler = new StAXHandler();
        stAXHandler.buildGemFund(fileName);
        GemFund gemFund = stAXHandler.getGemFund();
        LOGGER.info(gemFund);
    }
}
