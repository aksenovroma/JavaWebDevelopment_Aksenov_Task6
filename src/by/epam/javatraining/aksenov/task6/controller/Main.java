package by.epam.javatraining.aksenov.task6.controller;

import by.epam.javatraining.aksenov.task6.model.logic.factory.AbstractGemFundBuilder;
import by.epam.javatraining.aksenov.task6.model.logic.factory.GemFundBuilderFactory;
import by.epam.javatraining.aksenov.task6.util.ValidatorXML;
import org.apache.log4j.Logger;

public class Main {
    private static final String FILE_NAME = "data/gemFund.xml";
    private static final String SCHEMA_NAME = "data/gemFund.xsd";

    public static void main(String[] args) {
        GemFundBuilderFactory factory = new GemFundBuilderFactory();
        AbstractGemFundBuilder builder = factory.createGemFundBuilder("sax");
        if (ValidatorXML.validate(FILE_NAME, SCHEMA_NAME)) {
            builder.buildGemFund(FILE_NAME);
        }
    }
}
