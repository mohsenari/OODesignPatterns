import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

// Observable (Subject) class contains a list of observers,
// a state that changes in Main and a getter and setter for state
// notifyObservers calls update() in the list of observers 
class Observable {
    private int state = 0;
    private List<Observer> observers = new ArrayList<Observer>();

    public void setState (int newState) {
        state = newState;
        notifyObservers();
    }

    public int getState () {
        return this.state;
    }

    private void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addObserver(Observer obs) {
        observers.add(obs);
    }
}

// abstract class to be implemented differently by different observers
// only has an abstract method update and a pointer to Observable to get its state
// and be able to add itself to list of observers
abstract class Observer {
    protected Observable observable;
    public abstract void update();
}

// Different implementation of observer abstract class, show temprature in Celsius
class CelsiusObserver extends Observer { // not to get confused with java.util.Observer
    public CelsiusObserver(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    // completing the implementation abstract method in observer
    @Override
    public void update() {
        System.out.println("Updated celsius observer. Temprature is:" + observable.getState());
    }
}

// Different implementation of observer abstract class, show tempratur in Fahrenheit
class FahrenheitObserver extends Observer{
    public FahrenheitObserver(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    // completing the implementation abstract method in observer - converting to Fahrenheit
    @Override
    public void update() {
        System.out.println("Updated fahrenheit observer. Temprature is:" + (observable.getState() * 1.8 + 32) );
    }
}

// Main class to instantiate an observable, and two observers.
// then changing the observable's state to see update methods of 
// observers are being called.
class Main {
    public static void main (String args[]) {
        Observable observable = new Observable();
        new FahrenheitObserver(observable);
        new CelsiusObserver(observable);

        System.out.println("changing observer state");
        observable.setState(20);
        System.out.println("changing observer state again");
        observable.setState(25);

    }
}

// Unit tests

@RunWith(MockitoJUnitRunner.class)
public class ObserverWithUnitTest {
    // @Rule
    // public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testObservable() {
        Observable observable = spy(new Observable());
        CelsiusObserver celsiusObserver = new CelsiusObserver(observable);
        verify(observable).addObserver(celsiusObserver);
    }

    @Test
    public void testObserver() {
        Observable observable = new Observable();
        // type of celsiusObserver is Observer because the list in Observable class is of type Observer
        // if "CelsiusObserver celsiusObserver" used, Spy() doesn't recognize when .update() method is called 
        Observer celsiusObserver = spy(new CelsiusObserver(observable));
        observable.setState(15);
        verify(celsiusObserver).update();
    }
}