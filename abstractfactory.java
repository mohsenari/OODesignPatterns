interface Car {
    public String getCarName();
}

class FordElectric implements Car{
    public String getCarName() {
        return "Ford Model E";
    }
}
class Ford implements Car {
    public String getCarName() {
        return "Ford Focus";
    }
}

class Nissan implements Car{
    public String getCarName() {
        return "Nissan Rogue";
    }
}


class NissanElectric implements Car {
    public String getCarName() {
        return "Nisssan Leaf";
    }
}

interface CarFactory {
    public Car getCar(String carType);
}

class GasCarFactory implements CarFactory{
    public Car getCar(String carType) {
        if (carType.equalsIgnoreCase("American")) {
            return new Ford();
        } else if (carType.equalsIgnoreCase("Japanese")) {
            return new Nissan();
        }
        return null;
    }
}

class ElectricCarFactory implements CarFactory{
    public Car getCar(String carType) {
        if (carType.equalsIgnoreCase("American")) {
            return new FordElectric();
        } else if (carType.equalsIgnoreCase("Japanese")) {
            return new NissanElectric();
        }
        return null;
    }
}

class Main {
    public static void main (String args[]) {
        CarFactory factory = new GasCarFactory();
        Car myCar = factory.getCar("american");
        Car myCar2 = factory.getCar("japanese");
        System.out.println(myCar.getCarName()); // Ford Focus
        System.out.println(myCar2.getCarName()); // Nissan Rogue

        CarFactory eFactory = new ElectricCarFactory();
        Car myElectricCar = eFactory.getCar("american");
        Car myElectricCar2 = eFactory.getCar("japanese");
        System.out.println(myElectricCar.getCarName()); // Ford Model E
        System.out.println(myElectricCar2.getCarName()); // Nissan Leaf 
    }
}