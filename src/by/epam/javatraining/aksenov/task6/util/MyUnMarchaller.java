package by.epam.javatraining.aksenov.task6.util;

import by.epam.javatraining.aksenov.task6.model.entity.GemFund;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MyUnMarchaller {
    public static void unMarshal(String fileName) {
        try {
            JAXBContext jc = JAXBContext.newInstance(GemFund.class);

            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);

            GemFund students = (GemFund) u.unmarshal(reader);
            System.out.println(students);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
