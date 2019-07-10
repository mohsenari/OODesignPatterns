import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

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

    public boolean processInteger(int n) {
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
    public boolean processIntegerMethod(int n);
}

// Implementations of the IProcessInteger interface:

// Process integer implementation: is prime
class IsPrime implements IProcessInteger {
    @Override
    public boolean processIntegerMethod(int n) {
        // returns false if not prime, true if the number is prime
        boolean prime =  true;
        //handling edge case
        if (n < 1) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                prime = false;
            }
        }
        return prime;
    }
}

// Process integer implementation: odd or even
class IsEven implements IProcessInteger {
    @Override
    public boolean processIntegerMethod(int n) {
        // returns true if even, false if the number is odd
        return (n % 2 == 0 ? true : false);
    }
}

class Main {
    public static void main(String args[]) {
        // Instantiation of Strategy class and passing desired implementations of the methods.
        Strategy strategy = new Strategy(new SummarizeString(), new IsEven());
        Strategy strategy2 = new Strategy(new ReverseString(), new IsPrime());

        // Calling the methods with same input and comparing the results
        System.out.println(strategy.processString("this is a string")); // thi...
        System.out.println(strategy.processInteger(2)); // Even number

        System.out.println(strategy2.processString("this is a string")); // gnirts a si siht
        System.out.println(strategy2.processInteger(0)); // Prime number

    }
}

@RunWith(MockitoJUnitRunner.class)
public class StrategyWithUnitTest {

    @Spy
    SummarizeString summarizeString = new SummarizeString();
    @Spy
    ReverseString reverseString = new ReverseString();

    @Test
    public void testStringMethod() {
        Strategy strategy = new Strategy(summarizeString, new IsEven());
        strategy.processString("testing");
        verify(summarizeString).processStringMethod("testing");

        Strategy strategy2 = new Strategy(reverseString, new IsEven());
        strategy2.processString("testing2");
        verify(reverseString).processStringMethod("testing2");
    }
    
    @Spy
    IsPrime isPrime = new IsPrime();
    @Spy
    IsEven isEven = new IsEven();

    @Test
    public void testIntegerMethod() {
        Strategy strategy = new Strategy(new SummarizeString(), isEven);
        strategy.processInteger(24);
        verify(isEven).processIntegerMethod(24);

        Strategy strategy2 = new Strategy(new ReverseString(), isPrime);
        strategy2.processInteger(10);
        verify(isPrime).processIntegerMethod(10);
    }

    @Test
    public void testReverseString() {
        // Common case
        ReverseString RS = new ReverseString();
        assertEquals(RS.processStringMethod("abcd"), "dcba");
        assertEquals(RS.processStringMethod(""), "");

        // Edge case
        try {
            RS.processStringMethod(null);
        } catch (java.lang.NullPointerException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testIsPrime() {
        // Common case
        IsPrime IP = new IsPrime();
        assertEquals(IP.processIntegerMethod(2), true);
        assertEquals(IP.processIntegerMethod(11), true);
        assertEquals(IP.processIntegerMethod(4), false);

        // Edge case
        assertEquals(IP.processIntegerMethod(0), false);
        assertEquals(IP.processIntegerMethod(-2), false);
    }
}