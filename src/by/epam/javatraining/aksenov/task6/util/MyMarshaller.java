package by.epam.javatraining.aksenov.task6.util;

import by.epam.javatraining.aksenov.task6.model.entity.Gem;
import by.epam.javatraining.aksenov.task6.model.entity.GemFund;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MyMarshaller {
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
            System.out.println("XML-файл создан");
        } catch (FileNotFoundException e) {
            System.out.println("XML-файл не может быть создан: " + e);
        } catch (JAXBException e) {
            System.out.println("JAXB-контекст ошибочен " + e);
        }
    }
}
