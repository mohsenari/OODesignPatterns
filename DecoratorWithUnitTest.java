import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

// base class for beverage
abstract class Beverage {
    public abstract double cost();
}
// base class for addon beverage
abstract class AddonBeverage extends Beverage {
    public abstract double cost();
}

// implementation of addon beverage
// note that this both HAS a beverage and IS a beverage
class Caramel extends AddonBeverage { // IS a beverage so that it can be passed to other addon bevarages
    Beverage beverage; // has a beverage so that you can get the cost of if

    public Caramel (Beverage beverage) {
        this.beverage = beverage;
    }

    public double cost () {
        return this.beverage.cost() + 2; // base beverage cost so far + cost of caramel addon
    }
}

class Chocolate extends AddonBeverage {
    Beverage beverage;

    public Chocolate (Beverage beverage) {
        this.beverage = beverage;
    }

    public double cost () {
        return this.beverage.cost() + 1.5; // base beverage cost so far + cost of caramel addon
    }
}

// base implementation for bevarage
// similar to base case for recursion
class Espresso extends Beverage {
    public double cost() {
        return 2; // return base cost for a plain espresso
    }
}

class Main {
    public static void main (String args[]) {
        // constructing a full beverage
        Beverage caramelChocolateEspresso = new Caramel(new Chocolate(new Espresso()));
        // the cost is espress.cost() + Chocolate.cost() + Caramel.cost()
        System.out.println(caramelChocolateEspresso.cost()); // 5.5 => 2 + 1.5 + 2
        
    }
}

public class DecoratorWithUnitTest {

    @Test
    public void testEspressoCost() {
        Beverage espresso = new Espresso();
        assertEquals(2, espresso.cost(), 0);
    }
    
    @Test
    public void testAddonBeverages() {
        Beverage espresso = mock(Espresso.class);
        double mockedEspressoCost = 5;
        doReturn(mockedEspressoCost).when(espresso).cost();
        
        Beverage caramelEspresso = new Caramel(espresso);
        assertEquals(7, caramelEspresso.cost(), 0); // 5-> mocked value + 2 caramel cost

        Beverage caramelChocolateEspresso = new Caramel(new Chocolate(espresso));
        assertEquals(8.5, caramelChocolateEspresso.cost(), 0);
    }
}
