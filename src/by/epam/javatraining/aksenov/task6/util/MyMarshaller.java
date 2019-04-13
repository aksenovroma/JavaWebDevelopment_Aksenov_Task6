package by.epam.javatraining.aksenov.task6.util;

import by.epam.javatraining.aksenov.task6.model.entity.Gem;
import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MyMarshaller {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String FILE_CREATED = "XML-file created";
    private static final String FILE_ERROR = "XML-file didn't create";
    private static final String JAXB_ERROR = "JAXB-context error";

    public static void marshal(String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(GemFund.class);
            Marshaller m = context.createMarshaller();
            GemFund gF = new GemFund(){
                {
                    Gem.Visual visual = new Gem.Visual(Gem.Visual.Color.GREEN, 90, 10);
                    Gem gem = new Gem(true, "Belarus", visual, 3, "Diamond", "d1");
                    this.add(gem);

                    visual = new Gem.Visual(Gem.Visual.Color.BLUE, 110, 15);
                    gem = new Gem(false, "Russia", visual, 0.4, "Coral", "c1");
                    this.add(gem);
                }
            };
            m.marshal(gF, new FileOutputStream(fileName));
            LOGGER.info(FILE_CREATED);
        } catch (FileNotFoundException e) {
            LOGGER.error(FILE_ERROR + e);
        } catch (JAXBException e) {
            LOGGER.error(JAXB_ERROR + e);
        }
    }
}
