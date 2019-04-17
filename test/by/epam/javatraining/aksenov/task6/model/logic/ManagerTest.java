package by.epam.javatraining.aksenov.task6.model.logic;

import by.epam.javatraining.aksenov.task6.model.entity.Gem;
import by.epam.javatraining.aksenov.task6.model.entity.GemFund;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ManagerTest {
    private GemFund gemFund;

    @BeforeMethod
    public void setGemFund() {


    }

    @Test
    public void testCalcAvgGemWeight() {
        double expected = 3;
        double actual = Manager.calcAvgGemWeight(gemFund);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcAvgGemWeightNullArgument() {
        double expected = -1;
        double actual = Manager.calcAvgGemWeight(null);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcAvgGemWeightEmptyList() {
        double expected = -1;
        gemFund.getAll().clear();
        double actual = Manager.calcAvgGemWeight(gemFund);

        assertEquals(actual, expected);
    }
}
