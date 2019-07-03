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