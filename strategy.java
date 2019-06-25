class Strategy {
    private IProcessString ips;
    private IProcessInteger ipi;

    public Strategy(IProcessString ips, IProcessInteger ipi) {
        this.ips = ips;
        this.ipi = ipi;
    }

    public String processString(String s) {
        return this.ips.processStringMethod(s);
    }

    public String processInteger(int n) {
        return this.ipi.processIntegerMethod(n);
    }
}

// Interface for processing a string
interface IProcessString {
    public String processStringMethod(String s);
}

// Implementations of the IProcessString interface:

// Process string implementation: reverse string
class ReverseString implements IProcessString {
    @Override
    public String processStringMethod(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}

// Process string implementation: summarize a string and add "..." at the end
class SummarizeString implements IProcessString {
    @Override
    public String processStringMethod(String s) {
        String summarized = s.substring(0, 3);
        summarized = summarized + "...";
        return summarized;
    }
}

// Interface for processing an integer
interface IProcessInteger {
    public String processIntegerMethod(int n);
}

// Implementations of the IProcessInteger interface:

// Process integer implementation: is prime
class IsPrime implements IProcessInteger {
    @Override
    public String processIntegerMethod(int n) {
        // returns zero if not prime, one if the number is prime
        String prime =  "Prime number";
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                prime = "Not a prime number";
            }
        }
        return prime;
    }
}

// Process integer implementation: odd or even
class OddOrEven implements IProcessInteger {
    @Override
    public String processIntegerMethod(int n) {
        // returns zero if even, one if the number is odd
        return (n % 2 == 0 ? "Even number" : "Odd number");
    }
}

class Main {
    public static void main(String args[]) {
        // Instantiation of Strategy class and passing desired implementations of the methods.
        Strategy strategy = new Strategy(new SummarizeString(), new OddOrEven());
        Strategy strategy2 = new Strategy(new ReverseString(), new IsPrime());

        // Calling the methods with same input and comparing the results
        System.out.println(strategy.processString("this is a string")); // thi...
        System.out.println(strategy.processInteger(2)); // Even number

        System.out.println(strategy2.processString("this is a string")); // gnirts a si siht olleh
        System.out.println(strategy2.processInteger(2)); // Prime number
    }
}