package by.epam.javatraining.aksenov.task6.model.logic;

import by.epam.javatraining.aksenov.task6.model.entity.Gem;
import by.epam.javatraining.aksenov.task6.model.entity.GemFund;

public class Manager {
    public static double calcAvgGemWeight(GemFund gemFund) {
        if (gemFund != null && gemFund.size() > 0) {
            double sumGemWeight = 0;
            for (Gem gem : gemFund.getAll()) {
                sumGemWeight += gem.getValue();
            }
            return sumGemWeight / gemFund.size();
        }
        return -1;
    }
}
