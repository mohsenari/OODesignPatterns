import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

// invoker class to define execute and unexecute methods for commands
class Invoker {
    ICommand on;
    ICommand dim;
    public Invoker(ICommand on, ICommand dim) {
        this.on = on;
        this.dim = dim;
    }

    public void turnOnLights() {
        this.on.execute();
    }

    public void turnOffLights() {
        this.on.unexecute();
    }

    public void dimLights() {
        this.dim.execute();
    }

    public void brightLights() {
        this.dim.unexecute();
    }
}

interface ICommand {
    public void execute();
    public void unexecute();
}

// first implementation of command class
class LightOnCommand implements ICommand{
    private Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    public void execute(){
        this.light.on();
    }

    public void unexecute() {
        this.light.off();
    }
}

// second implementation of command interface
class LightDimCommand implements ICommand{
    private Light light;

    public LightDimCommand(Light light){
        this.light = light;
    }

    public void execute(){
        this.light.dim();
    }

    public void unexecute() {
        this.light.bright();
    }
}

// receiver class - command class controls this
class Light {
    private String status = "off";
    public void on(){
        this.status = "on";
        System.out.println(this.status);
    }

    public void off(){
        this.status = "off";
        System.out.println(this.status);
    }

    public void dim() {
        this.status = "dim";
        System.out.println(this.status);
    }

    public void bright() {
        this.status = "bright";
        System.out.println(this.status);
    }

    public String getStatus() {
        return this.status;
    }
}

// main class to instantiate the receiver (light) and the invoker with commands
class Main {
    public static void main (String args[]){
        Light light = new Light();
        Invoker invoker = new Invoker(new LightOnCommand(light), new LightDimCommand(light));
        invoker.turnOnLights(); //on
        invoker.dimLights(); //dim
        invoker.brightLights(); //bright
        invoker.turnOffLights(); //off
    }
}

public class CommandWithUnitTest {
    @Test
    public void testInvoker() {
        Light light = spy(new Light());
        Invoker invoker = new Invoker(new LightOnCommand(light), new LightDimCommand(light));

        invoker.turnOnLights();
        verify(light).on();
        assertEquals("on", light.getStatus());

        invoker.dimLights();
        verify(light).dim();
        assertEquals("dim", light.getStatus());
    }
}