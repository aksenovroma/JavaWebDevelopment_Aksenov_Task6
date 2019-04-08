package by.epam.javatraining.aksenov.task6.controller;

import by.epam.javatraining.aksenov.task6.util.MyUnMarchaller;

public class Main {
    private static final String FILE_NAME = "data/gemFund.xml";
    private static final String SCHEMA_NAME = "data/gemFund.xsd";
    private static final String NEW_FILE_NAME = "data/new_gemFund.xml";


    public static void main(String[] args) {
        MyUnMarchaller.unMarshal(NEW_FILE_NAME);
    }
}
