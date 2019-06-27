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
