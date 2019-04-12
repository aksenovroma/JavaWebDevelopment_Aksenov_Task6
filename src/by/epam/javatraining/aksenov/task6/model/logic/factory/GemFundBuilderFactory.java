package by.epam.javatraining.aksenov.task6.model.logic.factory;

public class GemFundBuilderFactory {
    private enum ParserType {
        SAX, STAX, DOM
    }
    public AbstractGemFundBuilder createGemFundBuilder(String typeParser) {
        ParserType type = ParserType.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new GemFundDOMBuilder();
            case SAX:
                return new GemFundSAXBuilder();
            case STAX:
                return new GemFundStAXBuilder();
            default:
                throw new java.lang.EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
