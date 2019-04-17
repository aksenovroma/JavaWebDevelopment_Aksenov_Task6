package by.epam.javatraining.aksenov.task6.controller;

import by.epam.javatraining.aksenov.task6.model.logic.Manager;
import by.epam.javatraining.aksenov.task6.model.logic.factory.AbstractGemFundBuilder;
import by.epam.javatraining.aksenov.task6.model.logic.factory.GemFundBuilderFactory;
import by.epam.javatraining.aksenov.task6.util.ValidatorXML;
import org.apache.log4j.Logger;
import properties.ProjectProperties;

import static properties.ProjectProperties.*;

public class Main {
    private static final String PROPERTIES_FILE = "src/properties/project.properties";
    private static final String MANAGER_RESULT = "Average weight of gems = ";
    private static final Logger LOGGER = Logger.getRootLogger();

    public static void main(String[] args) {
        ProjectProperties.loadProperties(PROPERTIES_FILE);
        GemFundBuilderFactory factory = new GemFundBuilderFactory();
        GemFundBuilderFactory.ParserType parserType = GemFundBuilderFactory.ParserType.valueOf(PARSER_TYPE);
        AbstractGemFundBuilder builder = factory.createGemFundBuilder(parserType);
        if (ValidatorXML.validate(INPUT_FILE, SCHEMA_FILE)) {
            builder.buildGemFund(INPUT_FILE);
        }
        LOGGER.info(MANAGER_RESULT + Manager.calcAvgGemWeight(builder.getGemFund()));
    }
}
