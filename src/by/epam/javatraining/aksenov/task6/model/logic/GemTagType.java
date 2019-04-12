package by.epam.javatraining.aksenov.task6.model.logic;

public enum GemTagType {
    GEMFUND("gemFund"),
    GEM("gem"),
    NAME("name"),
    PRECIOUSNESS("preciousness"),
    ORIGIN("origin"),
    VISUAL("visual"),
    COLOR("color"),
    TRANSPARENCY("transparency"),
    FACETING("faceting"),
    VALUE("value");


    private String value;

    GemTagType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
