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
        gemFund = new GemFund(){
            {
                Gem.Visual visual = new Gem.Visual(Gem.Visual.Color.GREEN, 90, 10);
                Gem gem = new Gem(true, "Belarus", visual, 4, "Diamond", "d1");
                this.add(gem);

                visual = new Gem.Visual(Gem.Visual.Color.BLUE, 10, 15);
                gem = new Gem(false, "Russia", visual, 2, "Coral", "c1");
                this.add(gem);

                visual = new Gem.Visual(Gem.Visual.Color.RED, 50, 12);
                gem = new Gem(true, "USA", visual, 3, "Ruby", "r1");
                this.add(gem);
            }
        };
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
