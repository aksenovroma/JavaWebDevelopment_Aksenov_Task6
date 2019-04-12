package by.epam.javatraining.aksenov.task6.controller;

import by.epam.javatraining.aksenov.task6.util.ValidatorXML;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String FILE_NAME = "data/gemFund.xml";
    private static final String SCHEMA_NAME = "data/gemFund.xsd";
    private static final String NEW_FILE_NAME = "data/new_gemFund.xml";


    public static void main(String[] args) {
        /*MyMarshaller.marshal(FILE_NAME);


        MyUnMarshaller.unMarshal(NEW_FILE_NAME);
        //DOMParser*/
        ValidatorXML.validate(FILE_NAME, SCHEMA_NAME);
    }
}
