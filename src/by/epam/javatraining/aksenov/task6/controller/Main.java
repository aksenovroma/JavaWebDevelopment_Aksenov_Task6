package by.epam.javatraining.aksenov.task6.controller;

import by.epam.javatraining.aksenov.task6.model.logic.factory.AbstractGemFundBuilder;
import by.epam.javatraining.aksenov.task6.model.logic.factory.GemFundBuilderFactory;
import by.epam.javatraining.aksenov.task6.util.ValidatorXML;
import properties.ProjectProperties;

import static properties.ProjectProperties.*;

public class Main {
    private static final String PROPERTIES_FILE = "src/properties/project.properties";

    public static void main(String[] args) {
        ProjectProperties.loadProperties(PROPERTIES_FILE);
        GemFundBuilderFactory factory = new GemFundBuilderFactory();
        AbstractGemFundBuilder builder = factory.createGemFundBuilder("stax");
        if (ValidatorXML.validate(INPUT_FILE, SCHEMA_FILE)) {
            builder.buildGemFund(INPUT_FILE);
        }
    }
}
