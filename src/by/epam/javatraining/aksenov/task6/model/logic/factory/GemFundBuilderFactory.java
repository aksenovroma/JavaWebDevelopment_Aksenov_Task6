package by.epam.javatraining.aksenov.task6.model.logic.factory;

public class GemFundBuilderFactory {
    public enum ParserType {
        SAX, STAX, DOM
    }

    public AbstractGemFundBuilder createGemFundBuilder(ParserType parserType) {
        AbstractGemFundBuilder builder = null;
        switch (parserType) {
            case DOM: {
                builder = new GemFundDOMBuilder();
                break;
            }
            case SAX: {
                builder = new GemFundSAXBuilder();
                break;
            }
            case STAX: {
                builder = new GemFundStAXBuilder();
                break;
            }
        }
        return builder;
    }
}
