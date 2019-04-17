package by.epam.javatraining.aksenov.task6.model.logic.factory;

import by.epam.javatraining.aksenov.task6.model.entity.GemFund;

public abstract class AbstractGemFundBuilder {
    protected GemFund gemFund;

    public AbstractGemFundBuilder() {
        gemFund = new GemFund();
    }

    public GemFund getGemFund() {
        return gemFund;
    }

    public void setGemFund(GemFund gemFund) {
        this.gemFund = gemFund;
    }

    abstract public void buildGemFund(String fileName);
}
