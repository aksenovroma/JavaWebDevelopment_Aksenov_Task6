package by.epam.javatraining.aksenov.task6.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class GemFund {
    @XmlElement(name = "gem")
    private List<Gem> list = new ArrayList<>();

    public GemFund() {
    }

    public boolean add(Gem gem) {
        return list.add(gem);
    }

    public void setList(List<Gem> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GemFund{" +
                "list=" + list +
                '}';
    }
}
