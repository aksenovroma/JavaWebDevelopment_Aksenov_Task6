package by.epam.javatraining.aksenov.task6.util;

import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MyUnMarshaller {
    private static final Logger LOGGER = Logger.getRootLogger();

    public static void unMarshal(String fileName) {
        try {
            JAXBContext jc = JAXBContext.newInstance(GemFund.class);

            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);

            GemFund students = (GemFund) u.unmarshal(reader);
            LOGGER.info(students);
        } catch (JAXBException | FileNotFoundException e) {
            LOGGER.error(e);
        }
    }
}
