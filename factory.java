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
        CarFactory myCarFactory = new CarFactory();
        Car myCar = myCarFactory.getCar("JAPANESE");

        System.out.println(myCar.getCarName());
    }
}