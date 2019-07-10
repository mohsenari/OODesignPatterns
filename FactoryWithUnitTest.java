import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


interface Car {
    public String getCarName();
}

class Toyota implements Car {
    public String getCarName() {
        return "TOYOTA";
    }
}

class Peugeot implements Car {
    public String getCarName() {
        return "PEUGEOT";
    }
}

class Ford implements Car {
    public String getCarName() {
        return "FORD";
    }
}

class CarFactory {
    public Car getCar(String carType) {
        if (carType.equalsIgnoreCase("American")) {
            return new Ford();
        } else if (carType.equalsIgnoreCase("Japanese")) {
            return new Toyota();
        } else if (carType.equalsIgnoreCase("French")) {
            return new Peugeot();
        }
        return null;
    }
}

class Main {
    public static void main (String args[]) {
        CarFactory factory = new CarFactory();
        Car myJapaneseCar = factory.getCar("JAPANESE");
        Car myAmericanCar = factory.getCar("AMERICAN");
        Car myFrenchCar = factory.getCar("FRENCH");

        System.out.println(myJapaneseCar.getCarName()); // TOYOTA
        System.out.println(myAmericanCar.getCarName()); // FORD
        System.out.println(myFrenchCar.getCarName()); // PEUGEOT
    }
}


public class FactoryWithUnitTest {
   @Test
   public void testCarFactory() {
        // Common case
        CarFactory testCarFactory = new CarFactory();
        Car myTestCar = testCarFactory.getCar("japanese");
        assertEquals(myTestCar.getCarName(),"TOYOTA");
        
        myTestCar = testCarFactory.getCar("american");
        assertEquals(myTestCar.getCarName(), "FORD");

        myTestCar = testCarFactory.getCar("french");
        assertEquals(myTestCar.getCarName(), "PEUGEOT");
   }

    @Test
    public void testInvalidCarFactory() {
        // Edge case
        CarFactory testCarFactory = new CarFactory();
        Car myTestCar = testCarFactory.getCar("invlid car type");

        try {
            myTestCar.getCarName();
        } catch(NullPointerException e) {
            assertNotNull(e);
        }
   }
}